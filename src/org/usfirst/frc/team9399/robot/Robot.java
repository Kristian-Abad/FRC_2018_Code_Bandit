package org.usfirst.frc.team9399.robot;

import org.usfirst.frc.team399.Auton.CheapAndDirtyBaseline;
import org.usfirst.frc.team399.Auton.CheapAndDirtySwitch;
import org.usfirst.frc.team399.Auton.DoNothing;
import org.usfirst.frc.team399.Auton.LinearScaleLeft;
import org.usfirst.frc.team399.Auton.LinearScaleRight;
import org.usfirst.frc.team399.Auton.NewTimedStraight;
import org.usfirst.frc.team399.Auton.ScaleClose;
import org.usfirst.frc.team399.Auton.Switch;
import org.usfirst.frc.team399.Auton.TCS;
import org.usfirst.frc.team399.Auton.TestPrint1;
import org.usfirst.frc.team399.Auton.TestPrint2;
import org.usfirst.frc.team399.Auton.ThreeCubeSwitch;
import org.usfirst.frc.team399.Auton.TimedSwitch;
import org.usfirst.frc.team399.Auton.TwoCubeSwitch;
import org.usfirst.frc.team399.Commands.MPStraight;
import org.usfirst.frc.team399.Commands.MPStraightAndControlSystems;
import org.usfirst.frc.team399.Commands.TestMPStraight;
import org.usfirst.frc.team399.Commands.TestPrint;
import org.usfirst.frc.team399.Commands.TestPrintSecondary;
import org.usfirst.frc.team399.Commands.TimedStraight;
import org.usfirst.frc.team399.Config.Constants;
import org.usfirst.frc.team399.Systems.Drivetrain;
import org.usfirst.frc.team399.Systems.Supersystem;
import org.usfirst.frc.team399.Utilities.ButtonPositionChooser;
import org.usfirst.frc.team399.Utilities.MPController;
import org.usfirst.frc.team399.Utilities.MultiToggler;
import org.usfirst.frc.team399.Utilities.PIDController;
import org.usfirst.frc.team399.Utilities.Toggler;
import org.usfirst.frc.team399.Utilities.TrajectoryGenerator;

import com.ctre.phoenix.motion.SetValueMotionProfile;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;


import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;


public class Robot extends IterativeRobot {
	TrajectoryGenerator generator = new TrajectoryGenerator(Constants.Subsystems.Drivetrain.Max_Velocity,Constants.Subsystems.Drivetrain.Max_Acceleration);
//	double[][] testProfile = generator.generateTrajectoryForTurning(Constants.Wheel_Constants.distance_Between_Wheels, -90);
	
	Supersystem Bandit = Supersystem.getInstance();
	ButtonPositionChooser positionChooser = new ButtonPositionChooser();
	Timer timer = new Timer();
	char pos, gameDataChar;	
	Command autoSelected;
	SendableChooser desiredChooser = new SendableChooser();
	SendableChooser defaultChooser = new SendableChooser();
	SendableChooser testChooser = new SendableChooser();
	private SendableChooser<String> stringChooser = new SendableChooser<>();

	String gameData;
	boolean runTimer = true;
	boolean buttonPressed = false;
	double time;
	

