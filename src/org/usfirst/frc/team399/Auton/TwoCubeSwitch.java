package org.usfirst.frc.team399.Auton;

import org.usfirst.frc.team399.Commands.MotionProfileStraight;
import org.usfirst.frc.team399.Commands.MotionProfileTurn;
import org.usfirst.frc.team399.Commands.PivotIntake;
import org.usfirst.frc.team399.Commands.RunIntakeWheels;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class TwoCubeSwitch extends CommandGroup{
	static String data = DriverStation.getInstance().getGameSpecificMessage();
	char gameDataChar;
	public TwoCubeSwitch(){
		/**Once we get out max velocity and max acceleration get times for timeout.
		 * We also need to make some of the sequentials parallels
		 * 
		 * **/
		if(data.length() != 0){
			gameDataChar = data.charAt(0);
			System.out.println(gameDataChar);
			if(gameDataChar == 'L'){
				System.out.println("Left");
//				this.addParallel(new PivotIntake(0.9,0.28,0));
//				this.addSequential(new MotionProfileStraight(24,false,1.3));
//				this.addSequential(new MotionProfileTurn(-35,1.0));
//				this.addSequential(new MotionProfileStraight(83,false,2.0));
//				this.addSequential(new MotionProfileTurn(15,1.0));
//				this.addSequential(new RunIntakeWheels(0.5,1.0,0.0));
//				this.addParallel(new MotionProfileStraight(52,true,1.3));
//				this.addSequential(new PivotIntake(0.9,1.1,0.3));
//				this.addSequential(new MotionProfileTurn(40,1.0));
//				this.addParallel(new MotionProfileStraight(24,false,1.3));
//				this.addSequential(new RunIntakeWheels(-1.0,1.5,0.0));
//				this.addSequential(new MotionProfileStraight(24,true,1.3));
//				this.addParallel(new MotionProfileTurn(-40,1.0));
//				this.addSequential(new PivotIntake(-0.9,0.8,0.3));
//				this.addSequential(new MotionProfileStraight(48,false,1.3));
//				this.addSequential(new RunIntakeWheels(0.5,1.0,0.0));
				
				//Bandit
				this.addParallel(new PivotIntake(0.9,0.28,0));
				this.addSequential(new MotionProfileStraight(24,false,0.52));
				this.addSequential(new MotionProfileTurn(-41.5,0.6));
				this.addSequential(new MotionProfileStraight(94,false,1.0));
				this.addSequential(new MotionProfileTurn(15,1.0));
				this.addSequential(new RunIntakeWheels(-0.5,0.5,0.0));
				// 1st cube in
				this.addParallel(new MotionProfileStraight(54,true,1.1));
				this.addSequential(new PivotIntake(0.9,1.1,0.3));
				this.addSequential(new MotionProfileTurn(60,0.8));
				this.addParallel(new MotionProfileStraight(16,false,1.3));
				this.addSequential(new RunIntakeWheels(1.0,1.3,0.0));
				this.addSequential(new MotionProfileStraight(28,true,1.0));
				this.addParallel(new MotionProfileTurn(-58,1.3));
				this.addSequential(new PivotIntake(-0.9,0.9,0.0));
				this.addSequential(new MotionProfileStraight(60,false,1.3));
				this.addSequential(new RunIntakeWheels(-0.5,0.5,0.0));
				
				this.addSequential(new MotionProfileStraight(15,true,0.5));
				this.addParallel(new MotionProfileTurn(80,0.5));
				this.addSequential(new PivotIntake(0.93,0.7,0.0));
				this.addParallel(new MotionProfileStraight(13,false,0.5));
				this.addSequential(new RunIntakeWheels(1.0,0.8,0.0));
				this.addSequential(new PivotIntake(-0.93,0.6,0.0));
				this.addSequential(new MotionProfileTurn(-60,0.5));
				this.addSequential(new RunIntakeWheels(-0.5,1.0,0.0));

			}else if(gameDataChar == 'R'){
				System.out.println("Right");
				
//				this.addParallel(new PivotIntake(0.9,0.28,0));
//				this.addSequential(new MotionProfileStraight(24,false,1.3));
//				this.addSequential(new MotionProfileTurn(30,1.0));
//				this.addSequential(new MotionProfileStraight(78,false,2.2));
//				this.addSequential(new MotionProfileTurn(-20,1.0));
//				this.addSequential(new RunIntakeWheels(0.5,1.0,0.0));
//				this.addParallel(new MotionProfileStraight(52,true,1.3));
//				this.addSequential(new PivotIntake(0.9,1.1,0.3));
//				this.addSequential(new MotionProfileTurn(-40,1.0));
//				this.addParallel(new MotionProfileStraight(24,false,1.0));
//				this.addSequential(new RunIntakeWheels(-1.0,1.5,0.0));
//				this.addSequential(new MotionProfileStraight(24,true,1.0));
//				this.addParallel(new MotionProfileTurn(40,1.0));
//				this.addSequential(new PivotIntake(-1.0,0.8,0.3));
//				this.addSequential(new MotionProfileStraight(37,false,1.3));
//				this.addSequential(new RunIntakeWheels(0.5,1.0,0.0));
				
				//Bandit
				this.addParallel(new PivotIntake(0.9,0.28,0));
				this.addSequential(new MotionProfileStraight(24,false,0.52));
				this.addSequential(new MotionProfileTurn(30,0.45));
				this.addSequential(new MotionProfileStraight(94,false,1.0));
				this.addSequential(new MotionProfileTurn(-17,0.3));
				this.addSequential(new RunIntakeWheels(-0.5,1.0,0.0));
				this.addParallel(new MotionProfileStraight(52,true,1.3));
				this.addSequential(new PivotIntake(0.9,1.1,0.3));
				this.addSequential(new MotionProfileTurn(-55,1.0));
				this.addParallel(new MotionProfileStraight(27,false,1.0));
				this.addSequential(new RunIntakeWheels(1.0,1.1,0.0));
				this.addSequential(new MotionProfileStraight(24,true,1.0));
				this.addParallel(new MotionProfileTurn(77,1.0));
				this.addSequential(new PivotIntake(-0.93,0.7,0.0));

				this.addSequential(new MotionProfileStraight(52,false,1.3));
				this.addSequential(new RunIntakeWheels(-0.5,1.0,0.0));
				
				this.addSequential(new MotionProfileStraight(10,true,0.5));
				this.addParallel(new MotionProfileTurn(-91,1.0));
				this.addSequential(new PivotIntake(0.93,0.7,0.0));
				this.addParallel(new MotionProfileStraight(10,false,0.5));
				this.addSequential(new RunIntakeWheels(1.0,0.7,0.0));
				this.addSequential(new PivotIntake(-0.93,0.7,0.0));
				this.addSequential(new MotionProfileTurn(60,1.0));
				this.addSequential(new RunIntakeWheels(-0.5,1.0,0.0));

			}
		}
		
		
	}
	
	public void setData(){
		
		data = DriverStation.getInstance().getGameSpecificMessage();
	}
}
