package org.usfirst.frc.team399.Commands;

import org.usfirst.frc.team399.Systems.Supersystem;

import edu.wpi.first.wpilibj.command.Command;

public class PivotElevator extends Command{
	
	double timeout;
	char position;
	
	public PivotElevator(char pos, double time){
		position = pos;
		timeout = time;

	}
	@Override
	protected void initialize() {
	
		setTimeout(timeout);
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
//		Supersystem.getInstance().elevator.pivotAutonLoop(position);
		// TODO Auto-generated method stub
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
	
		return this.isTimedOut();
	}

	@Override
	protected void end() {

		// TODO Auto-generated method stub
		
	}

	@Override
	protected void interrupted() {

		// TODO Auto-generated method stub
		
	}
}
