package org.usfirst.frc.team399.Commands;

import org.usfirst.frc.team399.Config.Constants;
import org.usfirst.frc.team399.Systems.Supersystem;
import org.usfirst.frc.team399.Utilities.TrajectoryGenerator;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class MotionProfileTurn extends Command{
	double timeout,time;
	int desiredAngle;
	Timer timer = new Timer();
	TrajectoryGenerator generator = new TrajectoryGenerator(Constants.Subsystems.Drivetrain.Max_Velocity,
															Constants.Subsystems.Drivetrain.Max_Acceleration);
	double[][] profile;
	
	public MotionProfileTurn(double dAngle, double time){
		desiredAngle = (int) dAngle;
		timeout = time;
		profile = generator.generateTrajectoryForTurning(Constants.Wheel_Constants.Distance_Between_Wheels, dAngle);
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

		
		Supersystem.getInstance().drive.runMotionProfile(profile, desiredAngle, true, time);
		//System.out.println(Supersystem.getInstance().drive.getAngle() + "\t\t" + time);
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
		System.out.println(Supersystem.getInstance().drive.getAngle() + "\t\t" + time);

	}

	@Override
	protected void interrupted() {

		// TODO Auto-generated method stub
		
	}
}
