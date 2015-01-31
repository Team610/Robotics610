package org.usfirst.frc.team614.robot.commands;

import org.usfirst.frc.team614.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

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
    }

    double avgDistance;
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double p = 0.025;
		double d = 0.07;
		double leftSpeed = 0, rightSpeed = 0;
		double leftDistance, rightDistance;
		double tAngle;

		leftDistance = driveTrain.getLeftEncoderDistance();
		rightDistance = driveTrain.getRightEncoderDistance();


		tAngle = 0;
		System.out.println("Right " + rightDistance);
		System.out.println("Left " + leftDistance);
		System.out.println(driveTrain.getYaw());
		
		avgDistance = (leftDistance + rightDistance) / 2;
		
		
	//	if (avgDistance < tInches) {
			rightSpeed = Math.min(cap, (tInches - avgDistance) * 0.02);
			leftSpeed = Math.min(cap, (tInches - avgDistance) * 0.02);
		//}
		double error = tAngle - driveTrain.getYaw();
		double diffError = lastError - error;

		//Verify left and right
		if (Math.abs(error) < 0.15) {
			rightSpeed -= error * p - diffError * d;
			leftSpeed += error * p - diffError * d;
		} 

		driveTrain.setLeft(leftSpeed);
		driveTrain.setRight(rightSpeed);
		
		lastError = error;
    }
    
    int tick = 0;

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(Math.abs(avgDistance - tInches) < 5){
    		tick++;
    	} else {
    		tick = 0;
    	}
    	if (tick > 10){
    		driveTrain.setLeft(0);
    		driveTrain.setRight(0);
    		return true;
    	} else {
    		return false;
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