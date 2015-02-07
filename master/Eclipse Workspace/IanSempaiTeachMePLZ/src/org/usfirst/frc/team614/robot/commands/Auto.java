package org.usfirst.frc.team614.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Auto extends CommandGroup {
    
    public  Auto() {
    	
    	addSequential(new A_ResetGyro());
    	addParallel(new A_CrossBow());
    	addSequential(new A_DriveForward(51, .50));
    	addParallel(new A_DriveForward(0,1));
    	
    	addSequential(new A_DriveForward(-140, .125));
    	addParallel((new A_CrossBowUp()));
        addSequential((new A_WinchUp(2200)));
    	
    	addSequential(new A_DriveForward(0, 1));
    	addSequential(new A_WingsDown());
    	
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
