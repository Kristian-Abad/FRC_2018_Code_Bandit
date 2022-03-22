package org.usfirst.frc.team399.Commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class MPStraightAndControlSystems extends CommandGroup{
	public MPStraightAndControlSystems(double disp, 
									  boolean invert, 
									  char goToPosition, 
									  char currPosition, 
									  double intakeWheelPower,
									  char pivotPosition,
									  double driveTime,
									  double pivotTime){
		this.addParallel(new MPStraight(disp, invert, goToPosition, currPosition, intakeWheelPower, driveTime));
		this.addSequential(new PivotElevator(pivotPosition, pivotTime));
	}
}
