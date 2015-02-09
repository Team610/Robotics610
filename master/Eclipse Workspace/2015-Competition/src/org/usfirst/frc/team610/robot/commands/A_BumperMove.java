package org.usfirst.frc.team610.robot.commands;

import org.usfirst.frc.team610.robot.constants.ElectricalConstants;
import org.usfirst.frc.team610.robot.subsystems.Bumper;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class A_BumperMove extends Command {
	
	Bumper bumper;
	boolean position;
	boolean isFinished = false;
    public A_BumperMove(boolean position) {
    	bumper = Bumper.getInstance();
    	this.position = position;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(position){
    		bumper.setArm(ElectricalConstants.CROSSBOW_DOWN);
    		bumper.setWings(ElectricalConstants.CROSSBOW_UP);
    		isFinished = true;
    	} else {
    		bumper.setArm(ElectricalConstants.CROSSBOW_UP);
    		bumper.setWings(ElectricalConstants.CROSSBOW_DOWN);
    		isFinished = true;
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isFinished;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}