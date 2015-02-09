package org.usfirst.frc.team610.robot.subsystems;

import org.usfirst.frc.team610.robot.constants.ElectricalConstants;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Bumper extends Subsystem {
	
	DoubleSolenoid arm;
	DoubleSolenoid wings;
	Talon winch;
	Encoder winchEncoder;
	static Bumper instance;
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private Bumper(){
    	arm = new DoubleSolenoid(0,1);
    	wings = new DoubleSolenoid(6,7);
    	winchEncoder = new Encoder(5,6);
    	winch = new Talon(ElectricalConstants.TALON_WINCH);
    }
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public static Bumper getInstance(){
    	if(instance == null){
    		instance = new Bumper();
    	}
    	return instance;
    }
    
    public void setArm(boolean position){
    	if(position){
    		arm.set(DoubleSolenoid.Value.kForward);
    	} else {
    		arm.set(DoubleSolenoid.Value.kReverse);
    	}
    }
    
    public void setWings(boolean position){
    	if(position){
    		wings.set(DoubleSolenoid.Value.kForward);
    	} else {
    		wings.set(DoubleSolenoid.Value.kReverse);
    	}
    }
    
    
    //PID Winch
//    public void setWinchPID(double position){
//    	winch.set(position - winchEncoder.getDistance());
//    }
    
    
    //Set Winch
    public void setWinch(double position){
    	if(winchEncoder.getDistance() < position){
    		winch.set(1);
    	} else {
    		winch.set(0);
    	}
    }
    
    public void resetWinchEncoder(){
    	winchEncoder.reset();
    }
    
    public double getWinchDistance(){
    	return winchEncoder.getDistance();
    }
    
    
    
}

