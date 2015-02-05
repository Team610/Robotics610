
package org.usfirst.frc.team610.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
	
	Talon leftRoller;
	Talon rightRoller;
	Solenoid leftClaw;
	Solenoid rightClaw;
	DigitalInput opticalLeft;
	DigitalInput opticalRight;
	Joystick driver;
	
	boolean openClawPressed;
	boolean lockClaw;
	
    public void robotInit() {
    	
    	leftRoller = new Talon(0);
    	rightRoller = new Talon(1);
    	
    	leftClaw = new Solenoid(0);
    	rightClaw = new Solenoid(1);
    	
    	opticalLeft = new DigitalInput(0);
    	opticalRight = new DigitalInput(1);
    	
    	driver = new Joystick(0);
    	
    	openClawPressed = false;
    	lockClaw = false;

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
        
    	boolean rollerPressedIn = driver.getRawButton(0);
    	boolean rollerPressedOut = driver.getRawButton(1);
    	
    	boolean grab = opticalLeft.get() || opticalRight.get();
    	
    	
    	if(rollerPressedIn && !grab){
    		setRollers(1);
    	}
    	else if(rollerPressedOut || grab){
    		setRollers(-1);
    	}
    	else{
    		setRollers(0);
    	}
    	
    	
    	
    	//toggle for solenoid
    	
    	if(driver.getRawButton(2) && !openClawPressed){
    		lockClaw = !lockClaw;
    		openClawPressed = true;
    		leftClaw.set(lockClaw);
    		rightClaw.set(lockClaw);
    	}
    	if(!driver.getRawButton(2)){
    		openClawPressed = false;
    	}
    	
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
    public void setRollers(double speed){
    	leftRoller.set(speed);
    	rightRoller.set(speed);
    }
    
}
