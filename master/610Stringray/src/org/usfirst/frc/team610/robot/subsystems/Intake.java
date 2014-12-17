package org.usfirst.frc.team610.robot.subsystems;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	Talon intakeLeft;
	Talon intakeRight;
	
	static Intake instance;
	
	public static Intake getInstance(){
		if(instance == null){
			instance = new Intake();
		}
		return instance;
	}
	
	
	public Intake(){
		intakeLeft = new Talon(8);
		intakeRight = new Talon(7);
	}
	
	/*
	public void intake(int speed){
		intakeLeft.set(speed);
		intakeRight.set(speed);
	}
	*/
	
	
	public void setLeft(double speed){
		intakeLeft.set(speed);
	}
	
	public void setRight(double speed){
		intakeRight.set(speed);
	}
	
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

