package org.usfirst.frc.team399.Auton;

import org.usfirst.frc.team399.Commands.MotionProfileStraight;
import org.usfirst.frc.team399.Commands.MotionProfileTurn;
import org.usfirst.frc.team399.Commands.PivotIntake;
import org.usfirst.frc.team399.Commands.RunIntakeWheels;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class TwoCubeScale extends CommandGroup{
	static String data = DriverStation.getInstance().getGameSpecificMessage();
	char gameDataChar;
	public  TwoCubeScale(){
		/**Still needs some tuning on the left side
		 * We also need to make some of the sequentials parallels
		 * 
		 * **/
		if(data.length() != 0){
			gameDataChar = data.charAt(0);
			System.out.println(gameDataChar);
			if(gameDataChar == 'L'){
				System.out.println("Left");
			//Two Cubes in the scale			
				this.addSequential(new MotionProfileStraight(138,false,2));
				this.addSequential(new MotionProfileTurn(15,0.5));
	// Lift
				this.addSequential(new MotionProfileStraight(139,false,2));
	//lift
				//this.addSequential(new RunIntakeWheels(0.0,.5));
				this.addSequential(new MotionProfileTurn(-15,0.5));
				this.addSequential(new MotionProfileStraight(24,true,1));
				this.addParallel(new PivotIntake(1,1.25,0));
				this.addSequential(new MotionProfileTurn(-195,1.7));
				//this.addParallel(new RunIntakeWheels(-1,7.0));
				this.addSequential(new MotionProfileStraight(36,false,1));
				this.addSequential(new MotionProfileStraight(12,true,1));
				this.addSequential(new PivotIntake(-1,1.25,0));
				this.addSequential(new MotionProfileStraight(12,true,1));
				this.addSequential(new MotionProfileTurn(-140,1.54));
				this.addSequential(new MotionProfileStraight(24,false,.7));
				
// One Cube in the Scale one cube in the Switch
//				this.addSequential(new MotionProfileStraight(138,false,2));
//				this.addSequential(new MotionProfileTurn(15,0.5));
				// Lift
				//this.addSequential(new MotionProfileStraight(139,false,2));
				//lift
//				this.addSequential(new RunIntakeWheels(0.0,.5));
//				this.addSequential(new MotionProfileTurn(-15,0.5));
//				this.addSequential(new MotionProfileStraight(24,true,1));
//				this.addParallel(new PivotIntake(1,1,0));
//				this.addSequential(new MotionProfileTurn(-195,1.7));
//				this.addParallel(new RunIntakeWheels(-1,2.0));
//				this.addSequential(new MotionProfileStraight(36,false,1));
//				this.addSequential(new MotionProfileStraight(12,true,1));
//				this.addSequential(new PivotIntake(-1,1,0));
//				this.addParallel(new PivotIntake(.6,.35,0));
//				this.addSequential(new MotionProfileStraight(28,false,1));
//				this.addSequential(new RunIntakeWheels(1,.5));
//				this.addSequential(new MotionProfileStraight(28,true,1));
//				this.addParallel(new PivotIntake(1,.75,0));
//				this.addSequential(new MotionProfileTurn(-15,.5));
//				this.addParallel(new RunIntakeWheels(-1,2.0));
//				this.addSequential(new MotionProfileStraight(24,false,1));
				
			}else if(gameDataChar == 'R'){
				System.out.println("Right");
				//Scores one cube in the scale need to add lift
				this.addSequential(new MotionProfileStraight(226,false,2.3));
				this.addSequential(new MotionProfileTurn(90,1.23));
				this.addSequential(new MotionProfileStraight(182,false,2.3));
				this.addSequential(new MotionProfileTurn(-110,1.3));
				this.addSequential(new MotionProfileStraight(24,false,1));
				this.addParallel(new PivotIntake(0.9,0.28,0));
				//this.addSequential(new RunIntakeWheels(1.0,1));
				
				// from this point on untuned
				//ajust angle 210 maybe
				
//				this.addSequential(new MotionProfileTurn(215,1.7));
//				this.addParallel(new PivotIntake(1,1,0));
//			    this.addParallel(new RunIntakeWheels(-1.0,2.65));
//			    this.addSequential(new MotionProfileStraight(72,false,1.4));
//			    this.addSequential(new MotionProfileStraight(12,true,.5));
//			    this.addParallel(new PivotIntake(-1,.75,0));
//				this.addSequential(new RunIntakeWheels(1.0,1));


			}
		}
		
		
	}
}
