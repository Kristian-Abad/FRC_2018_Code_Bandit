package org.usfirst.frc.team399.Commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class PivotElevatorOutSetPosition extends CommandGroup{
	public PivotElevatorOutSetPosition(char currPos,char transitionPos, double transitionTime){
		this.addParallel(new PivotElevator('O',0.2));
		this.addSequential(new BangBangSetElevatorPosition(currPos, transitionPos, transitionTime));
	}
}
