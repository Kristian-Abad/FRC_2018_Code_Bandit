package org.usfirst.frc.team399.Commands;

import org.usfirst.frc.team399.Systems.Supersystem;

import edu.wpi.first.wpilibj.command.Command;

public class Pivot extends Command{
	Supersystem bot = Supersystem.getInstance();
	
	double position;
	
	public Pivot(double pos){
		position = pos;
	}
	@Override
	protected void initialize() {
	//bot.elevator.pivot0(position);
		
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		bot.elevator.pivot0(position);
//		Supersystem.getInstance().elevator.pivotAutonLoop(position);
		// TODO Auto-generated method stub
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		bot.elevator.pivot0(0);
		return this.isTimedOut();
	}

	@Override
	protected void end() {
		bot.elevator.pivot0(0);
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void interrupted() {
		bot.elevator.pivot0(0);
		// TODO Auto-generated method stub
		
	}
}


