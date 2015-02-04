
package org.usfirst.frc.team614.robot;

import org.usfirst.frc.team614.robot.commands.Auto;
import org.usfirst.frc.team614.robot.commands.T_CrossBow;
import org.usfirst.frc.team614.robot.commands.T_KajDrive;
import org.usfirst.frc.team614.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

    CommandGroup autonomousCommand;
    Command driveCommand;
    Command crossBowCommand;
    DriveTrain driveTrain;
    

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        // instantiate the command used for the autonomous period
        autonomousCommand = new Auto();
        driveCommand = new T_KajDrive();
        crossBowCommand = new T_CrossBow();
        driveTrain = DriveTrain.getInstance();
        
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putNumber("Gyro", driveTrain.getYaw());
	}

    public void autonomousInit() {
        // schedule the autonomous command (example)
        autonomousCommand.start();
        driveCommand.cancel();
        crossBowCommand.cancel();
        
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
    	autonomousCommand.cancel();
        driveCommand.start();
        crossBowCommand.start();
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
        
        
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
