package org.usfirst.frc.team614.robot.commands;

import org.usfirst.frc.team614.robot.constants.ElectricalConstants;
import org.usfirst.frc.team614.robot.subsystems.CrossBow;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class A_CrossBow extends Command {
	
	CrossBow crossBow;
	boolean finished = false;
	

    public A_CrossBow() {
    	crossBow = CrossBow.getInstance();
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	setTimeout(0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	crossBow.setArm(ElectricalConstants.ARM_DOWN);
    		
    	finished = true;
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return finished;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
