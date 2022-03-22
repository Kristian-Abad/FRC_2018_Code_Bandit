package org.usfirst.frc.team399.Auton;

import org.usfirst.frc.team399.Systems.Supersystem;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class NewTimedStraight extends Command{
	Timer timer = new Timer();
	String gameData = DriverStation.getInstance().getGameSpecificMessage();
	double timeout = 15;
	public NewTimedStraight(){
		timer.stop();
	}
	
	@Override
	protected void initialize() {
		timer.stop();
		timer.reset();
		timer.start();
		setTimeout(timeout);
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		if(timer.get() < 1.5) {
			Supersystem.getInstance().drive.timedStraight(0.5, 0.5, false);
		}else{
			Supersystem.getInstance().drive.timedStraight(0, 0, false);
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
		
	}

	@Override
	protected void interrupted() {
		
		// TODO Auto-generated method stub
		
	}
}