	private String selectedString;
	

	
	@Override
	public void robotInit() {
		
		timer.stop();
		
//		desiredChooser.addObject("Test", new Test());
//		desiredChooser.addObject("Test Print 1", new TestPrint1());
		
		//These
//		desiredChooser.addDefault("Default", new TimedStraightAuto());
//		desiredChooser.addObject("Timed Straight", new TimedStraightAuto());
//		desiredChooser.addObject("Baseline", new Baseline());
//		desiredChooser.addObject("Switch", new Switch());
		//
		
//		desiredChooser.addObject("Linear Scale Left", new LinearScaleLeft());
//		desiredChooser.addObject("Linear Scale Right", new LinearScaleRight());
		
//		defaultChooser.addObject("Test Print 2", new TestPrint2());
		
		//These
//		defaultChooser.addDefault("Default", new TimedStraightAuto());
//		defaultChooser.addObject("Timed Straight", new TimedStraightAuto());
//		defaultChooser.addObject("Baseline", new Baseline());
//		defaultChooser.addObject("Switch", new Switch());
		
//		testChooser.addDefault("Do Nothing", new DoNothing());
//		testChooser.addDefault("Scale Left", new LinearScaleLeft());
//		testChooser.addObject("CheapAndDirtySwitch", new CheapAndDirtySwitch(15.0));
//		testChooser.addObject("MPStraight Test", new TestMPStraight());
//		testChooser.addObject("Switch", new Switch());
//		testChooser.addObject("2 Cube Switch", new TCS());
//		testChooser.addDefault("Test Print", new TestPrint2());
		
//		
//		testChooser.addDefault("Do Nothing", new DoNothing());
//		testChooser.addObject("Timed Straight", new NewTimedStraight());		
//		testChooser.addObject("Switch", new TimedSwitch());

//		SmartDashboard.putData("Auto choices", desiredChooser);
//		SmartDashboard.putData("Stand-in", defaultChooser);
		
		//Selectable auto code
//		stringChooser.addObject("2 Cube Switch", Constants.AutoStrings.kTCS);
//		stringChooser.addObject("1 Cube Switch", Constants.AutoStrings.kSwitch);
//		stringChooser.addObject("Cheap and Dirty Baseline", Constants.AutoStrings.kCADB);
//		stringChooser.addObject("Do Nothing", Constants.AutoStrings.kDoNothing);
//		stringChooser.addObject("3 Cube Switch", Constants.AutoStrings.k3CS);
//		SmartDashboard.putData("Autonomous",stringChooser);
//		SmartDashboard.putData("Autonomous", testChooser);
		

		CameraServer.getInstance().startAutomaticCapture();
	}
	
	

	@Override
	public void autonomousInit() {
		gameData = DriverStation.getInstance().getGameSpecificMessage();		
		timer.stop();
		timer.reset();
		timer.start();
		
		Supersystem.getInstance().resetSensors();
		
		//selectedString = stringChooser.getSelected();
		
//		if(selectedString != null){
//			switch(selectedString){
//			case Constants.AutoStrings.kTCS:
//				testChooser.addDefault("TCS",  new TwoCubeSwitch());
//				break;
//			case Constants.AutoStrings.kCADB:
//				testChooser.addDefault("Cheap and Dirty Baseline",  new CheapAndDirtyBaseline());
//				break;
//			case Constants.AutoStrings.kSwitch:
//				testChooser.addDefault("Switch",  new Switch());
//				break;
//			case Constants.AutoStrings.kDoNothing:
//				testChooser.addDefault("Do Nothing",  new DoNothing());
//				break;
//			case Constants.AutoStrings.k3CS:
//				testChooser.addDefault("3CS", new ThreeCubeSwitch());
//				break;
//			default:
//				testChooser.addDefault("Do Nothing", new DoNothing());
//				break;
//			}
//			autoSelected = (Command) testChooser.getSelected();
//			autoSelected.start();
//			System.out.println("Auto:" + autoSelected);
//
//		}
		
//		testChooser.addDefault("2 Cube Switch", new TwoCubeSwitch());
		testChooser.addDefault("2 Cube Switch", new ScaleClose());

		autoSelected = (Command) testChooser.getSelected();
		if(autoSelected != null){
			autoSelected.start();
			System.out.println("Auto:" + autoSelected);
		}
		
		
		
		
	}

	@Override
	public void autonomousPeriodic() {
		//Timed straight (tests needed just for pivot)
//		if(timer.get() < 1.5) {
//			Bandit.drive.timedStraight(0.5, 0.5, false);
//		}else{
//			Bandit.drive.timedStraight(0, 0, false);
//		}
		Scheduler.getInstance().run();
	}
		
	
	
	public void disabledPeriodic() {
		timer.stop();
		pos = positionChooser.getPosition();
		Supersystem.getInstance().resetSensors();

//		System.out.println("Field Position: " + pos);
	}
	
	public void disabledInit(){
		
	}
	
	public void teleopInit(){
		//Remember to add reset method here
		Bandit.resetSensors();
		timer.stop();
		timer.reset();
		timer.start();
	}
	
	@Override
	public void teleopPeriodic() {
		Bandit.drive.printEncoderPulses();
//		Bandit.drive.setTime(timer.get());
//		Bandit.drive.printVelocity();
//		System.out.println(Bandit.drive.getAverageDisplacement());
		Bandit.runTeleop();
//		System.out.println(Bandit.controls.GamepadRightJoystick());
		//System.out.println(Bandit.controls.DPad());
	}
		
	

	public void testInit(){
		
		
	}
	
	@Override
	public void testPeriodic() {
		
		
	}
}

