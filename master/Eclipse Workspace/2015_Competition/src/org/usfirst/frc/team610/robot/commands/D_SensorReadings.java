package org.usfirst.frc.team610.robot.commands;

import org.usfirst.frc.team610.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class D_SensorReadings extends Command {

	DriveTrain driveTrain;
	
	
    public D_SensorReadings() {
    	driveTrain = DriveTrain.getInstance();
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println("Gyro: " + driveTrain.getYaw());
    	System.out.println("Right Distance: " + driveTrain.getRightDistance());
    	System.out.println("Left Distance: " + driveTrain.getLeftDistance());
    	
    	SmartDashboard.putNumber("Right Distance", driveTrain.getRightDistance());
		SmartDashboard.putNumber("Left Distance", driveTrain.getLeftDistance());
		SmartDashboard.putNumber("Gyro", driveTrain.getYaw());
    	
    	
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