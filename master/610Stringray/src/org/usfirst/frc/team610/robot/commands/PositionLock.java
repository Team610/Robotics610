/*
 * Jack Williamson
 * December 1, 2014
 * ICS4U1-1
 * Position Lock Command
 * 
 */

package org.usfirst.frc.team610.robot.commands;

import org.usfirst.frc.team610.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PositionLock extends Command {

	//Position Lock is a very basic command, it locks the robot in place
	// and will move back to its position if it is has been pushed
	
	//declare a drivetrain
	Drivetrain drivetrain;
	
	//when a new position lock command is made, set up the drivetrain
    public PositionLock() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
    	//makes the drivetrain a reference the object to the only drivetrain that is allowed to exist
    	drivetrain = Drivetrain.getInstance();
    	//requires will throw an exception if there is no drivetrain
    	requires(drivetrain);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//set the encoders to zero
    	drivetrain.resetEncoders();
    }

    // Called repeatedly when this Command is scheduled to run
    //until this command is ended, be locked in this position 
    protected void execute() {
    	
   		//if left side is out of place by being too forward, move it back
   		if(drivetrain.getEncLeft() > 3){
   			drivetrain.setLeft(-1);
    	}
    	//if right side is out of place by being too forward, move it back
    	if(drivetrain.getEncRight() > 3){
    		drivetrain.setRight(-1);
    	}
    	//if left side is out of place by being too far back, move it forward
    	if(drivetrain.getEncLeft() < -3){
    		drivetrain.setLeft(1);
    	}
    	//if right side is out of place by being too far back, move it forward
    	if(drivetrain.getEncRight() < -3){
    		drivetrain.setRight(1);
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
