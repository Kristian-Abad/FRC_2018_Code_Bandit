package org.usfirst.frc.team399.Auton;

import org.usfirst.frc.team399.Systems.Supersystem;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class TimedSwitch extends Command{
	double timeout = 15;
	Timer timer = new Timer();
	String gameData = DriverStation.getInstance().getGameSpecificMessage();
	public TimedSwitch(){
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
		if(gameData.length() > 0 ){
			
			if(gameData.charAt(0) == 'L'){
				if(timer.get() < 0.5){
					Supersystem.getInstance().drive.timedStraight(0.5, 0.5, false);

				}else if(timer.get() < 0.85){
					Supersystem.getInstance().drive.timedStraight(-0.5, 0.5, false);

				}else if(timer.get() < 1.9){
					Supersystem.getInstance().drive.timedStraight(0.5, 0.5, false);

				}/**else if(timer.get() < 2.15){
					Supersystem.getInstance().drive.timedStraight(0.5, -0.5, false);

				}else if(timer.get() < 2.3){
					Supersystem.getInstance().drive.timedStraight(0.5, 0.5, false);

				}**/else if(timer.get() < 2.3){
					Supersystem.getInstance().elevator.runWheels(0.7);

				}else{
					Supersystem.getInstance().drive.timedStraight(0, 0, false);
					Supersystem.getInstance().elevator.runWheels(0);

				}
				
			}else if(gameData.charAt(0) == 'R'){
				if(timer.get() < 0.5){
					Supersystem.getInstance().drive.timedStraight(0.5, 0.5, false);
					
					if(timer.get() < 0.2){
						Supersystem.getInstance().elevator.pivot(0.5);

					}else{
						
						Supersystem.getInstance().elevator.pivot(-0);
					}

				}else if(timer.get() < 0.8){
					Supersystem.getInstance().drive.timedStraight(0.5,-0.5, false);
				}else if(timer.get() < 2.1){
					Supersystem.getInstance().drive.timedStraight(0.5, 0.5, false);
				}else if(timer.get() < 2.4){
					Supersystem.getInstance().drive.timedStraight(0, 0, false);
					Supersystem.getInstance().elevator.pivot(-0.17);

				}else if(timer.get() < 2.8){
					Supersystem.getInstance().elevator.runWheels(0.7);
					
				}else{
					Supersystem.getInstance().drive.timedStraight(0, 0, false);
					Supersystem.getInstance().elevator.runWheels(0.0);

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
