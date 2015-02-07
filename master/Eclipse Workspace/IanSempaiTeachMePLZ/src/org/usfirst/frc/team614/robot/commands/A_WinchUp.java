package org.usfirst.frc.team614.robot.commands;

import org.usfirst.frc.team614.robot.subsystems.CrossBow;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class A_WinchUp extends Command {
CrossBow crossBow;
int rots;
boolean finished = false;
    public A_WinchUp(int num) {
    	crossBow = CrossBow.getInstance();
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	rots = num;
    }

    // Called just before this Command runs the first time
    
    protected void initialize() {
    	 crossBow.resetWinch();
    	 setTimeout(0);
    	 
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	int checkStall = 0;
//    	int lastEnc = 0;
//    	int currentEnc = crossBow.getEncoder();
    	
    	if(crossBow.getEncoder() < rots){
    		crossBow.winchUp();
    	}else{
    		crossBow.winchZero();
    		finished = true;
    	}
//    	if(Math.abs(currentEnc - lastEnc) < 10 ){
//    		checkStall++;
//    		
//    	}
//    	lastEnc = currentEnc;
//    	if(checkStall > 70){
//    		crossBow.winchZero();
//    		
//    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
       return finished;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}