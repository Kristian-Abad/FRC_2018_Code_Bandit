package org.usfirst.frc.team399.Commands;

import org.usfirst.frc.team399.Config.Constants;
import org.usfirst.frc.team399.Systems.Supersystem;
import org.usfirst.frc.team399.Utilities.TrajectoryGenerator;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class MPTurn extends Command{
	Timer timer = new Timer();
	TrajectoryGenerator generator = new TrajectoryGenerator(Constants.Subsystems.Drivetrain.Max_Velocity, 
														    Constants.Subsystems.Drivetrain.Max_Acceleration);
	
	double[][] profile;
	double timeout, wheelsOutput, time;
	boolean runTimer = true;
	int desiredAngle;
	char goPosition, currentPosition;
	
	public MPTurn(int angle, char goToPosition, char currPosition, double intakeWheelPower,double time){
		timeout = time;
		desiredAngle = angle;
		goPosition = goToPosition;
		currentPosition = currPosition;
		wheelsOutput = intakeWheelPower;
		profile = generator.generateTrajectoryForTurning(Constants.Wheel_Constants.Distance_Between_Wheels, angle);
	}
	
	@Override
	protected void initialize() {
		timer.stop();
		setTimeout(timeout);
		Supersystem.getInstance().drive.configureReset();

		
	}

	@Override
	protected void execute() {
		time = timer.get();
		Supersystem.getInstance().drive.runMotionProfile(profile, desiredAngle, true, time);
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
		
	}
}
