package org.usfirst.frc.team610.robot;

import com.kauailabs.nav6.frc.IMUAdvanced;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */

public class Robot extends IterativeRobot {

	SerialPort serial_port;
	// IMU imu; // Alternatively, use IMUAdvanced for advanced features
	IMUAdvanced imu;
	Victor leftFront;
	Victor leftBack;
	Victor rightFront;
	Victor rightBack;
	Joystick driver;
	Encoder leftEncoder;
	Encoder rightEncoder;
	Compressor compressor;
	DoubleSolenoid solenoid1;
	DoubleSolenoid solenoid2;

	public Robot() {

		try {
			serial_port = new SerialPort(57600, SerialPort.Port.kMXP);

			// You can add a second parameter to modify the
			// update rate (in hz) from 4 to 100. The default is 100.
			// If you need to minimize CPU load, you can set it to a
			// lower value, as shown here, depending upon your needs.

			// You can also use the IMUAdvanced class for advanced
			// features.

			byte update_rate_hz = 50;
			// imu = new IMU(serial_port,update_rate_hz);
			imu = new IMUAdvanced(serial_port, update_rate_hz);
		} catch (Exception ex) {

		}
		if (imu != null) {
			LiveWindow.addSensor("IMU", "Gyro", imu);
		}
	}

	double angle;
	double lastError = 0;
	boolean isNotDeployed = true;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		angle = imu.getYaw();
		leftFront = new Victor(0);
		leftBack = new Victor(1);
		rightFront = new Victor(2);
		rightBack = new Victor(3);
		driver = new Joystick(0);
		leftEncoder = new Encoder(0, 1);
		rightEncoder = new Encoder(2, 3);
		compressor = new Compressor();
		solenoid1 = new DoubleSolenoid(0, 1);
		solenoid2 = new DoubleSolenoid(6, 7);

	}

	/**
	 * This function is called periodically during autonomous
	 */

	public void autonomousInit() {
		imu.zeroYaw();
		angle = imu.getYaw();
		leftEncoder.reset();
		rightEncoder.reset();
	}

	public void autonomousPeriodic() {

		double p = 0.03;
		double d = 0.1;
		double leftSpeed = 0, rightSpeed = 0;
		double leftDistance, rightDistance, avgDistance;
		double tAngle, tInches;

		leftDistance = -leftEncoder.getDistance();
		rightDistance = rightEncoder.getDistance();

		tInches = 500;
		tAngle = 0;
		System.out.println("Right " + rightDistance);
		System.out.println("Left " + leftDistance);

		avgDistance = (leftDistance + rightDistance) / 2;

		if (avgDistance < 500) {
			rightSpeed = (tInches - avgDistance) * 0.02;
			leftSpeed = (tInches - avgDistance) * 0.02;
		}
		double error = tAngle - imu.getYaw();
		double diffError = lastError - error;
		// System.out.println("Right: " + rightSpeed);
		// System.out.println("Left: " + leftSpeed);
		System.out.println("Gyro: " + imu.getYaw());

		if (isNotDeployed) {
			setSolenoid(true);
			isNotDeployed = false;
		}

		if (error < -0.15) {
			rightSpeed -= error * p - diffError * d;
			leftSpeed += error * p - diffError * d;
		} else if (error > 0.15) {
			rightSpeed -= error * p - diffError * d;
			leftSpeed += error * p - diffError * d;
		}

		setLeft(leftSpeed);
		setRight(-rightSpeed);

		lastError = error;
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void setLeft(double power) {
		leftFront.set(power);
		leftBack.set(power);
	}

	public void setRight(double power) {
		rightFront.set(power);
		rightBack.set(power);
	}

	public void setSolenoid(boolean deploy) {
		if (deploy) {
			solenoid1.set(DoubleSolenoid.Value.kReverse);
			solenoid2.set(DoubleSolenoid.Value.kForward);

		} else {
			solenoid1.set(DoubleSolenoid.Value.kForward);
			solenoid2.set(DoubleSolenoid.Value.kReverse);
		}
	}

	public void teleopInit() {
		imu.zeroYaw();

	}
	
	boolean isPressed = false;
	boolean position = false;

	public void teleopPeriodic() {
		double x, y;
		// System.out.println("Left " + (-leftEncoder.getDistance()));
		// System.out.println("Right " + rightEncoder.getDistance());

		System.out.println("Gyro " + imu.getYaw());
		y = driver.getRawAxis(1);
		x = driver.getRawAxis(2);

		y = y * y * y;
		x = x * x * x;
		setLeft(x + y);
		setRight(x - y);

		if (driver.getRawButton(2) && !isPressed){
			setSolenoid(!position);
			isPressed = true;
		}
		if (!driver.getRawButton(2)){
			isPressed = false;
		}

	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {

	}

}