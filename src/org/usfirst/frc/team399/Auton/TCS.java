package org.usfirst.frc.team399.Auton;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

public class TCS extends Command{
	TwoCubeSwitch tcs,tcs2;
	boolean once = true;
	
	public TCS(){
		
	}
	
	public void initialize(){
		setTimeout(15);
		
	}
	
	public void execute(){
//		tcs.setData();
//		tcs = new TwoCubeSwitch(DriverStation.getInstance().getGameSpecificMessage());
//		tcs.start();
//		
//		tcs2 = new TwoCubeSwitch();
//		if(once){
//
//			once = false;
//		}
		

		
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
