package org.usfirst.frc.team399.Commands;

import org.usfirst.frc.team399.Systems.Supersystem;

import edu.wpi.first.wpilibj.command.Command;

public class TimedStraight extends Command{
	double timeout;
	double output;
	
	public TimedStraight(double pow, double time){
		timeout = time;
		output = pow;
	}
	@Override
	protected void initialize() {
		
		setTimeout(timeout);
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		Supersystem.getInstance().drive.autonRunMode(output, output);
		// TODO Auto-generated method stub
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return this.isTimedOut();
	}

	@Override
	protected void end() {
		Supersystem.getInstance().drive.autonRunMode(0.0, 0.0);
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void interrupted() {
		Supersystem.getInstance().drive.autonRunMode(0.0, 0.0);
		// TODO Auto-generated method stub
		
	}
}
