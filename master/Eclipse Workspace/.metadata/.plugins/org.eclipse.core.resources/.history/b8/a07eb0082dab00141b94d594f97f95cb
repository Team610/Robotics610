package org.usfirst.frc.team614.robot.subsystems;

import org.usfirst.frc.team614.robot.constants.ElectricalConstants;

import com.kauailabs.nav6.frc.IMUAdvanced;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 *
 */
public class DriveTrain extends Subsystem {
	
	SerialPort serial_port;
	IMUAdvanced imu;
	Victor leftFront;
	Victor leftBack;
	Victor rightFront;
	Victor rightBack;
	Encoder leftEncoder;
	Encoder rightEncoder;
	DoubleSolenoid solenoid1;
	DoubleSolenoid solenoid2;
	
	
    static DriveTrain instance;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    private DriveTrain(){
		leftFront = new Victor(0);
		leftBack = new Victor(1);
		rightFront = new Victor(2);
		rightBack = new Victor(3);
		leftEncoder = new Encoder(0, 1, true);
		rightEncoder = new Encoder(2, 3);
		

		try {
			serial_port = new SerialPort(57600, SerialPort.Port.kMXP);

			// You can add a second parameter to modify the
			// update rate (in hz) from 4 to 100. The default is 100.
			// If you need to minimize CPU load, you can set it to a
			// lower value, as shown here, depending upon your needs.

			// You can also use the IMUAdvanced class for advanced
			// features.

			byte update_rate_hz = 50;
			// imu = new IMU(serial_port,update_rate_hz);
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
    
    public void setRight (double speed) {
    	rightFront.set(-speed);
    	rightBack.set(-speed);
    }
    public void setLeft (double speed) {
    	leftFront.set(speed);
    	leftBack.set(speed);
    }
    public double getLeftEncoderDistance(){
    	return leftEncoder.getDistance() / ElectricalConstants.CONVERSION_FACTOR;
    }
    public double getRightEncoderDistance(){
    	return rightEncoder.getDistance() / ElectricalConstants.CONVERSION_FACTOR;
    }
    public double getYaw(){
    	return imu.getYaw();
    }
    public void resetEncoder(){
    	leftEncoder.reset();
    	rightEncoder.reset();
    }
    public void resetGyro(){
    	imu.zeroYaw();
    }
    
}

