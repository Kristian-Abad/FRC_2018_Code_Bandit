package org.usfirst.frc.team399.Auton;

import org.usfirst.frc.team399.Commands.MPStraight;
import org.usfirst.frc.team399.Commands.MPStraightAndControlSystems;
import org.usfirst.frc.team399.Commands.MPTurn;
import org.usfirst.frc.team399.Commands.PivotElevatorOutSetPosition;
import org.usfirst.frc.team399.Commands.RunElevatorWheels;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class LinearScale extends CommandGroup{
	String gameData = DriverStation.getInstance().getGameSpecificMessage();
	
	public LinearScale(char position){
		if(gameData.length() != 0){
			if(position =='L'){
//				this.addParallel(new PivotElevatorOutSetPosition('0','2', 1.0));
				this.addSequential(new MPStraightAndControlSystems(245.75, 
																   false,
																   'N',
																   'N',
																   0.0,
																   'O',
																   3.280620,
																   0.001));
//				this.addSequential(new MPTurn(-90,0.0));
//				this.addSequential(new RunElevatorWheels('O', 1.0));
			}else if(position == 'R'){
//				this.addParallel(new PivotElevatorOutSetPosition('0','2', 1.0));
				this.addSequential(new MPStraightAndControlSystems(245.75, 
						   										   false,
						   										   'N',
						   										   'N',
						   										   0.0,
						   										   'O',
						   										   3.280620,
						   										   0.001));
//				this.addSequential(new MPTurn(90,0.0));
//				this.addSequential(new RunElevatorWheels('O', 1.0));
			}
		}
		
	}
}
