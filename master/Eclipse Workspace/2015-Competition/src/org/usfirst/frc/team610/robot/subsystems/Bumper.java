package org.usfirst.frc.team610.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Bumper extends Subsystem {
	
	DoubleSolenoid arm;
	DoubleSolenoid wings;
	
	static Bumper instance;
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private Bumper(){
    	arm = new DoubleSolenoid(0,1);
    	wings = new DoubleSolenoid(6,7);
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
    
    
    
}

