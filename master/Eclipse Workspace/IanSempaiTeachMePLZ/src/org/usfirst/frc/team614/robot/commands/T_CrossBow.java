package org.usfirst.frc.team614.robot.commands;

import org.usfirst.frc.team614.robot.subsystems.CrossBow;
import org.usfirst.frc.team614.robot.subsystems.OI;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class T_CrossBow extends Command {
	
	Joystick driver;
	CrossBow crossBow;
    boolean isPressed = false;
    boolean solenoid = false;

    public T_CrossBow() {
    	driver = OI.getInstance().getDriver();
    	crossBow = CrossBow.getInstance();
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    
    // Called repeatedly when this Command is scheduled to run

    protected void execute() {
    	if(driver.getRawButton(2) && !isPressed){
    		solenoid = !solenoid;
    		//crossBow.setArm(solenoid);
    		crossBow.setWing(solenoid);
    		isPressed = true;
    		System.out.println("toggled");
    	}
    	if(!driver.getRawButton(2)){
    		isPressed = false;
    		
    	}
   
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
