/*
 * Jack Williamson
 * December 1, 2014
 * ICS4U1-1
 * KAJ Drive Command
 */

package org.usfirst.frc.team610.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team610.robot.subsystems.Drivetrain;

/**
 *
 */
public class KAJDrive extends Command {

	//declare a drivetrain
	Drivetrain drivetrain;
	//declare a  joystick for controlling the robot
	Joystick driver;
	
	//When new kaj drive is created, set up the drivetrain and controller
    public KAJDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
    	//makes the drivetrain a reference the object to the only drivetrain that is allowed to exist
    	drivetrain = Drivetrain.getInstance();
    	
    	//set up the controller
    	driver = new Joystick(0);
    	
    	//requires will throw an exception if there is no drivetrain set up
    	requires(drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//receives movements of the joystick
    	double x = driver.getRawAxis(3);
    	double y = driver.getRawAxis(2);
    	
    	//calculates the speeds for the motors on each side
    	double leftSpeed = y-x;
    	double rightSpeed = y+x;
    	
    	//sets the speeds for each side of the drivetrain
    	drivetrain.setLeft(leftSpeed);
    	drivetrain.setRight(rightSpeed);
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
