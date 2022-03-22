package org.usfirst.frc.team399.Commands;

import org.usfirst.frc.team399.Config.Constants;
import org.usfirst.frc.team399.Systems.Supersystem;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class RunIntakeWheels extends Command{
	Timer timer = new Timer();
	double timeout,time, waitTime;
	double wheelsInput;
	
	public RunIntakeWheels(double input, double time, double wait){
		wait = waitTime;
		wheelsInput = input;
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

		if(time > waitTime){
			Supersystem.getInstance().elevator.runWheels(wheelsInput);
			
		}else{
			Supersystem.getInstance().elevator.runWheels(0);

		}
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
		Supersystem.getInstance().elevator.runWheels(0);

	}

	@Override
	protected void interrupted() {

		// TODO Auto-generated method stub
		
	}
}
