/*
 * Jack Williamson
 * December 1, 2014
 * ICS4U1-1
 * Drivetrain
 * 
 */

package org.usfirst.frc.team610.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drivetrain extends Subsystem {
    
	// Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	//declare the motor controllers (talons) and encoders
	Talon leftFront, leftMid, leftBack, rightFront, rightMid, rightBack;
	Encoder leftEnc, rightEnc;
	
	//declare a single instance for the drivetrain
	static Drivetrain instance;
	
	
	//returns the only instance of the drivetrain
	public static Drivetrain getInstance(){
		//if haven't made a drivetrain yet, make one
		if(instance == null){
			instance = new Drivetrain();
		}
		return instance;
	}
	
	//when creating the drivetrain instance, set up motor controllers and the encoders
	public Drivetrain(){
		
		//set up the motor controllers
		leftFront = new Talon(1);
		leftMid = new Talon(2);
		leftBack = new Talon(3);
		rightFront = new Talon(4);
		rightMid = new Talon(5);
		rightBack = new Talon(6);
		
		//set up the encoders
		leftEnc = new Encoder(2,1);
		rightEnc = new Encoder(4,3);
		//reversing the right encoder so it counts the right way
		rightEnc.setReverseDirection(true);
	}

	//organize the motor controllers so entire left side of the drivetrain can easily be controlled
	public void setLeft(double speed){
		leftFront.set(speed);
		leftMid.set(speed);
		leftBack.set(speed);
	}
	//organize the motor controllers so entire left side of the drivetrain can easily be controlled
	public void setRight(double speed){
		rightFront.set(speed);
		rightMid.set(speed);
		rightBack.set(speed);
	}
	
	//method to convert the readings on the encoders to the more workable measurements of inches
	private double toInches(int encCount){
		return ((int) (encCount/10.24* Math.PI *6+.5)) /100.0 * 3.8;
	}
	
	//returns the reading on the left encoder in inches
	public double getEncLeft(){
		return toInches(leftEnc.get());
	}
	
	//returns the reading on the right encoder in inches
	public double getEncRight(){
		return toInches(rightEnc.get());
	}
	
	//resets both encoders to zero
	public void resetEncoders(){
		leftEnc.reset();
		rightEnc.reset();
	}
	
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

