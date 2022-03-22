package org.usfirst.frc.team399.Config;

import org.usfirst.frc.team399.Utilities.TrajectoryGenerator;

public class Constants {
	static TrajectoryGenerator generator = new TrajectoryGenerator(Subsystems.Drivetrain.Max_Velocity,Subsystems.Drivetrain.Max_Acceleration);
	
	public class Wheel_Constants{
		//490 pulses in 1 revolution
		public static final double Circumference_of_Wheel = 18.849555921538759430775860299677;
		public static final int Distance_Between_Wheels = 28;
		// 36/942.75
		public static final double Inches_Per_Pulse = 0.03818615751789976133651551312649;

		
	}
	
	public class Subsystems{
		public class Elevator{
			public static final double Circumference_Of_Drum = 2 * Math.PI * 1.25;
			public static final double Switch_Position = 18.375;
			public static final double Scale_Position = 75.5;
			
			public static final double Max_Velocity = 45.00995802303778;
			public static final double Max_Acceleration = 45.00995802303778/1.7575290000000003;
			public static final double Distance_Per_Pulse = Circumference_Of_Drum/485.25;
			
			public static final double k_Velocity = 0.02221730576794056545277056690485;
			public static final double k_Acceleration = 0.03904755918902282072483413206388;
			public static final double k_Proportional = 0.0;
			public static final double k_Integral = 0.0;
			public static final double k_Derivative = 0.0;
			
			public static final double k_Hold_Output = 0.1;
			public static final double k_Pivot_Hold_Input = -0.11;
			
			public static final double Time_Transition_0_To_1 = 0.0;
			public static final double Time_Transition_0_To_2 = 0.0;
			public static final double Time_Transition_1_To_0 = 0.0;
			public static final double Time_Transition_2_To_0 = 0.0;
			public static final double Time_Transition_1_To_2 = 0.0;
			public static final double Time_Transition_2_To_1 = 0.0;
		}
		
		public class Drivetrain{
			public static final int Peak_Current = 40;
			public static final int Voltage_Compensation = 12;
			/**
			 * 3/2/18 Max vel set to 161.33403355175406
			 * 3/2/18 Max accel set to 161.33403355175406/1.4020270000000001
			 * **/
			public static final double Max_Velocity = 161.33403355175406;
			public static final double Max_Acceleration = 161.33403355175406/1.4020270000000001;
			//Bandit
//			public static final double k_Velocity = 0.01;
//			public static final double k_Acceleration = 0.0;
			//Practice Bot
			public static final double k_Velocity = 1/Max_Velocity;
			public static final double k_Acceleration = 0.4/Max_Acceleration; //0.1/Max_Acceleration;
			
			public static final double k_Proportional = 0.05;
			public static final double k_Integral = 0.0000000;
			public static final double k_Derivative = 0.001;
			
			public static final double k_Tangential_Velocity = 1/Max_Velocity;
			public static final double k_Perpendicular_Acceleration = 0.6/Max_Acceleration;
			
			public static final double k_Proportional_T = 0.08;
			public static final double k_Integral_T = 0.0;
			public static final double k_Derivative_T = 0.001;

			
			public static final int Raw_Units_Per_Revolution = 4096;
			
		}
	}
	
	public class AutoStrings{
		
		public static final String kTCS = "Two Cube Switch";
		public static final String kCADB = "Cheap And Dirty Baseline";
		public static final String kSwitch = "Switch";
		public static final String kDoNothing = "Do Nothing";
		public static final String k3CS = "Three Cube Switch";
		public static final String kPositionLeftScale = "Position: Left Auto: Scale";
	}
	
	public class MotionProfile{
		public static final int k_Timeout = 10;
		public static final int Motion_Profile_Update_Rate = 25;
		public static final int Minimum_Num_Of_Points = 5;
		
		public class Presets {
			public final double[][] Turn_90 = generator.generateTrajectoryForTurning(Wheel_Constants.Distance_Between_Wheels,90);
		}
		
	}
	
	public class Ports{

		public class Controls{
			public static final int Driver_Joystick_1 = 0;
			public static final int Driver_Joystick_2 = 1;
			public static final int Driver_Joystick_OperatorGamepad = 2;
		}
		
		public class Drivetrain{
			//Bandit ports
			public static final int Left_1_ID = 1;
			public static final int Left_2_ID = 2;
			public static final int Right_1_ID = 7;
			public static final int Right_2_ID = 8;
			
//			public static final int Left_1_ID = 8;
//			public static final int Left_2_ID = 9;
//			public static final int Right_1_ID = 1;
//			public static final int Right_2_ID = 2;
			
			public static final int Left_Encoder_A = 0;
			public static final int Left_Encoder_B = 1;
			public static final int Right_Encoder_A = 2;
			public static final int Right_Encoder_B = 3;

			
		}
		
		public class Elevator{
			
//			public static final int Elevator_1_ID = 4;
//			public static final int Elevator_2_ID = 7;
//			public static final int Wheels_1_ID = 5;
//			public static final int Wheels_2_ID = 13;
//			public static final int Pivot_ID = 12;
			
			//Bandit
			public static final int Pivot_ID = 5;
			public static final int Elevator_1_ID = 9;
			public static final int Wheels_1_ID = 6;
			public static final int Wheels_2_ID = 12;
			
//			public static final int Elevator_2_ID = 10;
		}
		
		public class Climber{
//			public static final int Pully_ID = 6;
//			public static final int Winch_1_ID = 10;
//			public static final int Winch_2_ID = 11;
//			public static final int Winch_3_ID = 3;
			
			//For Bandit
			public static final int Pully_ID = 11;
			public static final int Winch_1_ID = 3;
			public static final int Winch_2_ID = 4;
			public static final int Winch_3_ID = 10;
		}
	}
}
