package org.usfirst.frc.team614.robot.commands;

import org.usfirst.frc.team614.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class A_DriveForward extends Command {
	
	DriveTrain driveTrain;
	double angle;
	double lastError = 0;

	int tInches;
	double cap;
    public A_DriveForward(int tInches, double cap) {
    	
    	driveTrain = DriveTrain.getInstance();
    	requires(driveTrain);
    	this.tInches = tInches;
    	this.cap = cap;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }
    // Called just before this Command runs the first time
    protected void initialize() {
    	driveTrain.resetEncoder();
    	setTimeout(0);
    	if(tInches == 0){
    		setTimeout(90);
    	}
    }

    double avgDistance;
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double p = 0.025;
		double d = 0.07;
		double leftSpeed = 0, rightSpeed = 0;
		double leftDistance, rightDistance;
		double tAngle;
		System.out.println("Gyro: " + driveTrain.getYaw());

		leftDistance = driveTrain.getLeftEncoderDistance();
		rightDistance = driveTrain.getRightEncoderDistance();
		
		tAngle = 0;
		
		avgDistance = (leftDistance + rightDistance) / 2;
		
		
	//	if (avgDistance < tInches) {
			rightSpeed = Math.min(cap, (tInches - avgDistance) * 0.02);
			leftSpeed = Math.min(cap, (tInches - avgDistance) * 0.02);
		//}
			
			SmartDashboard.putNumber("RightSpeed1", rightSpeed);
			SmartDashboard.putNumber("LeftSpeed1", leftSpeed);
			
		double error = tAngle - driveTrain.getYaw();
		double diffError = lastError - error;

		//Verify left and right
		//if (Math.abs(error) > 0.15) {
			rightSpeed -= error * p - diffError * d;
			leftSpeed += error * p - diffError * d;
		//} 
		
		SmartDashboard.putNumber("RightSpeed2", rightSpeed);
		SmartDashboard.putNumber("LeftSpeed2", leftSpeed);
		
		
		if(rightSpeed > cap){
			leftSpeed -= rightSpeed - cap;
		} else if (leftSpeed > cap){
			rightSpeed -= leftSpeed - cap;
		} else if (rightSpeed < -cap){
			leftSpeed -= rightSpeed + cap;
		} else if (leftSpeed < -cap){
			rightSpeed -= leftSpeed + cap;
		}
		
		SmartDashboard.putNumber("LeftSpeed3", leftSpeed);
		SmartDashboard.putNumber("RightSpeed3", rightSpeed);
		
		driveTrain.setLeft(leftSpeed);
		driveTrain.setRight(rightSpeed);
		
		lastError = error;
		
		SmartDashboard.putNumber("Gyro", driveTrain.getYaw());
		SmartDashboard.putNumber("Error", error);
		
    }
    
    int tick = 0;

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(tInches == 0){
    		return isTimedOut();
    	} else {
	    	if(Math.abs(avgDistance - tInches) < 5){
	    		tick++;
	    	} else {
	    		tick = 0;
	    	}
    	
	    	if (tick > 20){
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
