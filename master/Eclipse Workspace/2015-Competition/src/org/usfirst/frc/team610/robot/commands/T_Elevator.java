package org.usfirst.frc.team610.robot.commands;

import org.usfirst.frc.team610.robot.OI;
import org.usfirst.frc.team610.robot.constants.ElectricalConstants;
import org.usfirst.frc.team610.robot.constants.InputConstants;
import org.usfirst.frc.team610.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class T_Elevator extends Command {

	Elevator elevator;
	Joystick driver;
	OI oi;
	double bottomPoint,midPoint;
	int elevatorPosition = 0;
	double iCounter;
    public T_Elevator() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	 oi = OI.getInstance();
    	 driver = oi.getDriver();
    	 elevator = Elevator.getInstance();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double curPot = elevator.getPot();
    	if(driver.getRawButton(InputConstants.BTN_A)){
    		elevatorPosition = 0;
    	}
    	if(driver.getRawButton(InputConstants.BTN_B)){
    		elevatorPosition = 1;
    	}
    	
    	double error = 0;
    	double targetSetpoint = 0.7;
    	switch(elevatorPosition){
    	
    	case 0:
    		targetSetpoint = ElectricalConstants.ELEVATOR_BOTTOM;
    		break;
    	case 1:
    		targetSetpoint = ElectricalConstants.ELEVATOR_MID;
    		break;
    	
    	}
    	error = curPot - targetSetpoint;
    	if(error>0.005&&iCounter<1000){
    		iCounter++;
    	} else if(error<-0.005&&iCounter>-1000){
    		iCounter--;
    	} 
    	
		elevator.setMotor(ElectricalConstants.ELEVATOR_P*error+iCounter*ElectricalConstants.ELEVATOR_I);
    	//System.out.println(curPot);
    	System.out.println(elevator.getPot());
    	
    	
    	
//    	if(driver.getRawButton(InputConstants.BTN_L1)){
//    		//Down
//    		elevator.setMotor(-0.1);
//    	}else if(driver.getRawButton(InputConstants.BTN_R1)){
//    		//Up
//    		elevator.setMotor(0.1);
//    	}else{
//    		elevator.setMotor(0);
//    	}
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
