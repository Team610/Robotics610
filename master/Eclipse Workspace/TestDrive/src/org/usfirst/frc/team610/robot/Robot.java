
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
	
	
	Talon leftTop;
	Talon leftMid;
	Talon leftBot;
	Talon rightTop;
	Talon rightMid;
	Talon rightBot;
	Joystick driver;
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	leftTop = new Talon(0);
    	leftMid = new Talon(1);
    	leftBot = new Talon(2);
    	rightTop = new Talon(3);
    	rightMid = new Talon(4);
    	rightBot = new Talon(5);
    	driver = new Joystick(0);

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
        double x, y, leftSpeed, rightSpeed;
    	x = driver.getRawAxis(1);
    	y = driver.getRawAxis(2);

    	leftSpeed = x+y;
    	rightSpeed = x-y;
    	setRightVBus(rightSpeed);
    	setLeftVBus(leftSpeed);
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
    public void setLeftVBus(double power){
    	leftTop.set(power);
    	leftMid.set(power);
    	leftBot.set(power);
    }
    
    public void setRightVBus(double power){
    	rightTop.set(power);
    	rightMid.set(power);
    	rightBot.set(power);
    }
    
}
