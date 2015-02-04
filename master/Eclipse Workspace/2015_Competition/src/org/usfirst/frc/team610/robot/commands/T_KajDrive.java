package org.usfirst.frc.team610.robot.commands;

import org.usfirst.frc.team610.robot.OI;
import org.usfirst.frc.team610.robot.constants.ElectricalConstants;
import org.usfirst.frc.team610.robot.constants.InputConstants;
import org.usfirst.frc.team610.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class T_KajDrive extends Command {

	Joystick driver;
	Joystick operator;
	
	DriveTrain driveTrain;
	
    public T_KajDrive() {
    	driver = OI.getInstance().getDriver();
    	operator = OI.getInstance().getOperator();
    	driveTrain = DriveTrain.getInstance();
    	
    	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    
    
    // Called just before this Command runs the first time
    double error;
    protected void initialize() {
    	driveTrain.zeroYaw();
    	error = driveTrain.getYaw();
    }

    // Called repeatedly when this Command is scheduled to run
    
    double lastError;
    protected void execute() {
    	double leftSpeed, rightSpeed, midSpeed, x, y, z, difference;
    	double p = 0.025;
    	double d = 0.07;
    	boolean isPressed = false;
    	boolean lock = false;
    	double currentAngle = driveTrain.getYaw();
    	
    	while (currentAngle < -180) {
			currentAngle += 360;
		}
		while (currentAngle > 180) {
			currentAngle -= 360;
		}
    	
		difference = currentAngle - lastError;

		
    	x = driver.getRawAxis(InputConstants.AXIS_X);
    	y = driver.getRawAxis(InputConstants.AXIS_Y);
    	z = driver.getRawAxis(InputConstants.AXIS_Z);
    	
    	leftSpeed = x + y;
    	rightSpeed = x - y;
    	midSpeed = z;
    	
    	if(driver.getRawButton(InputConstants.BTN_A) && !isPressed){
    		lock = !lock;
    		isPressed = true;
    	}
    	if(!driver.getRawButton(InputConstants.BTN_A)){
    		isPressed = false;
    	}
    	
    	if(lock){
    		if(driver.getRawAxis(InputConstants.AXIS_X) > 0.1){
        		error = driveTrain.getYaw();
    		}
    		leftSpeed -= error * p - difference * d;
    		rightSpeed += error * p - difference * d;	
    	}
    	
    	driveTrain.setLeft(leftSpeed);
    	driveTrain.setRight(rightSpeed);
    	driveTrain.setMid(midSpeed);
    	
    	lastError = error;
    	
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
