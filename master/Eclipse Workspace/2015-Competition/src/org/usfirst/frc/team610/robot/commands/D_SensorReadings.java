package org.usfirst.frc.team610.robot.commands;

import org.usfirst.frc.team610.robot.subsystems.DriveTrain;
import org.usfirst.frc.team610.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class D_SensorReadings extends Command {

	DriveTrain driveTrain;
	PowerDistributionPanel pdp;
	
	Elevator elevator;
	
    public D_SensorReadings() {
    	driveTrain = DriveTrain.getInstance();
    	elevator =  Elevator.getInstance();
    	pdp = new PowerDistributionPanel();
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	System.out.println("Gyro: " + driveTrain.getYaw());
//    	System.out.println("Right Distance: " + driveTrain.getRightDistance());
//    	System.out.println("Left Distance: " + driveTrain.getLeftDistance());
//    	
//    	System.out.println("Pot" + elevator.getPot());
    	
    	SmartDashboard.putNumber("Right Distance", driveTrain.getRightDistance());
		SmartDashboard.putNumber("Left Distance", driveTrain.getLeftDistance());
		SmartDashboard.putNumber("Gyro", driveTrain.getYaw());
		//System.out.println(pdp.getCurrent(2));
		SmartDashboard.putNumber("Current Draw of Elevator", pdp.getCurrent(2));
		
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
