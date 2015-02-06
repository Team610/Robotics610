package org.usfirst.frc.team610.robot.commands;

import org.usfirst.frc.team610.robot.constants.ElectricalConstants;
import org.usfirst.frc.team610.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class A_PositionMove extends Command {

	DriveTrain driveTrain;
	double angle;
	double lastError = 0;
	double tInches;
	double cap;
	double tAngle;
	double error;
	
	public A_PositionMove(double tInches, double cap) {

		driveTrain = DriveTrain.getInstance();
		requires(driveTrain);
		this.tInches = tInches;
		this.cap = cap;
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		driveTrain.resetEncoders();
		tAngle = driveTrain.getYaw();
		error = driveTrain.getYaw();
		setTimeout(0);
		if (tInches == 0) {
			setTimeout(90);
		}
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double p = ElectricalConstants.GYRO_P;
		double d = ElectricalConstants.GYRO_D;
		double leftSpeed, rightSpeed;
		
		double diffError;
		System.out.println("Gyro: " + driveTrain.getYaw());

		rightSpeed = Math.min(cap, (tInches - driveTrain.getAvgDistance())
				* ElectricalConstants.ENCODER_P);
		leftSpeed = Math.min(cap, (tInches - driveTrain.getAvgDistance())
				* ElectricalConstants.ENCODER_P);


		error = tAngle - driveTrain.getYaw();
		
		diffError = error - lastError;
		
		if(Math.abs(driveTrain.getYaw()) > 0.2){
	    	leftSpeed -= error * p + diffError * d;
	    	rightSpeed += error * p + diffError * d;	
	    	
		}
    	
    	
		if (rightSpeed > cap) {
			leftSpeed -= rightSpeed - cap;
		} else if (leftSpeed > cap) {
			rightSpeed -= leftSpeed - cap;
		} else if (rightSpeed < -cap) {
			leftSpeed -= rightSpeed + cap;
		} else if (leftSpeed < -cap) {
			rightSpeed -= leftSpeed + cap;
		}

		driveTrain.setLeft(leftSpeed);
		driveTrain.setRight(rightSpeed);

		lastError = error;

	}

	int tick = 0;

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (tInches == 0) {
			return isTimedOut();
		} else {
			if (Math.abs(driveTrain.getAvgDistance() - tInches) < 5) {
				tick++;
			} else {
				tick = 0;
			}

			if (tick > 20) {
				driveTrain.setLeft(0);
				driveTrain.setRight(0);
				return true;
			} else {
				return false;
			}
		}
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}