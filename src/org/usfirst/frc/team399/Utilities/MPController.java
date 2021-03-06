package org.usfirst.frc.team399.Utilities;

import java.util.Timer;
import java.util.TimerTask;

import org.usfirst.frc.team399.Config.Constants;
import org.usfirst.frc.team399.Systems.Supersystem;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class MPController {
	Timer timer = new Timer();
	double[][][] profiles;
	double[][] motionProfile;
	double[][] currentTransition;
	double[][] subsystemPosition1, 
			   subsystemPosition2, 
			   subsystemPosition3,
			   supplementalTransition1,
			   supplementalTransition2;
	
	char currentPosition, lastPosition;
	double subsystemOutput;
	double time;
	double lastError = 0;
	double lastTime = 0;
	double lastLeftPositionError = 0;
	double lastRightPositionError = 0;
	double errorLeftIntegration = 0;
	double errorRightIntegration = 0;
	double lastLeftError = 0;
	double leftErrorIntegration = 0;
	double lastRightError = 0;
	double rightErrorIntegration = 0;
	
	double angle, compAngle;
	
	double tunekV,tunekA,tunekVT,tunekAP;
	double kP2 = 0.03;
	
	//Move these PID Drivetrain constants!
	double kP = 0.01;
	double kI = 0.0;
	double kD = 0.0;
	boolean run = true;
	boolean runSubsystem = false;
	boolean degreeMode;
	int point = 0;
	int desiredAngle;
	
	public MPController(double[][] profile,int angle,boolean turn){
		motionProfile = profile;
		degreeMode = turn;
		desiredAngle = angle;
	}
	
	public MPController(double[][] profile1, double[][] profile2, double[][] profile3, double[][] supp1, double[][] supp2){
		subsystemPosition1 = profile1;
		subsystemPosition2 = profile2;
		subsystemPosition3 = profile3;
		supplementalTransition1 = supp1;
		supplementalTransition2 = supp2;
	}
	
	public MPController(double[][][] transitions){
		profiles = transitions;
	}
	
	public MPController(){
		
	}
	
	
	class Periodic extends TimerTask{
		
		public void run() {
			// TODO Auto-generated method stub
			point = (point < motionProfile.length - 1 ? point + 1 : point == motionProfile.length - 1 ? motionProfile.length - 1 : 0);
			
		}
		
		
	}
	
	public double[] getDrivetrainOutputs(double currentTime){
		double[] outputs;
		time = currentTime;
		compAngle = Supersystem.getInstance().drive.getAngle();
		double deltaTime = currentTime - lastTime;
		
		if(run){
			timer.scheduleAtFixedRate(new Periodic(), 0, Constants.MotionProfile.Motion_Profile_Update_Rate);
			run = false;
		}
		
		
		//Control statements depending on which mode the controller is in (degreemode or forward movement mode)
		if(degreeMode){
			
			//Get our instantaneous velocity "setpoint" from our motion profile that was already pre-calculated for the left side of the drivetrain
			double leftInstantaneousVelocity = (desiredAngle < 0 ? -1 * motionProfile[(point == motionProfile.length - 1 ? motionProfile.length - 1 : point)][0]:
												motionProfile[(point == motionProfile.length - 1 ? motionProfile.length - 1 : point)][0]);
			
			//Get our instantaneous acceleration "setpoint" from our motion profile that was already pre-calculated for the left side of the drivetrain
			double leftInstantaneousAcceleration = (desiredAngle < 0 ? -1 * motionProfile[(point == motionProfile.length - 1 ? motionProfile.length - 1 : point)][1]:
													motionProfile[(point == motionProfile.length - 1 ? motionProfile.length - 1 : point)][1]);
			
			//Get our instantaneous velocity "setpoint" from our motion profile that was already pre-calculated for the right side of the drivetrain
			double rightInstantaneousVelocity = (desiredAngle < 0 ? motionProfile[(point == motionProfile.length - 1 ? motionProfile.length - 1 : point)][0]:
												 -1 * motionProfile[(point == motionProfile.length - 1 ? motionProfile.length - 1 : point)][0]);
			
			//Get our instantaneous acceleration "setpoint" from our motion profile that was already pre-calculated for the right side of the drivetrain
			double rightInstantaneousAcceleration = (desiredAngle < 0 ? motionProfile[(point == motionProfile.length - 1 ? motionProfile.length - 1 : point)][1]:
				 								 -1 * motionProfile[(point == motionProfile.length - 1 ? motionProfile.length - 1 : point)][1]);
			
			//Get our instantaneous displacement "setpoint" from our motion profile that was alread pre-calculated
			double instantaneousDisplacement = motionProfile[point][2];
			
			double instantaneousAngle = motionProfile[point][3];
			//Stuff for the built-in PID loop. Have to make two different sets for both sides of the drivetrain? This is kludge for now.
//			double error = (instantaneousVelocity < 0 ? -1 : 1) * Math.abs(instantaneousDisplacement) - Math.abs(getAverageDisplacement());;
//			double deltaError = error - lastError;
//			double errorIntegration =+ error;
//			double errorDelta = deltaError/deltaTime - instantaneousVelocity;
//			double lastTime = time;
//			double lastError = error;
			
			double leftError = (leftInstantaneousVelocity + 0.001 < 0 ? -1 : leftInstantaneousVelocity + 0.001 > 0 ? 1 : 1) * (Math.abs(instantaneousDisplacement) - Math.abs(getLeftPosition()));
			double leftDeltaError = leftError - lastLeftError;
			leftErrorIntegration =+ leftError;
			double leftErrorDelta = leftDeltaError/(deltaTime == 0 ? 1 : deltaTime);
			
			double rightError = (rightInstantaneousVelocity + 0.001 < 0 ? -1 : rightInstantaneousVelocity + 0.001 > 0 ? 1 : 1) * (Math.abs(instantaneousDisplacement) - Math.abs(getRightPosition()));
			double rightDeltaError = rightError - lastRightError;
			rightErrorIntegration += rightError;
			double rightErrorDelta = rightDeltaError/(deltaTime == 0 ? 1 : deltaTime);
			
			double angleError = Math.abs(instantaneousAngle) - Math.abs(compAngle);
			
			//Compute our compensation velocity values if we have some output imbalance due to mechanical or electrical discrepancies for the left side of the drivetrain
			double compensationLeftVelocity = (isRightVelocityGreater() && leftInstantaneousVelocity < 0 ? -1 :
											   isRightVelocityGreater() && leftInstantaneousVelocity > 0 ? 1 :
											   isLeftVelocityGreater() && leftInstantaneousVelocity < 0 ? 1 : 
											   isLeftVelocityGreater() && leftInstantaneousVelocity > 0 ? -1 : 0) * getDifferenceInVelocity();
												
//											  (isLeftVelocityGreater() ? 
//											   -1 * Constants.Wheel_Constants.distance_Between_Wheels * (Math.abs(angle)/time) :
//												isRightVelocityGreater() ? 
//												Constants.Wheel_Constants.distance_Between_Wheels * (Math.abs(angle)/time): 
//												0);
			
			//Compute our compensation velocity values if we have some output imbalance due to mechanical or electrical discrepancies for the right side of the drivetrain
			double compensationRightVelocity = (isLeftVelocityGreater() && rightInstantaneousVelocity < 0 ? -1 :
												isLeftVelocityGreater() && rightInstantaneousVelocity > 0 ? 1:
												isRightVelocityGreater() && rightInstantaneousVelocity < 0 ? 1 :
												isRightVelocityGreater() && rightInstantaneousVelocity > 0 ? -1 : 0) * getDifferenceInVelocity();
//											   (isLeftVelocityGreater() ? 
//										  	    -1 * Constants.Wheel_Constants.distance_Between_Wheels * (Math.abs(angle)/time) :
//										  	    isRightVelocityGreater() ? 
//										  	    Constants.Wheel_Constants.distance_Between_Wheels * (Math.abs(angle)/time): 
//										        0);
											  
			//Compute our actual output for the left side of the drivetrain. PID excluded for now
			double l_output = /**(Constants.Subsystems.Drivetrain.k_Proportional_T * leftError) + 
						      (Constants.Subsystems.Drivetrain.k_Integral_T * leftErrorIntegration) + 
						      (Constants.Subsystems.Drivetrain.k_Derivative_T * leftErrorDelta) + **/
							  ((desiredAngle < 0 ? -1 : 1) * Constants.Subsystems.Drivetrain.k_Proportional_T * angleError) +
							  (Constants.Subsystems.Drivetrain.k_Tangential_Velocity * leftInstantaneousVelocity) + 
							  /**(Constants.Subsystems.Drivetrain.k_Tangential_Velocity * compensationLeftVelocity)  + **/
							  (Constants.Subsystems.Drivetrain.k_Perpendicular_Acceleration * leftInstantaneousAcceleration);

			//Compute our actual output for the left side of the drivetrain. PID excluded for now
			double r_output = /**(Constants.Subsystems.Drivetrain.k_Proportional_T * rightError) + 
				      		  (Constants.Subsystems.Drivetrain.k_Integral_T * rightErrorIntegration) + 
				      		  (Constants.Subsystems.Drivetrain.k_Derivative_T * rightErrorDelta) + **/
					  		  ((desiredAngle < 0 ? 1 : -1) * Constants.Subsystems.Drivetrain.k_Proportional_T * angleError) +
							  (Constants.Subsystems.Drivetrain.k_Tangential_Velocity * rightInstantaneousVelocity) + 
							  /**(Constants.Subsystems.Drivetrain.k_Tangential_Velocity * compensationRightVelocity) + **/
						      (Constants.Subsystems.Drivetrain.k_Perpendicular_Acceleration * rightInstantaneousAcceleration);
			
//			System.out.println(desiredAngle + "\t\t\t"+ angleError + "\t\t\t" + leftInstantaneousVelocity + "\t\t\t" + rightInstantaneousVelocity + "\t\t\t" +
//					((desiredAngle < 0 ? -1 : 1) * Constants.Subsystems.Drivetrain.k_Proportional_T * angleError) + "\t\t\t" +
//					((desiredAngle < 0 ? 1 : -1) * Constants.Subsystems.Drivetrain.k_Proportional_T * angleError));
			//System.out.println(l_output + "\t\t" + r_output);
			//Print statements for diagnostics
//			System.out.println("Percent Error:"+ 
//							   "\t\t\t" + 
//							   getPercentError(desiredAngle, angle));
			
//			System.out.println(Constants.MotionProfile.k_Tangential_Velocity * compensationLeftVelocity + 
//						       "\t\t\t" + 
//						       Constants.MotionProfile.k_Tangential_Velocity * compensationRightVelocity);
//			System.out.println(l_output + "\t\t\t" + r_output);
			
			lastLeftError = leftError;
			lastRightError = rightError;
			
			//Might not need to have input difference for turning. We might actually need to add compensationVelocity on just the left
			outputs = new double[]{l_output,r_output};
			
		}else{
			
			//Get our instantaneous velocity "setpoint" from our motion profile that was already pre-calculated
			double instantaneousVelocity = motionProfile[(point == motionProfile.length - 1 ? motionProfile.length - 1 : point)][0];
			
			//Get our instantaneous acceleration "setpoint" from our motion profile that was already pre-calculated
			double instantaneousAcceleration = motionProfile[(point == motionProfile.length - 1 ? motionProfile.length - 1 : point)][1];
			
			//Get our instantaneous displacement "setpoint" from our motion profile that was already pre-calculated
			double instantaneousDisplacement = motionProfile[point][2];
			
			//Compute our compensation velocity values if we have some output imbalance due to mechanical or electrical discrepancies for the left side of the drivetrain
			double compensationLeftVelocity = Constants.Subsystems.Drivetrain.k_Velocity * (compAngle < 0 ? Constants.Wheel_Constants.Distance_Between_Wheels * (Math.abs(compAngle)/time) : 
				compAngle > 0 ? -1 * Constants.Wheel_Constants.Distance_Between_Wheels * (Math.abs(compAngle)/time) : 
				   0);
			
			//Compute our compensation velocity values if we have some output imbalance due to mechanical or electrical discrepancies for the right side of the drivetrain
			double compensationRightVelocity = Constants.Subsystems.Drivetrain.k_Velocity * (compAngle < 0 ? -1 * Constants.Wheel_Constants.Distance_Between_Wheels * (Math.abs(compAngle)/time) : 
				    angle > 0 ? Constants.Wheel_Constants.Distance_Between_Wheels * (Math.abs(compAngle)/time):
				    0 );
			
			//PID Loop calculations
			double error = (instantaneousVelocity < 0 ? -1 : 1) *  (Math.abs(instantaneousDisplacement) - Math.abs(getAverageDisplacement()));
			double deltaError = error - lastError;
			double errorIntegration =+ error;
			double errorDelta = deltaError/(deltaTime == 0 ? 1 : deltaTime) - instantaneousVelocity;
			
			
			

			double l_output = (Constants.Subsystems.Drivetrain.k_Proportional * error) + 
							  (Constants.Subsystems.Drivetrain.k_Integral * errorIntegration) + 
							  (Constants.Subsystems.Drivetrain.k_Derivative * errorDelta) + 
							  (Constants.Subsystems.Drivetrain.k_Velocity * (instantaneousVelocity /**+ compensationLeftVelocity +**/ )) +
							  (Constants.Subsystems.Drivetrain.k_Acceleration * instantaneousAcceleration); 
							  

			double r_output = (Constants.Subsystems.Drivetrain.k_Proportional * error) + 
					  	      (Constants.Subsystems.Drivetrain.k_Integral * errorIntegration) + 
					          (Constants.Subsystems.Drivetrain.k_Derivative * errorDelta) +  
							  (Constants.Subsystems.Drivetrain.k_Velocity * (instantaneousVelocity /**+ compensationRightVelocity +**/)) +		  
						      (Constants.Subsystems.Drivetrain.k_Acceleration * instantaneousAcceleration); 
			
			//System.out.printf("%-10f%-10f%-10f%-10f\n", error,instantaneousDisplacement,getAverageDisplacement(),time);
			//System.out.println(error + "\t\t" + instantaneousDisplacement + "\t\t" + getAverageDisplacement() + "\t\t" + time);
			outputs = new double[]{Supersystem.getInstance().drive.compensateAndGetNewInput(l_output) + Supersystem.getInstance().drive.getAngleCompensation('L', time),
					   			   r_output + Supersystem.getInstance().drive.getAngleCompensation('R', time)};
			
			lastTime = time;
			lastError = error;
			// Just some print statements for diagnostics
//			System.out.println(l_output + "\t\t\t" + r_output);
//			System.out.println(compensationLeftVelocity + "\t\t\t" + compensationRightVelocity + "\t\t\t" + angle);
//			System.out.printf("%-10s%-10d%-10s%-12f%-10s%-12f%-10s%-10f%-10s%-10f%-10s%-10f%-10s%-10f\n","Point:",
//			   point,
//			   "Inst_Velocity:", 
//			   instantaneousVelocity,
//			   "L_Velocity:",
//			   getLeftVelocity(),
//			   "R_Velocity:",
//			   getRightVelocity(),
//			   "Inst_Pos:",
//			   instantaneousDisplacement,
//			   "L_Pos:",
//			   getLeftPosition(),
//			   "R_Pos:",
//			   getRightPosition());
		}
		
		return outputs;
		
	}
	
	public void schedulePoints(){
		//Have our timer schedule our point to update once and not periodically
		if(run){
			timer.scheduleAtFixedRate(new Periodic(), 0, Constants.MotionProfile.Motion_Profile_Update_Rate);
			run = false;
		}
	}
		
	public void pointReset(){
		point = 0;
	}
	
	public void setProfile(double[][] newProfile, int angle, boolean turn){
		motionProfile = newProfile;
		degreeMode = turn;
		desiredAngle = angle;
	}
	
	public void configureNewProfile(double[][] newProfile, int angle, boolean turn){
		pointReset();
		motionProfile = newProfile;
		degreeMode = turn;
		desiredAngle = angle;
	}
	public double getSubsystemOutput(boolean goToPosition2,
									 boolean goToPosition1,
									 boolean goToPosition0,
								     double kP,
								     double kI,
								     double kD,
								     double kV,
								     double kA,
								     double currentDisplacement,
								     double currentTime){
		double deltaTime = currentTime - lastTime;
		
		if(goToPosition2){
			currentPosition = '2';
		}else if(goToPosition1){
			currentPosition = '1';
		}else if(goToPosition0){
			currentPosition = '0';
		}
		
		
		
		if(currentPosition != lastPosition){
			runSubsystem = true;
		}
		
		
//		boolean moveToPosition0 = (goToPosition1 || goToPosition2 ? false : goToPosition0);
//		boolean moveToPosition1 = (goToPosition0 || goToPosition2 ? false : goToPosition1);
//		boolean moveToPosition2 = (goToPosition0 || goToPosition1 ? false : goToPosition2);
		
		double[][] transition0To1 = profiles[0];
		double[][] transition0To2 = profiles[1];
		double[][] transition1To0 = profiles[2];
		double[][] transition2To0 = profiles[3];
		double[][] transition1To2 = profiles[4];
		double[][] transition2To1 = profiles[5];
		double[][] noTransition = new double[][]{{0,0,0}};
		
		
		
//		lastPosition = (currentTransition == transition0To1 || currentTransition == transition0To2 ? '0' :
//				        currentTransition == transition1To0 || currentTransition == transition1To2 ? '1' :
//				        currentTransition == transition2To0 || currentTransition == transition2To1 ? '2' :
//				        currentTransition == noTransition ? '0' : '0');
		if(currentTransition == transition0To1 || currentTransition == transition0To2 && transitionNotFinished(currentTransition)){
			lastPosition = '0';
		}else if(currentTransition == transition1To0 || currentTransition == transition1To2 && transitionNotFinished(currentTransition)){
			lastPosition = '1';
		}else if(currentTransition == transition2To0 || currentTransition == transition2To1 && transitionNotFinished(currentTransition)){
			lastPosition = '2';
		}else{
			lastPosition = currentPosition;
		}
		
		currentTransition = (lastPosition0(lastPosition) && goToPosition1 ? transition0To1 : 
							 lastPosition0(lastPosition) && goToPosition2 ? transition0To2 : 
							 lastPosition1(lastPosition) && goToPosition0 ? transition1To0 :
							 lastPosition2(lastPosition) && goToPosition0 ? transition2To0 :
							 lastPosition1(lastPosition) && goToPosition2 ? transition1To2 :
							 lastPosition2(lastPosition) && goToPosition1 ? transition2To1 :
							 noTransition);
		
		
		
		motionProfile = currentTransition;
		if(goToPosition0 || goToPosition1 || goToPosition2 && runSubsystem){
			pointReset();
//			timer.purge();
//			timer.cancel();
			timer.scheduleAtFixedRate(new Periodic(), 0, Constants.MotionProfile.Motion_Profile_Update_Rate);
			runSubsystem = false;
		}
		double instantaneousVelocity = currentTransition[(point == currentTransition.length - 1 && currentTransition.length > 1 ? 
				currentTransition.length - 1 : point < currentTransition.length - 1 && currentTransition.length > 1 ? point : 0)][0];
		double instantaneousAcceleration = currentTransition[(point == currentTransition.length - 1 && currentTransition.length > 1 
				? currentTransition.length - 1 : point < currentTransition.length - 1 && currentTransition.length > 1 ? point : 0)][1];
		double instantaneousDisplacement = currentTransition[(point == currentTransition.length - 1 && currentTransition.length > 1 
				? currentTransition.length - 1 : point < currentTransition.length - 1 && currentTransition.length > 1 ? point : 0)][2];
		
		
		//Excluded for now
//		double error = instantaneousDisplacement - currentDisplacement;
//		double deltaError = error - lastError;
//		double errorIntegration =+ error;
//		double errorDelta = deltaError/deltaTime - instantaneousVelocity;
//		double lastTime = time;
//		double lastError = error;
//		lastPosition = currentPosition;
		subsystemOutput = (point < currentTransition.length - 1 ? (kV * instantaneousVelocity) + (kA * instantaneousAcceleration) : 
						   Constants.Subsystems.Elevator.k_Hold_Output);
		
		
		System.out.println(currentPosition + "\t\t\t" + lastPosition + "\t\t\t" + runSubsystem);
		return subsystemOutput;
	}
	
	public boolean transitionNotFinished(double[][] transition){
		return point != transition.length - 1;
	}
	
	public char getLastPosition(){
		return lastPosition;
	}
	
	public void setLastPosition(char set){
		lastPosition = set;
	}
	
	
	
	public boolean lastPosition0(char evaluate){
		boolean result = (evaluate == '0' ? true : false);
		return result;
	}
	
	public boolean lastPosition1(char evaluate){
		boolean result = (evaluate == '1' ? true : false);
		return result;
	}
	
	public boolean lastPosition2(char evaluate){
		boolean result = (evaluate == '2' ? true : false);
		return result;
	}
	
	public double getAverageDisplacement(){
		double averageDisplacement = Supersystem.getInstance().drive.getAverageDisplacement();
		return averageDisplacement;
	}
	
	public double getLeftPosition(){
		double leftPosition = Supersystem.getInstance().drive.getLeftPosition();
		return leftPosition;
	}
	
	public double getLeftVelocity(){
		double leftVelocity = Supersystem.getInstance().drive.getLeftVelocity();
		return leftVelocity;
		
	}
	
	public double getRightPosition(){
		double rightPosition = Supersystem.getInstance().drive.getRightPosition();
		return rightPosition;
	}
	
	public double getRightVelocity(){
		double rightVelocity = Supersystem.getInstance().drive.getRightVelocity();
		return rightVelocity;
		
	}
	
	public double getPercentError(double theoreticalValue, double experimentalValue){
		double percentError = (theoreticalValue < 0 ? ((Math.abs(theoreticalValue) - Math.abs(experimentalValue))/Math.abs(theoreticalValue)) * 100 :
							   ((theoreticalValue - experimentalValue)/theoreticalValue) * 100);
		return percentError;
	}
	
	public boolean isLeftVelocityGreater(){
		boolean isLeftBigger = (Math.abs(getLeftVelocity()) > Math.abs(getRightVelocity()) ? true : false);
		return	isLeftBigger;
	}
	
	public boolean isRightVelocityGreater(){
		boolean isRightBigger = (Math.abs(getRightVelocity()) > Math.abs(getLeftVelocity()) ? true : false);
		return	isRightBigger;
	}
	
	public double getDifferenceInVelocity(){
		double difference = (isLeftVelocityGreater() ? Math.abs(getLeftVelocity()) - Math.abs(getRightVelocity()) :
							 isRightVelocityGreater() ? Math.abs(getRightVelocity()) - Math.abs(getLeftVelocity()) :
							 0);
		return difference;
	}
}
