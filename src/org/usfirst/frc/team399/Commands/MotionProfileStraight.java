package org.usfirst.frc.team399.Commands;

import org.usfirst.frc.team399.Config.Constants;
import org.usfirst.frc.team399.Systems.Supersystem;
import org.usfirst.frc.team399.Utilities.TrajectoryGenerator;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class MotionProfileStraight extends Command{
	double timeout,time;
	Timer timer = new Timer();
	TrajectoryGenerator generator = new TrajectoryGenerator(Constants.Subsystems.Drivetrain.Max_Velocity,
															Constants.Subsystems.Drivetrain.Max_Acceleration);
	double[][] profile;
	
	public MotionProfileStraight(double disp, boolean invertInput, double time){
		timeout = time;
		profile = generator.generateTrajectory(disp, invertInput,0);
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

		
		Supersystem.getInstance().drive.runMotionProfile(profile, 0, false, time);

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
		Supersystem.getInstance().drive.timedStraight(0.0, 0.0, false);

	}

	@Override
	protected void interrupted() {

		// TODO Auto-generated method stub
		
	}
}
