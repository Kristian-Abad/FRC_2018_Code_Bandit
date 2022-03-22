package org.usfirst.frc.team399.Commands;

import org.usfirst.frc.team399.Config.Constants;
import org.usfirst.frc.team399.Systems.Supersystem;
import org.usfirst.frc.team399.Utilities.MPController;
import org.usfirst.frc.team399.Utilities.TrajectoryGenerator;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class MPStraight extends Command{
	Timer timer = new Timer();
	TrajectoryGenerator generator = new TrajectoryGenerator(Constants.Subsystems.Drivetrain.Max_Velocity,
			Constants.Subsystems.Drivetrain.Max_Acceleration);
//	MPController controller;
	
	private double timeout;
	double[][] profile;
	double leftOutput, rightOutput, time, wheelsOutput;
	char goPosition, currentPosition;
//	boolean runTimer = true;
	
	public MPStraight(double disp, boolean invert, char goToPosition, char currPosition, double intakeWheelPower, double time){
		timeout = time;
		profile = generator.generateTrajectory(disp, invert, 0);
		goPosition = goToPosition;
		currentPosition = currPosition;
		wheelsOutput = intakeWheelPower;
//		controller = new MPController(profile,0,false);
	}
	@Override
	protected void initialize() {
		timer.stop();
		setTimeout(timeout);
		Supersystem.getInstance().drive.configureReset();
		timer.reset();
		timer.start();
		
		System.out.println("Init Print fron MPStraight");

	}

	@Override
	protected void execute() {
		time = timer.get();
		System.out.println("Execute Print fron MPStraight");
		Supersystem.getInstance().drive.runMotionProfile(profile, 0, false, time);
		Supersystem.getInstance().elevator.newElevatorAutonLoop(goPosition, currentPosition);
		Supersystem.getInstance().elevator.runWheels(wheelsOutput);

		
		
	}

	@Override
	protected boolean isFinished() {
		
		Supersystem.getInstance().drive.autonRunMode(0.0, 0.0);
		Supersystem.getInstance().elevator.newElevatorAutonLoop('N', 'N');
		Supersystem.getInstance().elevator.runWheels(0.0);
		return this.isTimedOut();
	}

	@Override
	protected void end() {
		
		Supersystem.getInstance().drive.autonRunMode(0.0, 0.0);
		Supersystem.getInstance().elevator.newElevatorAutonLoop('N', 'N');
		Supersystem.getInstance().elevator.runWheels(0.0);
		
	}

	@Override
	protected void interrupted() {
		Supersystem.getInstance().drive.autonRunMode(0.0, 0.0);
		Supersystem.getInstance().elevator.newElevatorAutonLoop('N', 'N');
		Supersystem.getInstance().elevator.runWheels(0.0);
		// TODO Auto-generated method stub
		
	}
}
