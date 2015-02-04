
package org.usfirst.frc.team610.robot;

import com.kauailabs.nav6.frc.IMUAdvanced;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	
	SerialPort serial_port;
	IMUAdvanced imu;
	Talon leftFront;
	Talon leftBack;
	Talon rightFront;
	Talon rightBack;
	Talon mid;
	Joystick driver;
	Encoder left;
	Encoder right;
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	leftFront = new Talon(1);
    	leftBack = new Talon(2);
    	rightFront = new Talon(3);
    	rightBack = new Talon(4);
    	mid = new Talon(0);
        driver = new Joystick(0);
        right = new Encoder(0,1);
        left = new Encoder(2,3);
        
     
        //IMU navX
      		try {
      			serial_port = new SerialPort(57600, SerialPort.Port.kMXP);
      			byte update_rate_hz = 50;
      			imu = new IMUAdvanced(serial_port, update_rate_hz);
      		} catch (Exception ex) {
      		}
      		if (imu != null) {
      			LiveWindow.addSensor("IMU", "Gyro", imu);
      		}

    }
    
    public void disabledPeriodic(){
    	System.out.println("Gyro: " + imu.getYaw());
    	System.out.println("Right Distance: " + right.getDistance());
    	System.out.println("Left Distance: " + left.getDistance());
    	
    	SmartDashboard.putNumber("Right Distance", right.getDistance());
		SmartDashboard.putNumber("Left Distance", left.getDistance());
		SmartDashboard.putNumber("Gyro", imu.getYaw());
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	double x, y, z, leftSpeed, rightSpeed, midSpeed;
    	
    	x = driver.getRawAxis(2);
    	y = -driver.getRawAxis(1);
    	z = driver.getRawAxis(0);
    	
    	leftSpeed = y + x;
    	rightSpeed = y - x;
    	midSpeed = z;
    	
    	leftFront.set(leftSpeed);
    	leftBack.set(leftSpeed);
    	rightFront.set(rightSpeed);
    	rightBack.set(rightSpeed);
    	mid.set(midSpeed);
        
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}
