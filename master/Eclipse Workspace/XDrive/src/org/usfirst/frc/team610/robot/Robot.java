package org.usfirst.frc.team610.robot;

import com.kauailabs.nav6.frc.IMUAdvanced;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;
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

	Talon rightFront;
	Talon rightBack;
	Talon leftFront;
	Talon leftBack;
	Joystick driver;
	Relay claw;
	SerialPort serial_port;
	// IMU imu; // Alternatively, use IMUAdvanced for advanced features
	IMUAdvanced imu;
	boolean first_iteration;
	boolean gyroLock;
	double angl;
	double fieldCentric;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {

		rightFront = new Talon(0);
		rightBack = new Talon(1);
		leftFront = new Talon(3);
		leftBack = new Talon(2);
		driver = new Joystick(0);
		claw = new Relay(0);

		gyroLock = true;
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
		first_iteration = true;
		fieldCentric = 0;
	}

	/**
	 * This function is called periodic ally during autonomous
	 */
	public void autonomousPeriodic() {

	}

	/**
	 * This function is called p1eriodically during operator control
	 */

	public void teleopInit() {
		imu.zeroYaw(); 
		angl = imu.getYaw();
		fieldCentric = angl;
	}

	public void teleopPeriodic() {

		double angle, x, y, z, m, a, b, x2, y2, speedX, speedY;
		boolean isPressed = false;
		//Get driver axes
		x = driver.getRawAxis(0);
		y = -driver.getRawAxis(1);
		
		double p = 0.03;
		double cur = imu.getYaw();
		while(cur<-180){
			cur+=360;
		}
		while(cur>180){
			cur-=360;
		}

		//Turn off gyro lock
		if (Math.abs(driver.getRawAxis(2)) > 0.05) {
			gyroLock = false;
		} 
		//restart gyrolock
		else if (driver.getRawButton(8)) {
			gyroLock = true;
			angl = cur;
		}
		//Reset fieldcentric
		if(driver.getRawButton(7)){
			imu.zeroYaw();
			
			fieldCentric = imu.getYaw();
			angl = fieldCentric;
			cur = angl;
		}
		
//		if (gyroLock) {
//			double error = angl - cur; // if positive,turning right. If
//			// Negative, turning left.
//
//			z = error * p;
//
//		} else {
//			z =	driver.getRawAxis(2);
//		}
//		z =	driver.getRawAxis(2);
		angl+=driver.getRawAxis(2)*8;
		double error = angl - cur; // if positive,turning right. If
		while(error<-180){
			error+=360;
		}
		while(error>180){
			error-=360;
		}
		z = error * p;
		
		
		double fieldCentricError = fieldCentric-cur;
		while(fieldCentricError<-180){
			fieldCentricError+=360;
		}
		while(fieldCentricError>180){
			fieldCentricError-=360;
		}
		System.out.println(fieldCentricError + " " + error);
//		angle = Math.atan2(y, x) - Math.PI / 4.0 ;
		angle = Math.atan2(y, x) - Math.PI / 4.0 - Math.PI/180.0*fieldCentricError;

		angle += 2 * Math.PI;
		m = Math.sqrt(x * x + y * y);

		y2 = m * Math.sin(angle);
		x2 = m * Math.cos(angle);
		angle = (angle - (Math.PI / 4)) % (Math.PI / 2) + (Math.PI / 4);

		speedX = x2 * (1 / Math.sin(angle));
		speedY = y2 * (1 / Math.sin(angle));

		//Claw control
		rightFront.set(-speedX + z);
		rightBack.set(-speedY + z);
		leftBack.set(speedY + z);
		leftFront.set(speedX + z);

		if (driver.getRawButton(5)) {
			claw.set(Relay.Value.kForward);
		}
		else if (driver.getRawButton(6)) {
			claw.set(Relay.Value.kReverse);
		}

	}
	public void disabledPeriodic(){
//		System.out.println("Disabled Init");
	}
	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {

	}

}
