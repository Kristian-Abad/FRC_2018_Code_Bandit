package org.usfirst.frc.team399.Commands;

import org.usfirst.frc.team399.Config.Constants;
import org.usfirst.frc.team399.Systems.Supersystem;
import org.usfirst.frc.team399.Utilities.TrajectoryGenerator;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class Wait extends Command{
	double timeout;
	
	public Wait(double time){
		timeout = time;
	}
	
	@Override
	protected void initialize() {
		setTimeout(timeout);
		Supersystem.getInstance().drive.configureReset();
		Supersystem.getInstance().resetSensors();
		
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		

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
