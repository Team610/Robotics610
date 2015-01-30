package org.usfirst.frc.team614.robot.commands;

import org.usfirst.frc.team614.robot.subsystems.DriveTrain;
import org.usfirst.frc.team614.robot.subsystems.OI;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class T_KajDrive extends Command {

	Joystick driver;
	DriveTrain driveTrain;
    public T_KajDrive() {
    	driver = OI.getInstance().getDriver();
    	driveTrain = DriveTrain.getInstance();
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double x, y;
		// System.out.println("Left " + (-leftEncoder.getDistance()));
		// System.out.println("Right " + rightEncoder.getDistance());
		y = driver.getRawAxis(1);
		x = driver.getRawAxis(2);

		y = y * y * y;
		x = x * x * x;
		
		driveTrain.setLeft(x + y);
		driveTrain.setRight(x - y);
		
		System.out.println("Gyro: " + driveTrain.getYaw());
		System.out.println("Left: " + driveTrain.getLeftEncoderDistance());
		System.out.println("Right: " + driveTrain.getRightEncoderDistance());
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
