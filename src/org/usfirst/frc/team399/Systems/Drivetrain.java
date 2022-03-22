package org.usfirst.frc.team399.Systems;

import java.util.Timer;
import java.util.TimerTask;

import org.usfirst.frc.team399.Config.Constants;
import org.usfirst.frc.team399.Utilities.MPController;
import org.usfirst.frc.team399.Utilities.Toggler;



import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.WaitCommand;

import com.ctre.phoenix.motion.TrajectoryPoint;
//import com.ctre.phoenix.motion.TrajectoryPoint.TrajectoryDuration;
import com.ctre.phoenix.motion.MotionProfileStatus;

public class Drivetrain {
	
	double lastTime, lastAngle, positiveAngle, newAngle, scaledAngle;
	double lastErrorForAngleCompensation = 0;
	double lastTimeForAngleCompensation = 0;
	double errorIntegrationForAngleCompensation = 0;
	boolean loop = false;
	static boolean configureController = true;
	boolean autonRunOnce = true;
	boolean teleopRunOnce = true;
	double time;
	MPController mpcontroller = new MPController();
	Timer timer = new Timer();
	public TalonSRX left_1;
	public TalonSRX left_2;
	public TalonSRX right_1;
	public TalonSRX right_2;
	
	public AHRS navx = new AHRS(SPI.Port.kMXP);
	Encoder leftEncoder = new Encoder(Constants.Ports.Drivetrain.Left_Encoder_A,Constants.Ports.Drivetrain.Left_Encoder_B);
	Encoder rightEncoder = new Encoder(Constants.Ports.Drivetrain.Right_Encoder_A,Constants.Ports.Drivetrain.Right_Encoder_B);

	
	

	/**
	 * -Averagedisplacement method returns only one encoder value since we are only using one
	 * 
	 * **/
	public Drivetrain(int l1, int l2, int r1, int r2){
		left_1 = new TalonSRX(l1);
		left_2 = new TalonSRX(l2);
		right_1 = new TalonSRX(r1);
		right_2 = new TalonSRX(r2);
		
		left_1.setNeutralMode(NeutralMode.Brake);
		left_2.setNeutralMode(NeutralMode.Brake);
		right_1.setNeutralMode(NeutralMode.Brake);
		right_2.setNeutralMode(NeutralMode.Brake);
		
		
		left_2.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		right_2.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		
		left_2.setSensorPhase(false);
		right_2.setSensorPhase(true);
		
		left_1.enableVoltageCompensation(true);
		left_2.enableVoltageCompensation(true);
		right_1.enableVoltageCompensation(true);
		right_2.enableVoltageCompensation(true);
		
		left_1.configVoltageCompSaturation(Constants.Subsystems.Drivetrain.Voltage_Compensation, 10);
		left_2.configVoltageCompSaturation(Constants.Subsystems.Drivetrain.Voltage_Compensation, 10);
		right_1.configVoltageCompSaturation(Constants.Subsystems.Drivetrain.Voltage_Compensation, 10);
		right_2.configVoltageCompSaturation(Constants.Subsystems.Drivetrain.Voltage_Compensation, 10);
		
		leftEncoder.setDistancePerPulse(1);
		rightEncoder.setDistancePerPulse(1);
		rightEncoder.setReverseDirection(true);
		//		right_1.setSelectedSensorPosition(0, 0, 0);
//		left_2.setSelectedSensorPosition(0, 0, 0);
//		left_1.enableCurrentLimit(true);
//	    left_2.enableCurrentLimit(true);
//	    right_1.enableCurrentLimit(true);
//	    right_2.enableCurrentLimit(true);
//	    
//	    left_1.configPeakCurrentLimit(18, 10);
//	    left_2.configPeakCurrentLimit(18, 10);
//	    right_1.configPeakCurrentLimit(18, 10);
//	    left_1.configPeakCurrentLimit(18, 10);
//	    
//	    left_1.configPeakCurrentDuration(10, 10);
//	    left_2.configPeakCurrentDuration(10, 10);
//	    right_1.configPeakCurrentDuration(10, 10);
//	    right_2.configPeakCurrentDuration(10, 10);
//	    
//	    left_1.configContinuousCurrentLimit(18, 10);
//	    left_2.configContinuousCurrentLimit(18, 10);
//	    right_1.configContinuousCurrentLimit(18, 10);
//	    right_2.configContinuousCurrentLimit(18, 10);
	}
	
	public double getAngle(){
		return navx.getAngle();
	}
	
	public void runLeft1(double pow){
		left_1.set(ControlMode.PercentOutput, pow);
	}
	
	public void runLeft2(double pow){
		left_2.set(ControlMode.PercentOutput, pow);
	}
	
	public void runRight1(double pow){
		right_1.set(ControlMode.PercentOutput, pow);
	}
	
	public void runRight2(double pow){
		right_2.set(ControlMode.PercentOutput, pow);
	}
	
