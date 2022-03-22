package org.usfirst.frc.team399.Commands;

import org.usfirst.frc.team399.Config.Constants;
import org.usfirst.frc.team399.Systems.Supersystem;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class PivotIntake extends Command{
	Timer timer = new Timer();
	WaitCommand wait;
	double timeout,time;
	double waitTime;
	double pivotInput;
	
	public PivotIntake(double input, double time, double wait){
		waitTime = wait;
		pivotInput = input;
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
			Supersystem.getInstance().elevator.pivot(pivotInput);

		}else{
			Supersystem.getInstance().elevator.pivot(Constants.Subsystems.Elevator.k_Pivot_Hold_Input);

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
		Supersystem.getInstance().elevator.pivot(Constants.Subsystems.Elevator.k_Pivot_Hold_Input);

	}

	@Override
	protected void interrupted() {

		// TODO Auto-generated method stub
		
	}
}
