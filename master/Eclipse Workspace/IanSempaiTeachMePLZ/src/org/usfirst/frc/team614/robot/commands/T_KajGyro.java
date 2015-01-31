package org.usfirst.frc.team614.robot.commands;

import org.usfirst.frc.team614.robot.OI;
import org.usfirst.frc.team614.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class T_KajGyro extends Command {

	Joystick driver;
	DriveTrain driveTrain;
    public T_KajGyro() {
    	driver = OI.getInstance().getDriver();
    	driveTrain = DriveTrain.getInstance();
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }
    
    double error;

    // Called just before this Command runs the first time
    protected void initialize() {
    	driveTrain.resetGyro();
    	error = driveTrain.getYaw();
    }
    double lastError = 0;
    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double x, y, leftSpeed, rightSpeed, currentAngle, difference;
    	double p = 0.05;
    	currentAngle = driveTrain.getYaw();
    	System.out.println("Gyro: " + currentAngle);
		// System.out.println("Left " + (-leftEncoder.getDistance()));
		// System.out.println("Right " + rightEncoder.getDistance());
		y = driver.getRawAxis(1);
		x = driver.getRawAxis(2);
		
		y = y * y * y;
		x = x * x * x;
		
		leftSpeed = x + y;
		rightSpeed = x - y;
		
		while (currentAngle < -180) {
			currentAngle += 360;
		}
		while (currentAngle > 180) {
			currentAngle -= 360;
		}
		
		difference = currentAngle - error;
		
		difference *= p;
		
		leftSpeed -= difference;
		rightSpeed -= difference;
		
		driveTrain.setLeft(leftSpeed);
		driveTrain.setRight(rightSpeed);
		
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