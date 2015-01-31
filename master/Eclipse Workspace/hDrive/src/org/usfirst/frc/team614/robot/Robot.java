package org.usfirst.frc.team614.robot;

import com.kauailabs.nav6.frc.IMUAdvanced;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	Talon leftFront;
	Talon leftBack;
	Talon rightFront;
	Talon rightBack;
	Talon mid;
	DoubleSolenoid arm;
	DoubleSolenoid wing;
	Joystick driver;
	SerialPort serial_port;
	IMUAdvanced imu;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		leftFront = new Talon(1);
		leftBack = new Talon(3);
		rightFront = new Talon(0);
		rightBack = new Talon(2);
		mid = new Talon(4);
		driver = new Joystick(0);
		arm = new DoubleSolenoid(0, 1);
		wing = new DoubleSolenoid(6, 7);
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
			ex.printStackTrace();

		}
		if (imu != null) {
			LiveWindow.addSensor("IMU", "Gyro", imu);
		}
	}

	public void setLeft(double speed) {
		leftFront.set(speed);
		leftBack.set(speed);
	}

	public void setRight(double speed) {
		rightFront.set(-speed);
		rightBack.set(-speed);
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {

	}

	/**
	 * This function is called periodically during operator control
	 */

	public void teleopInit() {
		imu.zeroYaw();
		error = imu.getYaw();
	}

	public void setSolenoid(boolean on) {
		if (on) {
			arm.set(DoubleSolenoid.Value.kForward);
			wing.set(DoubleSolenoid.Value.kForward);
		} else {
			arm.set(DoubleSolenoid.Value.kReverse);
			wing.set(DoubleSolenoid.Value.kReverse);
		}
	}

	double error;
	double lastError;
	boolean gyroLock;
	boolean isPressed = false;
	boolean isLock = false;;
	boolean solenoid = false;

	public void teleopPeriodic() {

		double leftSpeed, rightSpeed, midSpeed, x, y, z;
		double p = 0.02;
		double d = 0.35;
		double currentAngle = imu.getYaw();
		double diffError = lastError - error;

		while (currentAngle < -180) {
			currentAngle += 360;
		}
		while (currentAngle > 180) {
			currentAngle -= 360;
		}
		double difference = currentAngle - error;
		difference = difference * p;

		// System.out.println("gyro " + imu.getYaw());

		x = driver.getRawAxis(2);
		y = driver.getRawAxis(1);
		z = driver.getRawAxis(0);

		leftSpeed = (y + x);
		rightSpeed = (y - x);
		midSpeed = z;

		if(driver.getRawButton(1)){
			isLock = true;
		} else if(driver.getRawButton(3)){
			isLock = false;
		}
		
		if(isLock){
			if (Math.abs(x) > 0.08) {
				gyroLock = false;
			}
	
			if (Math.abs(x) < 0.08 && !gyroLock) {
				gyroLock = true;
				error = currentAngle;
			}
	
			if (gyroLock) {
				leftSpeed -= difference - diffError * d;
				rightSpeed += difference - diffError * d;
			}
		} else {
			error = currentAngle;
		}
		
		// SetSolenoid
		if (driver.getRawButton(2) && !isPressed) {
			solenoid = !solenoid;
			setSolenoid(solenoid);
			isPressed = true;
			System.out.println("toggled");
		}
		if (!driver.getRawButton(2)) {
			isPressed = false;
		}

		setLeft(leftSpeed);
		setRight(rightSpeed);
		mid.set(midSpeed);

		lastError = error;

	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {

	}

}