package org.usfirst.frc.team399.Auton;

import org.usfirst.frc.team399.Commands.MPStraight;
import org.usfirst.frc.team399.Commands.MPStraightAndControlSystems;
import org.usfirst.frc.team399.Commands.MPTurn;
import org.usfirst.frc.team399.Commands.PivotElevatorOutSetPosition;
import org.usfirst.frc.team399.Commands.RunElevatorWheels;
import org.usfirst.frc.team399.Commands.SetElevatorPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LinearScaleRight extends CommandGroup{
	public LinearScaleRight(){
		//Make this a parallel later to bring the elevator to max extension
//		this.addParallel(new PivotElevatorOutSetPosition('0','1', 1.0));
//		this.addSequential(new MPStraight(50.781, false, 0.0));
//		this.addSequential(new MPTurn(-90,0.0));
//		this.addSequential(new RunElevatorWheels('O', 1.0));
		
		this.addSequential(new MPStraightAndControlSystems(48.0, 
				   										   false,
				   										   'N',
				   										   'N',
				   										   0.0,
				   										   'O',
				   										   5.0,
				   										   0.001));
	}
}
