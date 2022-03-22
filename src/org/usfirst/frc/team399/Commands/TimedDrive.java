package org.usfirst.frc.team399.Commands;

import org.usfirst.frc.team399.Config.Constants;
import org.usfirst.frc.team399.Systems.Supersystem;
import org.usfirst.frc.team399.Utilities.TrajectoryGenerator;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class TimedDrive extends Command{
	Timer timer = new Timer();
	double timeout,time;
	double left, right;
	
	public TimedDrive(double l_input,double r_input, double time){
		left = l_input;
		right = r_input;
		timeout = time;
	}
	
	@Override
	protected void initialize() {
		setTimeout(timeout);
		Supersystem.getInstance().drive.configureReset();
		Supersystem.getInstance().resetSensors();
		timer.stop();
		timer.reset();
		timer.start();

		setTimeout(timeout);
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		time = timer.get();

		
		Supersystem.getInstance().drive.timedStraight(left, right, false);
		// TODO Auto-generated method stub
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		Supersystem.getInstance().drive.timedStraight(0, 0, false);

		return this.isTimedOut();
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		Supersystem.getInstance().drive.timedStraight(0, 0, false);

	}

	@Override
	protected void interrupted() {

		// TODO Auto-generated method stub
		
	}
}
