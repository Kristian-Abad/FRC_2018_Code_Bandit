package org.usfirst.frc.team399.Auton;

import org.usfirst.frc.team399.Commands.ElevateLift;
import org.usfirst.frc.team399.Commands.MotionProfileStraight;
import org.usfirst.frc.team399.Commands.MotionProfileTurn;
import org.usfirst.frc.team399.Commands.PivotIntake;
import org.usfirst.frc.team399.Commands.RunIntakeWheels;
import org.usfirst.frc.team399.Commands.Wait;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class ScaleClose extends CommandGroup{
	static String data = DriverStation.getInstance().getGameSpecificMessage();
	char gameDataChar;
	public ScaleClose(){
		if(data.length() != 0){
			gameDataChar = data.charAt(1);
			System.out.println(gameDataChar);
			if(gameDataChar == 'L'){
				System.out.println("Left");
				this.addSequential(new Wait(5.0));
				this.addSequential(new MotionProfileStraight(192,false,3.3));//3.3 timeout for 267 in
				//this.addSequential(new ElevateLift(0.5,1.0,0.0));
				//this.addSequential(new MotionProfileTurn(45,1.0));
				//this.addSequential(new RunIntakeWheels(-0.5,0.5,0.0));


			}else if(gameDataChar == 'R'){
				System.out.println("Right");
				this.addSequential(new MotionProfileStraight(192,false,3.3));//3.3 timeout for 267 in


				

			}
		}
	}
}
