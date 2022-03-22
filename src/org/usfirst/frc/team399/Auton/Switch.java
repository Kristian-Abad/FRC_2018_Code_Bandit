package org.usfirst.frc.team399.Auton;

import org.usfirst.frc.team399.Commands.MPStraight;
import org.usfirst.frc.team399.Commands.MPTurn;
import org.usfirst.frc.team399.Commands.MotionProfileStraight;
import org.usfirst.frc.team399.Commands.MotionProfileTurn;
import org.usfirst.frc.team399.Commands.PivotElevatorOutSetPosition;
import org.usfirst.frc.team399.Commands.PivotIntake;
import org.usfirst.frc.team399.Commands.RunElevatorWheels;
import org.usfirst.frc.team399.Commands.RunIntakeWheels;
import org.usfirst.frc.team399.Commands.SetElevatorPosition;
import org.usfirst.frc.team399.Commands.TimedStraight;

import edu.wpi.first.wpilibj.DriverStation;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Switch extends CommandGroup{
	String gameData = DriverStation.getInstance().getGameSpecificMessage();
	char gameDataChar;
	public Switch(){
		/**Once we get out max velocity and max acceleration get times for timeout.
		 * We also need to make some of the sequentials parallels
		 * 
		 * **/
		
		if(gameData.length() != 0){
			gameDataChar = gameData.charAt(0);
			System.out.println(gameDataChar);
			if(gameDataChar == 'L'){
				System.out.println("Left");
				this.addParallel(new PivotIntake(0.9,0.28,0));
				this.addSequential(new MotionProfileStraight(24,false,1.3));
				this.addSequential(new MotionProfileTurn(-35,1.0));
				this.addSequential(new MotionProfileStraight(83,false,2.0));
				this.addSequential(new MotionProfileTurn(15,1.0));
				this.addSequential(new RunIntakeWheels(-0.5,1.0,0.0));
				


			}else if(gameDataChar == 'R'){
				System.out.println("Right");
				this.addParallel(new PivotIntake(0.9,0.28,0));
				this.addSequential(new MotionProfileStraight(24,false,1.3));
				this.addSequential(new MotionProfileTurn(30,1.0));
				this.addSequential(new MotionProfileStraight(78,false,2.2));
				this.addSequential(new MotionProfileTurn(-20,1.0));
				this.addSequential(new RunIntakeWheels(-0.5,1.0,0.0));
			}
		}
		
		
	}
}
