package org.usfirst.frc.team614.robot.subsystems;

import org.usfirst.frc.team614.robot.constants.ElectricalConstants;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class CrossBow extends Subsystem {
	
	DoubleSolenoid arm;
	DoubleSolenoid wing;
	Talon winch;
	Encoder winchEncoder;
	
	double tDistance;
	
	static CrossBow instance;
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    private CrossBow(){
    	arm = new DoubleSolenoid (6,7);
    	wing = new DoubleSolenoid (0,1);
    	winch = new Talon(4);
    	winchEncoder = new Encoder(4,5);
    }
    
    public static CrossBow getInstance(){
    	if(instance == null){
    		instance = new CrossBow();
    	}
    	return instance;
    }
    public void winchUp(){
   	 winch.set(0.5);
    }
    public void winchDown(){
   	 winch.set(-1.0);
    }
    public void winchZero(){
    	winch.set(0);
    }
    public void setArm(boolean up){
    	if(up == ElectricalConstants.ARM_UP) {
    		arm.set(DoubleSolenoid.Value.kForward);
    		
    	} else {
    		arm.set(DoubleSolenoid.Value.kReverse);
    		
    	}
    }
    
    public void setWing(boolean up){
    	if(up == ElectricalConstants.ARM_UP) {
    		wing.set(DoubleSolenoid.Value.kForward);
    		System.out.println("Arm Forward");
    	} else {
    		wing.set(DoubleSolenoid.Value.kReverse);
    		System.out.println("Arm Backward");
    	}
    }
    public int getEncoder(){
    	return winchEncoder.get();
    }
    public void resetWinch(){
   	winchEncoder.reset();
   }
    
//    
//    public void setWinch(double ticks){
//    	if(Math.abs(ticks - winchEncoder.getDistance()) < ticks){
//    		winch.set((ticks - winchEncoder.getDistance()) * 0.01);
//    	}else{
//    		winch.set(0);
//    	}
//    }
    
    
}

