package org.usfirst.frc.team610.robot.subsystems;

import org.usfirst.frc.team610.robot.constants.ElectricalConstants;

import com.kauailabs.nav6.frc.IMUAdvanced;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 *
 */
public class DriveTrain extends Subsystem {
	
	SerialPort serial_port;
	IMUAdvanced imu;
	Talon rightFront;
	Talon rightBack;
	Talon leftFront;
	Talon leftBack;
	Talon mid;
	Encoder left;
	Encoder right;
    
	static DriveTrain instance;
	
	private DriveTrain(){
		//Talons
		rightFront = new Talon(ElectricalConstants.TALON_RIGHT_FRONT);
		rightBack = new Talon(ElectricalConstants.TALON_RIGHT_BACK);
		leftFront = new Talon(ElectricalConstants.TALON_LEFT_FRONT);
		leftBack = new Talon(ElectricalConstants.TALON_LEFT_BACK);
		mid = new Talon(ElectricalConstants.TALON_MID);
		
		//Encoders
		left = new Encoder(2,3, true);
		right = new Encoder(0,1);
		
		
		//IMU navX
		try {
			serial_port = new SerialPort(57600, SerialPort.Port.kMXP);
			byte update_rate_hz = 50;
			imu = new IMUAdvanced(serial_port, update_rate_hz);
		} catch (Exception ex) {
		}
		if (imu != null) {
			LiveWindow.addSensor("IMU", "Gyro", imu);
		}
	}
	
	public static DriveTrain getInstance(){
		if(instance == null){
			instance = new DriveTrain();
		} 
		return instance;
	}
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	
	//Set left speed
	public void setLeft(double speed){
		leftFront.set(speed);
		leftBack.set(speed);
	}
	
	//Set right speed
	public void setRight(double speed){
		rightFront.set(speed);
		rightBack.set(speed);
	}
	
	//Set mid speed
	public void setMid(double speed){
		mid.set(speed);
	}
	
	
	//Gyro
	public double getYaw(){
		return imu.getYaw();
	}
	
	//Zero Gyro
	public void zeroYaw(){
		imu.zeroYaw();
	}
	
	//Get Average Encoder
	public double getAvgDistance(){
		double average, a, b;
		a = left.getDistance() / ElectricalConstants.ENCODER_INCHES ;
		b = right.getDistance() / ElectricalConstants.ENCODER_INCHES;
		average = (a + b) / 2;
		return average;
	}
	
	//Get Right & Left Encoders
	public double getRightDistance(){
		return right.getDistance() / ElectricalConstants.ENCODER_INCHES;
	}
	public double getLeftDistance(){
		return left.getDistance() / ElectricalConstants.ENCODER_INCHES;
	}
	
	//Reset encoders
	public void resetEncoders(){
		right.reset();
		left.reset();
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}
