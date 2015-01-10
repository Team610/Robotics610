
package org.usfirst.frc.team610.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;

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
	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	
    	rightFront = new Talon(1);
    	rightBack = new Talon(0);
    	leftFront = new Talon(3);
    	leftBack = new Talon(2);
    	driver = new Joystick(0);
 

    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {

    }

    /**
     * This function is called p1eriodically during operator control
     */
    
    public void teleopPeriodic() {

    	double angle, x, y,z, m, a, b, x2, y2, speedX, speedY ;
    	x = driver.getRawAxis(0);
    	y = driver.getRawAxis(1);
    	z = driver.getRawAxis(2);

    	angle = Math.atan2(y,x) + Math.PI/4.0;
    	angle += 2*Math.PI;
    	m = Math.sqrt(x*x + y*y);

    	y2 = m*Math.sin(angle);
    	x2 = m*Math.cos(angle);
    	angle = (angle - (Math.PI/4)) % (Math.PI/2) + (Math.PI/4);
    	
    	
    	
    	speedX = x2 * (1/Math.sin(angle));
    	speedY = y2 * (1/Math.sin(angle));
    	
    	rightFront.set(-speedX);
    	rightBack.set(-speedX);
    	leftBack.set(speedY);
    	leftFront.set(speedY);
    	
//    	double x, y, z;
//    	
//    	x = driver.getRawAxis(0)/2;
//    	y = driver.getRawAxis(1)/2;
//    	z = driver.getRawAxis(2)/2;
    	
//    	x = x*x*x;
//    	y = y*y*y;
//    	z = z*z*z;
    	
    	
    	
    	rightFront.set(y+x+z);
    	rightBack.set(y-x+z);
    	leftFront.set(-y+x+z);
    	leftBack.set(-y-x+z);
    	
    	
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}
