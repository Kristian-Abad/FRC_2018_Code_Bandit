package org.usfirst.frc.team399.Auton;

import org.usfirst.frc.team399.Systems.Supersystem;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class CheapAndDirtySwitch extends Command{
	String gameData = DriverStation.getInstance().getGameSpecificMessage();
	Timer timer = new Timer();
	boolean runTimer = true;
	double timeout;
	public CheapAndDirtySwitch(double time){
		timeout = time;
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
		//Low abstract switch 3/12/18 (WORKING)
		if(gameData.length() > 0 ){
			
			
			if(gameData.charAt(0) == 'L'){
				if(timer.get() < 0.5){
					Supersystem.getInstance().drive.autonRunMode(0.5, 0.5);
				}else if(timer.get() < 0.73){
					Supersystem.getInstance().drive.autonRunMode(-0.5,0.5 );
				}else if(timer.get() < 1.5){
					Supersystem.getInstance().drive.autonRunMode(0.5, 0.5);
				}else{
					Supersystem.getInstance().drive.autonRunMode(0, 0);
				}
				
			}else if(gameData.charAt(0) == 'R'){
				if(timer.get() < 0.5){
					Supersystem.getInstance().drive.autonRunMode(0.5, 0.5);
				}else if(timer.get() < 0.8){
					Supersystem.getInstance().drive.autonRunMode(0.75,-0.75);
				}else if(timer.get() < 1.8){
					Supersystem.getInstance().drive.autonRunMode(0.5, 0.5);
				}else{
					Supersystem.getInstance().drive.autonRunMode(0, 0);
				}
			}

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