	public TalonSRX getLeft1() {
		return left_1;
	}
	
	public TalonSRX getLeft2() {
		return left_2;
	}
	
	public TalonSRX getRight1() {
		return right_1;
	}
	
	public TalonSRX getRight2() {
		return right_2;
	}
	
	public void runMotionProfile(double[][] motionProfile, int desiredAngle, boolean degreeMode, double currentTime){
		time = currentTime;
		if (configureController){
			mpcontroller.configureNewProfile(motionProfile, desiredAngle, degreeMode);
			configureController = false;
		}
		
		double[] outputs = mpcontroller.getDrivetrainOutputs(currentTime);
		double leftOutput = outputs[0];
		double rightOutput = outputs[1];
		//System.out.println(leftOutput + "\t\t\t" + rightOutput);
		//used to be autonRunMode
		timedStraight(leftOutput,rightOutput,false);
	}
	
	public void configureReset(){
		configureController = true;
	}
	
	public void autonRunMode(double lpow, double rpow){
		if(autonRunOnce){
			left_1.setInverted(false);
			left_2.setInverted(false);
			right_1.setInverted(true);
			right_2.setInverted(true);
			
			autonRunOnce = false;
		}
		
		double scaledAngle = navx.getAngle() * 0.06;
		
		left_1.set(ControlMode.PercentOutput, lpow - scaledAngle);
		left_2.set(ControlMode.PercentOutput,lpow - scaledAngle);
		right_1.set(ControlMode.PercentOutput,rpow + scaledAngle);
		right_2.set(ControlMode.PercentOutput,rpow + scaledAngle);
	}
	
	
	public void timedStraight(double lpow, double rpow, boolean compensate){
		if(autonRunOnce){
			left_1.setInverted(false);
			left_2.setInverted(false);
			right_1.setInverted(true);
			right_2.setInverted(true);
			
			autonRunOnce = false;
		}
		//double angle = Math.abs(navx.getAngle());
//		scaledAngle = Math.abs(navx.getAngle()) * 0.01;
		positiveAngle = (navx.getAngle() < 0 ? -1 * navx.getAngle() : navx.getAngle());
		newAngle = (positiveAngle < 0.09 ? 0 : positiveAngle);
		scaledAngle = newAngle * 0.06;
		

//		double outputl = (lpow < 0 && navx.getAngle() > 0 && correct ? lpow + (-1 * angle * 0.15) : lpow);
//		double outputr = (rpow < 0 && navx.getAngle() > 0 && correct ? rpow + (angle * 0.15) : rpow);
		
//		System.out.println(outputl + "\t\t\t" + outputr);

		if(compensate){
			left_1.set(ControlMode.PercentOutput, lpow + scaledAngle);
			left_2.set(ControlMode.PercentOutput, lpow + scaledAngle);
			right_1.set(ControlMode.PercentOutput, rpow - scaledAngle);
			right_2.set(ControlMode.PercentOutput, rpow - scaledAngle);
			
		}else {
			left_1.set(ControlMode.PercentOutput, lpow);
			left_2.set(ControlMode.PercentOutput, lpow);
			right_1.set(ControlMode.PercentOutput, rpow);
			right_2.set(ControlMode.PercentOutput, rpow);
		}
		
	}
	public void teleopOpenLoop(double leftpow, double rightpow){
		
		if(teleopRunOnce){

			teleopRunOnce = false;
		}
		left_1.setInverted(true);
		left_2.setInverted(true);
		right_1.setInverted(false);
		right_2.setInverted(false);
		
		left_1.setNeutralMode(NeutralMode.Brake);
		left_2.setNeutralMode(NeutralMode.Brake);
		right_1.setNeutralMode(NeutralMode.Brake);
		right_2.setNeutralMode(NeutralMode.Brake);
		
		left_1.set(ControlMode.PercentOutput, leftpow);
		left_2.set(ControlMode.PercentOutput,leftpow);
		right_1.set(ControlMode.PercentOutput,rightpow);
		right_2.set(ControlMode.PercentOutput,rightpow);
		
	    
	
	}
		
	public void runMotorsInAmps(double amp1,double amp2){
		left_1.set(ControlMode.Current, amp1);
		left_2.set(ControlMode.Current, amp1);
		right_1.set(ControlMode.Current, amp2);
		right_2.set(ControlMode.Current, amp2);
	}
	
	public void printMotorOutputCurrent(){
		System.out.printf("%-10f%-10f%-10f%-10f\n",left_1.getOutputCurrent(),
											   	   left_2.getOutputCurrent(),
											       right_1.getOutputCurrent(),
											       right_2.getOutputCurrent());
	}
	
	public void printMotorOutputVoltage(){		
		System.out.printf("%-10f%-10f%-10f%-10f", left_1.getMotorOutputVoltage(),
												  left_2.getMotorOutputVoltage(),
												  right_1.getMotorOutputVoltage(),
												  right_2.getMotorOutputVoltage());
	}
	
	
	public void printPosition(){
		System.out.println(getLeftPosition() + "           " + getRightPosition());
	}
	
