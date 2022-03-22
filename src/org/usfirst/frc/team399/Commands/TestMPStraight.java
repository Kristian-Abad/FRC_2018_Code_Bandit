package org.usfirst.frc.team399.Commands;

import org.usfirst.frc.team399.Config.Constants;
import org.usfirst.frc.team399.Systems.Supersystem;
import org.usfirst.frc.team399.Utilities.TrajectoryGenerator;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class TestMPStraight extends Command{
	double timeout,time;
	Timer timer = new Timer();
	TrajectoryGenerator generator = new TrajectoryGenerator(Constants.Subsystems.Drivetrain.Max_Velocity,
															Constants.Subsystems.Drivetrain.Max_Acceleration);
	double[][] firstStraight = generator.generateTrajectory(36, false,0);
	
	public TestMPStraight(/**double time**/){
		timeout = 15;
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
//		while (time < 15){
//			System.out.println("Execute Print fron MPStraight");
//			
//			if(time < 5.0){
//				Supersystem.getInstance().drive.runMotionProfile(firstStraight, 0, false, time);
//				
//			}else{
//				Supersystem.getInstance().drive.autonRunMode(0, 0);
//			}
//			
//		}
		
		Supersystem.getInstance().drive.runMotionProfile(firstStraight, 0, false, time);
//		if(time < 5.0){
//		
//		}else{
//			Supersystem.getInstance().drive.autonRunMode(0, 0);
//		}
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
