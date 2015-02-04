package org.usfirst.frc.team614.robot.commands;

import org.usfirst.frc.team614.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class A_ResetGyro extends Command {
	DriveTrain driveTrain;

    public A_ResetGyro() {
    	driveTrain = DriveTrain.getInstance();
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	setTimeout(0.5);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	driveTrain.resetGyro();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return isTimedOut();
        
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
