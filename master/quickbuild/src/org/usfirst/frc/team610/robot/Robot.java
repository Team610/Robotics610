
package org.usfirst.frc.team610.robot;

import org.usfirst.frc.team610.robot.commands.ExampleCommand;
import org.usfirst.frc.team610.robot.subsystems.ExampleSubsystem;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	Joystick driver;
	Talon leftMotor;
	Talon leftMotor1;
	Talon rightMotor;
	Talon rightMotor1;
	double RightSpeed;
	double LeftSpeed;
	
	public static final ExampleSubsystem exampleSubsystem = new ExampleSubsystem();
	public static OI oi;

    Command autonomouscommand;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	driver = new Joystick(0);
    	leftMotor = new Talon(0);
    	leftMotor1 = new Talon(2);
    	rightMotor = new Talon(1);
    	rightMotor1 = new Talon(3);
    	
    	
		oi = new OI();
        // instantiate the command used for the autonomous period
        autonomouscommand = new ExampleCommand();
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {
        // schedule the autonomous command (example)
        if (autonomouscommand != null) autonomouscommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomouscommand != null) autonomouscommand.cancel();
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	double Y = driver.getRawAxis(1)*1;
    	double X = driver.getRawAxis(2)*1;
    	LeftSpeed = Y-X;
    	RightSpeed = Y+X;
    	leftMotor.set(-LeftSpeed);
    	leftMotor1.set(-LeftSpeed);
    	rightMotor.set(RightSpeed);
    	rightMotor1.set(RightSpeed);
        Scheduler.getInstance().run();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
