package org.usfirst.frc.team399.Auton;

import org.usfirst.frc.team399.Commands.ElevateLift;
import org.usfirst.frc.team399.Commands.MotionProfileStraight;
import org.usfirst.frc.team399.Commands.MotionProfileTurn;
import org.usfirst.frc.team399.Commands.Pivot;
import org.usfirst.frc.team399.Commands.PivotIntake;
import org.usfirst.frc.team399.Commands.RunIntakeWheels;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class ThreeCubeSwitch extends CommandGroup{
	static String data = DriverStation.getInstance().getGameSpecificMessage();
	char gameDataChar;
	public ThreeCubeSwitch(){
		/**Once we get out max velocity and max acceleration get times for timeout.
		 * We also need to make some of the sequentials parallels
		 * 
		 * **/
		if(data.length() != 0){
			gameDataChar = data.charAt(0);
			System.out.println(gameDataChar);
			if(gameDataChar == 'L'){
				System.out.println("Left");				
				//Switch
				
//				this.addSequential(new MotionProfileStraight(24,false,1));
//				this.addSequential(new MotionProfileTurn(-35,1.0));
//				this.addSequential(new MotionProfileStraight(83,false,2.0));
//				this.addSequential(new MotionProfileTurn(15,1.0));
//				this.addSequential(new PivotIntake(1,0.8,0.5));
//				this.addSequential(new RunIntakeWheels(0.5,1.0));
//				this.addParallel(new MotionProfileStraight(52,true,1.3));
//				this.addSequential(new Pivot(1),.3);
//				this.addSequential(new MotionProfileTurn(40,1.0));
//				this.addParallel(new MotionProfileStraight(24,false,1.3));
//				this.addSequential(new RunIntakeWheels(-1.0,1.5));
//				this.addSequential(new MotionProfileStraight(24,true,1.3));
//				this.addParallel(new MotionProfileTurn(-40,1.0));
//				this.addSequential(new PivotIntake(-1,0.8,0.5));
//				this.addSequential(new MotionProfileStraight(48,false,1.3));
//				this.addSequential(new RunIntakeWheels(0.5,1.0));
//				this.addSequential(new MotionProfileStraight(24,true,1.3));
				
				//Bandit
//				this.addParallel(new PivotIntake(0.9,0.28,0));
//				this.addSequential(new MotionProfileStraight(24,false,1.3));
//				this.addSequential(new MotionProfileTurn(-35,1.0));
//				this.addSequential(new MotionProfileStraight(83,false,2.0));
//				this.addSequential(new MotionProfileTurn(15,1.0));
//				this.addSequential(new RunIntakeWheels(-0.5,1.0));
//				this.addParallel(new MotionProfileStraight(52,true,1.3));
//				this.addSequential(new PivotIntake(0.9,1.1,0.3));
//				this.addSequential(new MotionProfileTurn(40,1.0));
//				this.addParallel(new MotionProfileStraight(24,false,1.3));
//				this.addSequential(new RunIntakeWheels(1.0,1.5));
//				this.addSequential(new MotionProfileStraight(24,true,1.3));
//				this.addParallel(new MotionProfileTurn(-40,1.0));
//				this.addSequential(new PivotIntake(-0.9,0.8,0.3));
//				this.addSequential(new MotionProfileStraight(48,false,1.3));
//				this.addSequential(new RunIntakeWheels(-0.5,1.0));


			}else if(gameDataChar == 'R'){
				System.out.println("Right");
				
				//Work in progress
				this.addParallel(new PivotIntake(0.9,0.28,0));
				this.addSequential(new MotionProfileStraight(24,false,1.0));
				this.addSequential(new MotionProfileTurn(30,1.0));
				this.addSequential(new MotionProfileStraight(94,false,2.2));
				this.addSequential(new RunIntakeWheels(0.5,0.5,0.0));
				this.addSequential(new MotionProfileTurn(-60,1.0));

				//switch
//				this.addParallel(new PivotIntake(0.9,0.28,0));
//				this.addSequential(new MotionProfileStraight(24,false,0.913379));//0.4 diff used to be 1.3
//				this.addSequential(new MotionProfileTurn(30,0.713877));//0.3 diff used to be 1.0
//				this.addSequential(new MotionProfileStraight(78,false,1.646617));// 0.4 diff used to be 2.2
//				this.addSequential(new MotionProfileTurn(-20,0.582878));// about 0.5 diff used to be 1.0
//				this.addSequential(new RunIntakeWheels(0.5,1.0));
//				this.addParallel(new MotionProfileStraight(52,true,1.3));
//				this.addSequential(new PivotIntake(0.9,1.1,0.3));
//				this.addSequential(new MotionProfileTurn(-40,1.0));
//				this.addParallel(new MotionProfileStraight(24,false,1.0));
//				this.addSequential(new RunIntakeWheels(-1.0,1.5));
//				this.addSequential(new MotionProfileStraight(24,true,1.0));
//				this.addSequential(new MotionProfileTurn(43,1.0));
//				this.addSequential(new PivotIntake(-1.0,0.5,0.0));
//				this.addSequential(new MotionProfileStraight(37,false,1.3));
//				this.addSequential(new RunIntakeWheels(0.5,1.0));
				
				//Switch rework
//				this.addParallel(new PivotIntake(0.9,0.28,0));
//				this.addSequential(new MotionProfileStraight(24,false,0.913379));
//				this.addSequential(new MotionProfileTurn(30,1.0));
//				this.addParallel(new MotionProfileStraight(65,false,1.5));
//				this.addSequential(new RunIntakeWheels(-0.5,1.5,1.2));
//				this.addParallel(new MotionProfileStraight(24,true,0.913379));
//				this.addSequential(new PivotIntake(0.9,0.8,0.0));
//				this.addSequential(new MotionProfileTurn(60,1.0));
//				this.addParallel(new MotionProfileStraight(24,true,0.913379));
//				this.addSequential(new RunIntakeWheels(0.5,0.913379,0.0));
//				this.addParallel(new MotionProfileStraight(24,true,0.913379));
//				this.addSequential(new PivotIntake(-0.9,0.5,0.0));
//				this.addSequential(new MotionProfileTurn(-60,1.0));
//				this.addSequential(new RunIntakeWheels(-0.5,0.5,0.0));


				//Bandit
//				this.addParallel(new PivotIntake(0.9,0.28,0));
//				this.addSequential(new MotionProfileStraight(24,false,1.3));
//				this.addSequential(new MotionProfileTurn(30,1.0));
//				this.addSequential(new MotionProfileStraight(78,false,2.2));
//				this.addSequential(new MotionProfileTurn(-20,1.0));
//				this.addSequential(new RunIntakeWheels(-0.5,1.0));
//				this.addParallel(new MotionProfileStraight(52,true,1.3));
//				this.addSequential(new PivotIntake(0.9,1.1,0.3));
//				this.addSequential(new MotionProfileTurn(-40,1.0));
//				this.addParallel(new MotionProfileStraight(24,false,1.0));
//				this.addSequential(new RunIntakeWheels(1.0,1.5));
//				this.addSequential(new MotionProfileStraight(24,true,1.0));
//				this.addParallel(new MotionProfileTurn(43,1.0));
//				this.addSequential(new PivotIntake(-0.93,0.8,0.3));
//				this.addSequential(new MotionProfileStraight(37,false,1.3));
//				this.addSequential(new RunIntakeWheels(-0.5,1.0));

			}
		}
		
		
	}
}
