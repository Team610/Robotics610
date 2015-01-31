package org.usfirst.frc.team614.robot.subsystems;

import org.usfirst.frc.team614.robot.constants.ElectricalConstants;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class CrossBow extends Subsystem {
	
	DoubleSolenoid arm;
	DoubleSolenoid wing;
	
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
    }
    
    public static CrossBow getInstance(){
    	if(instance == null){
    		instance = new CrossBow();
    	}
    	return instance;
    }
    
    public void setArm(boolean up){
    	if(up == ElectricalConstants.ARM_UP) {
    		arm.set(DoubleSolenoid.Value.kForward);
    		
    	} else {
    		arm.set(DoubleSolenoid.Value.kReverse);
    		
    	}
    }
    
    public void setWing(boolean up){
//    	if(up == ElectricalConstants.ARM_UP) {
//    		wing.set(DoubleSolenoid.Value.kForward);
//    		System.out.println("Arm Forward");
//    	} else {
//    		wing.set(DoubleSolenoid.Value.kReverse);
//    		System.out.println("Arm Backward");
//    	}
    }
    
    
}
