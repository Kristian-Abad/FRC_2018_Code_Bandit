package org.usfirst.frc.team399.Commands;

import org.usfirst.frc.team399.Systems.Supersystem;

import edu.wpi.first.wpilibj.command.Command;

public class BangBangSetElevatorPosition extends Command{
	double timeout;
	char currentPosition, transitionToPosition;
	
	public BangBangSetElevatorPosition(char currentPos, char transitionToPos,double time){
		timeout = time;
		currentPosition = currentPos;
		transitionToPosition = transitionToPos;
	}
	@Override
	protected void initialize() {
	
		setTimeout(timeout);
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		Supersystem.getInstance().elevator.newElevatorAutonLoop(transitionToPosition, currentPosition);
		// TODO Auto-generated method stub
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
	
		return this.isTimedOut();
	}

	@Override
	protected void end() {
		Supersystem.getInstance().elevator.newElevatorAutonLoop('N', 'N');
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void interrupted() {
		Supersystem.getInstance().elevator.newElevatorAutonLoop('N', 'N');
		// TODO Auto-generated method stub
		
	}
}
