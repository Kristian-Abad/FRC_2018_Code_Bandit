package org.usfirst.frc.team399.Auton;

public class SortLater {
	public SortLater(){
		//Low abstraction MP ("Cheap and dirty") Switch for both sides.
//		double[][] straight1Left = generator.generateTrajectory(24, false);
//		double[][] turnLeft = generator.generateTrajectory(21.991148575128552669238503682957, false);
//		double[][] straight2Left = generator.generateTrajectory(36, false);
//		if(gameData.charAt(0) == 'L'){
//			if(timer.get() < 0.913379){
//				Bandit.drive.runMotionProfile(straight1Left, 0, false, timer.get());
//				if(timer.get() < 0.413379){
//					Bandit.elevator.pivot(0.5);
//				}else{
//					Bandit.elevator.pivot(0);
//				}
//			}else if(timer.get() < 1.787696){
//				Bandit.drive.runMotionProfile(turnLeft, -45, false, timer.get() - 1.787697);
//				
//			}else if(timer.get() < 2.906352){
//				Bandit.drive.runMotionProfile(straight2Left, 0, false, timer.get() - 2.906352);
//			}
//			
//		}else if(gameData.charAt(1) == 'R'){
//			
//		}
		
		//Low abstraction "cheap and dirty" scale left
//		if(gameData.charAt(1) == 'L'){
//			if(timer.get() < 3.5){
//				Bandit.drive.autonRunMode(0.5, 0.5);
//			}else if(timer.get() < 3.8){
//				Bandit.drive.autonRunMode(0.5, -0.5);
//			}else if(timer.get() < 4.5){
//				Bandit.drive.autonRunMode(0, 0);
//				Bandit.elevator.elevateLift(1.0);
//			}else if(timer.get() < 5){
//				Bandit.elevator.elevateLift(Constants.Subsystems.Elevator.k_Hold_Output);
//				Bandit.elevator.runWheels(-0.7);
//			
//			}else{
//				Bandit.drive.autonRunMode(0, 0);
//				Bandit.elevator.elevateLift(Constants.Subsystems.Elevator.k_Hold_Output);
//				Bandit.elevator.runWheels(0);
//			}
//		}else{
//			if (timer.get() < 1.5) {
//				Bandit.drive.autonRunMode(0.5, 0.5);
//				Bandit.elevator.runWheels(-0);
//			}else{
//				Bandit.drive.autonRunMode(0, 0);
//			}
//		}
		
		//Low abstraction timed switch
//		if(gameData.charAt(0) == 'L'){
//
//			if (timer.get() < 0.8) {
//				Bandit.elevator.elevateLift(1.0);
//				if(timer.get() < 0.5) {
//					Bandit.elevator.pivot(0.5);
//				}else {
//					Bandit.elevator.pivot(-0.1);
//				}
//			}else if(timer.get() < 1.3){
//				Bandit.drive.autonRunMode(0.5, 0.5);
//			}else if(timer.get() < 1.6) {
//				Bandit.drive.autonRunMode(-0.5, 0.5);
//				Bandit.elevator.elevateLift(Constants.Subsystems.Elevator.k_Hold_Output);
//			}else if (timer.get() < 2.1){
//				Bandit.drive.autonRunMode(0.5, 0.5);
//			}else if(timer.get() < 2.6){
//				Bandit.drive.autonRunMode(0, 0);
//			}else if(timer.get() < 3.1){
//				Bandit.drive.autonRunMode(-0.5, -0.5);
//				Bandit.elevator.runWheels(-0.5);
//			}else{
//				Bandit.drive.autonRunMode(-0, -0);
//				Bandit.elevator.runWheels(-0);
//			} 
//				
//		
//		}else if(gameData.charAt(0) == 'R'){
//			if (timer.get() < 0.8) {
//				Bandit.elevator.elevateLift(1.0);
//				if(timer.get() < 0.5) {
//					Bandit.elevator.pivot(0.5);
//				}else {
//					Bandit.elevator.pivot(-0.1);
//				}
//			}else if(timer.get() < 1.3){
//				Bandit.drive.autonRunMode(0.5, 0.5);
//			}else if(timer.get() < 1.6) {
//				Bandit.drive.autonRunMode(-0.5, 0.5);
//				Bandit.elevator.elevateLift(Constants.Subsystems.Elevator.k_Hold_Output);
//			}else if (timer.get() < 2.1){
//				Bandit.drive.autonRunMode(0.5, 0.5);
//			}else if(timer.get() < 2.6){
//				Bandit.drive.autonRunMode(0, 0);
//			}else if(timer.get() < 3.1){
//				Bandit.drive.autonRunMode(-0.5, -0.5);
//				Bandit.elevator.runWheels(-0.5);
//			}else{
//				Bandit.drive.autonRunMode(-0, -0);
//				Bandit.elevator.runWheels(-0);
//			} 
//		}
		
//		MPStraightAndControlSystems test = new MPStraightAndControlSystems(time, buttonPressed, gameDataChar, gameDataChar, time, gameDataChar, time, time);

		//Scale left timed  (Old, probably doesn't work)
//		if(gameData.charAt(1) == 'L'){
//			if(timer.get() < 3.0){
//				Bandit.drive.timedStraight(-0.5, -0.5,true);
//			}else if(timer.get() < 3.4){
//				Bandit.drive.timedStraight(0, 0,false);
//			}else if(timer.get() < 3.6){
//				Bandit.drive.timedStraight(0.5, -0.5, false);
//				Supersystem.getInstance().resetSensors();
//			}else if(timer.get() < 4.1){
//				Bandit.drive.timedStraight(-0.5, -0.5,true);
//			}else{
//				Bandit.drive.timedStraight(0, 0,false);
//			}
//		}else{
//			if(timer.get() < 1.5) {
//				Bandit.drive.timedStraight(-0.5, -0.5, false);
//			}else{
//				Bandit.drive.timedStraight(0, 0, false);
//			}
//		}
		
		//This was in teleop periodic
//		System.out.println(Supersystem.getInstance().controls.DPad());
//		System.out.println(Supersystem.getInstance().controls.BottomLeftTrigger());
//		if(runTimer){
//			timer.start();
//			runTimer = false;
//		}
//		time = timer.get();
//		Bandit.drive.setTime(time);
//		Bandit.drive.runMotionProfile(testProfile, -90, true, time);
//		System.out.println(Bandit.drive.getRightVelocity() + " || Time :" + time);
		
		//AVR moved stuff from main Robot class
		
		//Scale Left
//		if(gameData.length() > 0){
//			if(gameData.charAt(1) == 'L'){
//				if(timer.get() < 2.7){
//					if(timer.get() < 0.2) {
//						Bandit.elevator.pivot(0.6);
//
//					}else {
//						Bandit.elevator.pivot(-0.1);
//
//					}
//
//					Bandit.drive.timedStraight(0.5, 0.5,true);
//					
//
//			}else if(timer.get() < 3.1){
//				Bandit.drive.timedStraight(0, 0,false);
//			}else if(timer.get() < 3.175){
//				
//				Bandit.drive.timedStraight(0.5, -0.5, false);
//
//			}else if(timer.get() < 3.675){
//				
//				Bandit.drive.timedStraight(0.5, 0.5,false);
//			}else if(timer.get() < 5.675) {
//				Bandit.drive.timedStraight(0, 0,false);
//
//				Bandit.elevator.elevateLift(0.7);
//
//			}else{
//				Bandit.drive.timedStraight(0, 0,false);
//				Bandit.elevator.elevateLift(0.1);
//				Bandit.elevator.runWheels(-0.5);
//
//
//			}
//		}else{
//			if(timer.get() < 1.5) {
//				Bandit.drive.timedStraight(0.5, 0.5, false);
//			}else{
//				Bandit.drive.timedStraight(0, 0, false);
//			}
//		}
//		
//	}	

		//Scale right Work in progress(3/30)
//		if(gameData.length() > 0){
//			if(gameData.charAt(0) == 'R'){
//				System.out.println(Bandit.drive.getAngle() + "\t\t" + timer.get());
//				if(timer.get() < 3.0){
//					Bandit.drive.straightDrive(0.5, 0.5, true);
//
//				}else if(timer.get() < 3.4){
//					Bandit.drive.straightDrive(0, 0, false);
//
//				}else if(timer.get() < 3.6){
//					Bandit.drive.straightDrive(-0.5, 0.5, false);
//				}else{
//					Bandit.drive.straightDrive(0, 0, false);
//
//				}
//			}else{
//				if(timer.get() < 1.5){
//					Bandit.drive.straightDrive(0.5, 0.5, true);
//					
//				}else{
//					Bandit.drive.straightDrive(0, 0, true);
//
//				}
//
//			}
//		}
		
		//Working switch 3/20/18
//		if(gameData.length() > 0 ){
//			
//			
//			if(gameData.charAt(0) == 'L'){
//				if(timer.get() < 0.5){
//					
//					Supersystem.getInstance().drive.timedStraight(0.5, 0.5, false);
//					if(timer.get() < 0.4){
//						Supersystem.getInstance().elevator.pivot(0.28);
//
//					}else{
//						
//						Supersystem.getInstance().elevator.pivot(-0);
//					}
//				}else if(timer.get() < 0.845){
//					Supersystem.getInstance().drive.timedStraight(-0.5, 0.5, false);
//
//				}else if(timer.get() < 2.145){
//					Supersystem.getInstance().drive.timedStraight(0.5, 0.5, false);
//
//				}else if(timer.get() < 3.0){
//					Supersystem.getInstance().drive.timedStraight(0, 0, false);
//					Supersystem.getInstance().elevator.pivot(-0.17);
//
//					Supersystem.getInstance().elevator.runWheels(-0.7);
//
//				}else{
//					Supersystem.getInstance().drive.timedStraight(0, 0, false);
//					Supersystem.getInstance().elevator.runWheels(0);
//
//				}
//				
//			}else if(gameData.charAt(0) == 'R'){
//				if(timer.get() < 0.5){
//					Supersystem.getInstance().drive.timedStraight(0.5, 0.5, false);
//					
//					if(timer.get() < 0.4){
//						Supersystem.getInstance().elevator.pivot(0.28);
//
//					}else{
//						
//						Supersystem.getInstance().elevator.pivot(-0);
//					}
//
//				}else if(timer.get() < 0.78){
//					Supersystem.getInstance().drive.timedStraight(0.5,-0.5, false);
//				}else if(timer.get() < 1.88){
//					Supersystem.getInstance().drive.timedStraight(0.5, 0.5, false);
//				}else if(timer.get() < 2.18){
//					Supersystem.getInstance().drive.timedStraight(0, 0, false);
//					Supersystem.getInstance().elevator.pivot(-0.17);
//
//				}else if(timer.get() < 3.4){
//					Supersystem.getInstance().elevator.runWheels(-0.7);
//					
//				}else{
//					Supersystem.getInstance().drive.timedStraight(0, 0, false);
//					Supersystem.getInstance().elevator.runWheels(0.0);
//
//				}
//			}
//		}
		
		//Timed straight (tests needed just for pivot)
//		if(timer.get() < 1.5) {
//			Bandit.drive.timedStraight(0.5, 0.5, false);
//		}else{
//			Bandit.drive.timedStraight(0, 0, false);
//		}
			
		//Scale right timed most recent 		
//		if(gameData.length() > 0){
//			if(gameData.charAt(1) == 'R'){
//				if(timer.get() < 3.0){
//					
//					Bandit.drive.timedStraight(0.5, 0.5,true);
//					if(timer.get() < 0.2){
//						Bandit.elevator.pivot(0.6);
//				
//					}else if(timer.get() < 1.5){
//						Bandit.elevator.pivot(-0.1);
//						Bandit.elevator.elevateLift(-0.6);
//					}else{
//						Bandit.elevator.pivot(-0.1);
//
//						Bandit.elevator.elevateLift(Constants.Subsystems.Elevator.k_Hold_Output);
//					}
//				}else if(timer.get() < 3.4){
//					Bandit.drive.timedStraight(0, 0,false);
//				}else if(timer.get() < 3.6){
//					Bandit.configureReset();
//					Bandit.drive.timedStraight(-0.5, 0.5, false);
//
//				}else if(timer.get() < 4.1){
//					Bandit.drive.timedStraight(0.5, 0.5,false);
//				}else{
//					Bandit.drive.timedStraight(0, 0,false);
//				}
//			}else{
//				if(timer.get() < 1.5) {
//					Bandit.drive.timedStraight(0.5, 0.5, false);
//				}else{
//					Bandit.drive.timedStraight(0, 0, false);
//				}
//			}
//			
//		}
		
		//Part of guaranteed scale auto if positioned on the right (Tests needed)
//		if(gameData.length() > 0){
//			if(gameData.charAt(1) == 'L'){
//				if(timer.get() < 3.1){
//					Bandit.drive.timedStraight(0.5, 0.5,true);
//				}else if(timer.get() < 3.8){
//					Bandit.drive.timedStraight(-0.5, 0.5, false);
//				}else if(timer.get() < 4.2){
//					Bandit.drive.timedStraight(0, 0, false);
//
//				}else if(timer.get() < 6.5){
//					Bandit.resetOnce();
//					Bandit.drive.timedStraight(0.5, 0.5,true);
//
//				}else if(timer.get() < 6.9){
//					Bandit.drive.timedStraight(0, 0, false);
//
//				}else if(timer.get() < 7.63){
//					Bandit.drive.timedStraight(0.5, -0.5, false);
//
//				}else if(timer.get() < 7.93){
//					Bandit.drive.timedStraight(0.5, 0.5, false);
//
//				}else{
//					Bandit.drive.timedStraight(0, 0,false);
//				}
//			}else{
//				
//			}
//		}
		
		//Working straight switch
//		if(gameData.charAt(0) == 'L'){
//
//			if (timer.get() < 0.8) {
//				Bandit.elevator.elevateLift(1.0);
//				if(timer.get() < 0.5) {
//					Bandit.elevator.pivot(0.5);
//				}else {
//					Bandit.elevator.pivot(-0.1);
//				}
//			}else if(timer.get() < 2.3) {
//				Bandit.drive.autonRunMode(0.5, 0.5);
//				Bandit.elevator.elevateLift(Constants.Subsystems.Elevator.k_Hold_Output);
//			}else if (timer.get() < 2.8){
//				Bandit.drive.autonRunMode(0, 0);
//			} else {
//				if (timer.get() < 3.3) {
//					Bandit.drive.autonRunMode(-0.5, -0.5);
//				} else {
//					Bandit.drive.autonRunMode(-0, -0);
//				}
//				Bandit.elevator.runWheels(-0.5);
//			}
//		
//		}else{
//			if (timer.get() < 1.5) {
//				Bandit.drive.autonRunMode(0.5, 0.5);
//				Bandit.elevator.runWheels(-0);
//			}else{
//				Bandit.drive.autonRunMode(0, 0);
//			}
//		}
		
		//Low abstraction "cheap and dirty" scale right
//		if(gameData.charAt(1) == 'R'){
//			if(timer.get() < 3.5){
//				Bandit.drive.autonRunMode(0.5, 0.5);
//			}else if(timer.get() < 3.8){
//				Bandit.drive.autonRunMode(-0.5, 0.5);
//			}else if(timer.get() < 4.5){
//				Bandit.drive.autonRunMode(0, 0);
//				Bandit.elevator.elevateLift(1.0);
//			}else if(timer.get() < 5){
//				Bandit.elevator.elevateLift(Constants.Subsystems.Elevator.k_Hold_Output);
//				Bandit.elevator.runWheels(-0.7);
//					
//			}else{
//				Bandit.drive.autonRunMode(0, 0);
//				Bandit.elevator.elevateLift(Constants.Subsystems.Elevator.k_Hold_Output);
//				Bandit.elevator.runWheels(0);
//			}
//		}else{
//			if(timer.get() < 1.5) {
//				Bandit.drive.autonRunMode(0.5, 0.5);
//				Bandit.elevator.runWheels(-0);
//			}else{
//				Bandit.drive.autonRunMode(0, 0);
//			}
//		}
	}
}
