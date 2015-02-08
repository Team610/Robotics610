package org.usfirst.frc.team610.robot.subsystems;

import org.usfirst.frc.team610.robot.constants.ElectricalConstants;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;

/**
 *
 */
public class Elevator extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    Talon motorTalon;
    Potentiometer armPot;
    AnalogInput aiPot;
    //
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    
    }
   
    
    public Elevator(){
    	aiPot = new AnalogInput(ElectricalConstants.POT_ANALOGPORT);
    	armPot = new AnalogPotentiometer(aiPot);
    	motorTalon = new Talon(ElectricalConstants.TALON_ELEVATOR);
		}   
    /*
     * 2-3 possible positions?
     */
    public void setPos(int setting){
    	switch(setting){
    	case 1:
    		setUpPos();
    	
    	case 2:
    		setMidPos();
    	case 3:
    		setLowPos();
    	default:
    		setLowPos();
    	
    	}
    
    }
    public double getPot(){
    	return armPot.get();
    }
    public void resetPot(){
    }
    public void setUpPos(){
    	
    }
    public void setMidPos(){
    	
    }
    public void setLowPos(){
    	
    }

}
