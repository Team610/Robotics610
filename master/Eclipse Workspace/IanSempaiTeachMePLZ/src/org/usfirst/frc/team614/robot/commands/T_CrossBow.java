package org.usfirst.frc.team614.robot.commands;

import org.usfirst.frc.team614.robot.OI;
import org.usfirst.frc.team614.robot.constants.ElectricalConstants;
import org.usfirst.frc.team614.robot.subsystems.CrossBow;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class T_CrossBow extends Command {
	
	Joystick driver;
	CrossBow crossBow;
    boolean isPressed = false; 
    boolean isWingsPressed = false;
    boolean solenoid = false;
    boolean wings = false;

    public T_CrossBow() {
    	driver = OI.getInstance().getDriver();
    	crossBow = CrossBow.getInstance();
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    
    // Called repeatedly when this Command is scheduled to run

    protected void execute() {
    	SmartDashboard.putNumber("Winch Encoder", crossBow.getEncoder());
    	System.out.println("CrossBow" + crossBow.getEncoder());
    	if(driver.getRawButton(2) && !isPressed){
    		solenoid = !solenoid;
    		//crossBow.setArm(solenoid);
    		crossBow.setArm(solenoid);
    		isPressed = true;
    		System.out.println("toggled");
    	}
    	if(!driver.getRawButton(2)){
    		isPressed = false;
    		
    	}
    	if(driver.getRawButton(3)){
    		crossBow.setArm(true);
    	}
    	if(driver.getRawButton(4)){
    		crossBow.setArm(false);
    	}
    	
    	
    	
    	if(driver.getRawButton(5)){
    		crossBow.winchDown();
    		
    	}
    	else if(driver.getRawButton(6)){
    		crossBow.winchUp();
    		
    	}else{
    		crossBow.winchZero();
    	}
    	
    	
    	
    	if(driver.getRawButton(7)){
    		crossBow.setWing(true);
    	
    	}
    	if(driver.getRawButton(8)){
    		crossBow.setWing(false);
    	}
    	
   
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