	public void printVelocity(){
		
		// Input + (input/0.7 (max input in denom) * 0.07 or our (differenceAtMaxInput) )
		double test = 0.3 * (0.07/0.7);
		
		if(time < 5.0){
			timedStraight(compensateAndGetNewInput(0.3) + getAngleCompensation('L',time),0.3 + getAngleCompensation('R',time), false);

		}else{
			timedStraight(0.08 * navx.getAngle() * -1, 0.08 * navx.getAngle(),false);

		}
		
		//Original low abstraction code
//		if(time < 1.5){
//			timedStraight(compensateAndGetNewInput(0.5) - (0.08 * navx.getAngle()),0.5 + (0.08 * navx.getAngle()),false);
//
//		}else{
//			timedStraight(0.08 * navx.getAngle() * -1, 0.08 * navx.getAngle(),false);
//
//		}

//		System.out.println("Left: " + getLeftVelocity() + " || Right: " + getRightVelocity() + " || Time: " + time + "  || Angle: " + navx.getAngle());
	}
	
	public void printEncoderPulses(){
		System.out.println(leftEncoder.getDistance() + "\t\t" + rightEncoder.getDistance() + "\t\t" + getAverageEncoderPulses());

	}
	
	public double getAverageEncoderPulses(){
		return ((leftEncoder.getDistance() + rightEncoder.getDistance()) * 0.5);
	}
	
	public double getLeftRawUnits(){
		return left_2.getSelectedSensorPosition(0);
		
	}
	
	public double getRightRawUnits(){
		return right_2.getSelectedSensorPosition(0);
	}
	
	public double getLeftRotations(){
		return left_2.getSelectedSensorPosition(0)/4096;
	}
	
	public double getRightRotations(){
		return right_2.getSelectedSensorPosition(0)/4096;
	}
	
	public double getLeftPosition(){
		//double left_in = getLeftRotations() * Constants.Wheel_Constants.Circumference_of_Wheel;
		double left_in = leftEncoder.getDistance() * Constants.Wheel_Constants.Inches_Per_Pulse;
		return left_in;
	}
	
	public double getRightPosition(){
		double right_in = rightEncoder.getDistance() * Constants.Wheel_Constants.Inches_Per_Pulse;
		return right_in;
	}
	
	public double getAverageDisplacement(){
		double averageDisplacement = ((getLeftPosition() + getRightPosition()) * 0.5);
		return averageDisplacement;
	}
	
	public double getLeftVelocity(){
		double leftVelocity = getLeftPosition()/time;
		return leftVelocity;
	}
	
	public double getRightVelocity(){
		double rightVelocity = getRightPosition()/time;
		return rightVelocity;
	}
	
	public void setTime(double current){
		time = current;
	}
	
	public void resetSensors(){
//		left_2.setSelectedSensorPosition(0, 0, 0);
//		right_2.setSelectedSensorPosition(0, 0, 0);
		leftEncoder.reset();
		rightEncoder.reset();
		navx.reset();
	}
	
	public double compensateAndGetNewInput(double input){
		//This is with left "dragging"
		double newInput = (input + (input * (0.07/0.7)));
		return newInput;
	}
	
	public double getAngleCompensation(char driveSide, double time){
		//We start veering to the right once we add in input compensation
		double currentTime = time;
		double deltaTime = time - lastTimeForAngleCompensation;
		double error = navx.getAngle();
		double deltaError = error - lastErrorForAngleCompensation;
		errorIntegrationForAngleCompensation =+ error;
		double errorIROC = deltaError/(deltaTime == 0 ? 1 : deltaTime);
		
		
		
		double kPOutput = (driveSide == 'L' ? -1 * 0.08 * navx.getAngle() : driveSide == 'R' ? 0.08 * navx.getAngle() : 0 );
		double kDOutput = (driveSide == 'L' ? -1 * 0.03 * errorIROC : driveSide == 'R' ? 0.03 * errorIROC : 0 );
		double finalOutput = kPOutput + kDOutput;
//		System.out.println(kPOutput + "\t\t" + kDOutput + "\t\t" + time + "\t\t" + lastTimeForAngleCompensation);

		
//		System.out.println(time + "\t\t" + lastTimeForAngleCompensation + "\t\t" + getAngle());
		
		lastTimeForAngleCompensation = time;
		lastErrorForAngleCompensation = error;
		
		return finalOutput;
		
	}
	
	public void straightDrive(double lpow, double rpow, boolean angleCompensation){
		timedStraight(compensateAndGetNewInput(lpow) + (angleCompensation ? getAngleCompensation('L', 0) : 0), 
					  rpow + (angleCompensation ? getAngleCompensation('R', 0) : 0) ,
					  false);
	}

	
}

