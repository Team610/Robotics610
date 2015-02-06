
package org.usfirst.frc.team610.robot;

import org.usfirst.frc.team610.robot.commands.A_ForwardBack;
import org.usfirst.frc.team610.robot.commands.D_SensorReadings;
import org.usfirst.frc.team610.robot.commands.T_KajDrive;
import org.usfirst.frc.team610.robot.commands.T_ResetSensors;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
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

	public static OI oi;

    Command kajDrive;
    Command readings;
    Command reset;
    CommandGroup auto;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
		
        // instantiate the command used for the autonomous period
        kajDrive = new T_KajDrive();
        readings = new D_SensorReadings();
        reset = new T_ResetSensors();
        auto = new A_ForwardBack();
        
    }
	
	public void disabledPeriodic() {
		kajDrive.cancel();
		readings.start();
	}

    public void autonomousInit() {
        // schedule the autonomous command (example)
        readings.cancel();
    	kajDrive.cancel();
    	auto.start();
    	
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
        Scheduler.getInstance().run();
        auto.cancel();
        readings.start();
        kajDrive.start();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}