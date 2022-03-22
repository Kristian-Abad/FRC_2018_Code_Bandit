package org.usfirst.frc.team399.Utilities;
//*******************************************************************
// Dear CompileJava users,
//
// CompileJava has been operating since 2013 completely free. If you
// find this site useful, or would otherwise like to contribute, then
// please consider a donation (link in 'More Info' tab) to support
// development of the new CompileJava website (stay tuned!).
//
// Most sincerely, Z.
//*******************************************************************

import java.lang.Math; // headers MUST be above the first class

import org.usfirst.frc.team399.Config.Constants;

// one class needs to have a main() method
public class TrajectoryGenerator{
	
  double position1, position2;
  double r_Max_Velocity, r_Max_Acceleration;
  int updateRateInMs = 25;
  
  
  public TrajectoryGenerator(double max_Velocity, double max_Acceleration){
  	r_Max_Velocity = max_Velocity;
    r_Max_Acceleration = max_Acceleration;
  }
  
  public TrajectoryGenerator(double maxVelocity, double maxAcceleration, double pos1,double pos2){
	  r_Max_Velocity = maxVelocity;
	  r_Max_Acceleration = maxAcceleration;
	  position1 = pos1;
	  position2 = pos2;
  }
  
  /**
   * @param d - The displacement value to where you want to go taken from the
   * 			parameter in the generateTrajectory method
   * 
   * We need to find the max velocity that we will reach given how displaced we want to be from where we initially were.
   * If the math seems a little funky when rearranging the kinematic equations view the explanation below
   * **/
  public double getMaxVelocity(double d){
/** If you are wondering why it's not V_f = (2 * a * d)^1/2, it's because when this generator generates a 
 *  triangular profile when your distance is small enough to where you can't fit the "rectangle" portion of the trapezoid, 
 *  you'll notice that just accelerating up to the top of the triangle, will leave you with getting to your desired 
 *  position or displacement while only getting through half of the triangle. To fix this, we remove the 2 and leave
 *  acceleration * displacement. To further explain why this works, that the area under the shape gives
 *  displacement where A = 1/2 * base * height. However since we have two 2 triangles however, we can multiply by 2 thus 
 *  eliminating the 1/2.
 * 
 * 
**/	  
  	double calculated_Max_Velocity = Math.sqrt(r_Max_Acceleration*d);
    
    return Math.min(r_Max_Velocity,calculated_Max_Velocity);
  }
  
  /**
   * @param displacement - The displacement value to where you want to go
   * @param invert - Flip the profile under the x-axis or multiply the velocity and acceleration	
   * 				 values by -1
   * 
   * This method takes in the distance you want to go and whether or not to invert the values.
   * **/
  public double[][] generateTrajectory(double displacement, boolean invert, double angle){
	  /**
	   * @param d_Accel - displacement during acceleration ("the ramp up" part of the trapezoidal motion profile)
	   * @param d_MaxVel - displacement when the robot reaches its maximum velocity ("cruise velocity")
	   * @param d_Decel - displacement during deceleration or negative acceleration ("the ramp down" part of the trapezoidal motion profile)
	   * @param d_Total - The total amount of displacement or the sum of d_Accel, d_MaxVel, d_Decel
	   * 
	   * @param t_Accel - The time that will elapse once the robot accelerates during the "incline" part of the motion profile
	   * @param t_MaxVel - The time that will elapse when the robot reaches its maximum velocity
	   * @param t_Decel - The time that will
	   * @param t_Total - The total time the motion profile will take to run
	   * 
	   * **/
    double time;
    double velocity,acceleration,curr_Displacement;
  	double max_Velocity = getMaxVelocity(displacement);
    
    double t_Accel = max_Velocity/r_Max_Acceleration;
    
    double t_Decel = t_Accel;
    
    double d_Accel = max_Velocity/2 * t_Accel;
    double d_Decel = d_Accel;
    double d_MaxVel = //(displacement - (d_Accel + d_Decel) < 0 ? 0 :
    								       displacement - (d_Accel + d_Decel);
    double d_Total = d_Accel + d_Decel + d_MaxVel;
    
    double time_Cruise_Velocity = d_MaxVel/max_Velocity;
    double time_Total = t_Accel + time_Cruise_Velocity + t_Decel;
    

    double trajectory_Points_Acceleration = (t_Accel*1000)/updateRateInMs;
    double trajectory_Points_Cruise_Velocity = (time_Cruise_Velocity*1000)/updateRateInMs;
    double trajectory_Points_Deceleration = (t_Decel*1000)/updateRateInMs;
    int trajectory_Points_Total =  (int) ((int) trajectory_Points_Acceleration + trajectory_Points_Deceleration + trajectory_Points_Cruise_Velocity);
    double trajectory_Points_Accel_and_Cruise = trajectory_Points_Acceleration + trajectory_Points_Cruise_Velocity;
    double time_Per_Point = time_Total/trajectory_Points_Total;
    double point_Per_Time = trajectory_Points_Total/time_Total;
    double[][] profile = new double[trajectory_Points_Total][4];
    
    //Part of the old print statements for visualization
//    System.out.printf("%-22s%-22s%-22s%-22s%-22s\n", "Velocity","Acceleration","Displacement","Time","Point");
    for(int i = 0; i <= trajectory_Points_Total - 1; i++){
    	
    	double time_At_Point =  (i == 0 ? 0 :
    							 i * time_Per_Point);

    	acceleration =  (i == 0 ? 0 :
    									    i == trajectory_Points_Total - 1 ? 0 :
    									    i < trajectory_Points_Acceleration ? r_Max_Acceleration :
    									    i > trajectory_Points_Acceleration && 
    									    i < trajectory_Points_Acceleration + trajectory_Points_Cruise_Velocity || i == Math.round(trajectory_Points_Acceleration)? 0 :
    									    i > trajectory_Points_Acceleration + trajectory_Points_Cruise_Velocity ? -1 * r_Max_Acceleration: 0);
      
    	velocity =  (i == 0 ? 0 :
				    				    i == trajectory_Points_Total - 1 ? 0 :
				    				    i < trajectory_Points_Acceleration ? r_Max_Acceleration * time_At_Point :
				    				    i > trajectory_Points_Acceleration && 
				    				    i < trajectory_Points_Acceleration + trajectory_Points_Cruise_Velocity || i == Math.round(trajectory_Points_Acceleration)? max_Velocity :
				    				    i > trajectory_Points_Acceleration + trajectory_Points_Cruise_Velocity ? -1 * 
				    				    		acceleration * (trajectory_Points_Total - i) * time_Per_Point : 0);
      
    	curr_Displacement = (i == 1 ? 0 :
    		  			     i < trajectory_Points_Acceleration ? velocity * time_At_Point * 0.5 :
    	                     i > trajectory_Points_Acceleration && 
    	                     i < trajectory_Points_Acceleration + trajectory_Points_Cruise_Velocity || 
    	                     i == Math.round(trajectory_Points_Acceleration) ? d_Accel + 
    	                		   max_Velocity * (time_At_Point - t_Accel):
  		  				     i > trajectory_Points_Acceleration + trajectory_Points_Cruise_Velocity ? displacement - 
  		  						   velocity * 0.5 * (time_Total - time_At_Point) : 0);
    	double instantaneousAngle = (angle < 0 ? -1 : 1) * (curr_Displacement/Constants.Wheel_Constants.Distance_Between_Wheels * (180/Math.PI));
    	profile[i][0] = (invert ? -1 : 1) * velocity;
    	profile[i][1] = (invert ? -1 : 1) * acceleration ;
    	profile[i][2] = curr_Displacement;
    	profile[i][3] = instantaneousAngle;
//    	profile.add(new double[]{velocity,acceleration,curr_Displacement});
//  		  				   i > trajectory_Points_Acceleration && trajectory_Points_Cruise_Velocity == 0 ? velocity * 0.5 * (time_Total - time_At_Point) : 
//  		  				   i < trajectory_Points_Acceleration && trajectory_Points_Cruise_Velocity == 0 ? velocity * time_At_Point * 0.5 : 0) ; 
    	
    	//Old print statements to actually see the multi-dimensional array or the values spaced out along with the array index
//      System.out.printf("%-22f%-22f%-22f%-22f%-22d\n", velocity,acceleration,curr_Displacement,time_At_Point,i);
//      System.out.printf("%-1s%-1f%-1s%-1f%-1s%-1f%-1s%-1s\n","{",velocity,",",acceleration,",",curr_Displacement,"}",",");
//      System.out.printf("%-1s%-1f%-1s%-1f%-1s%-1s\n","{",getRevolutions(curr_Displacement),",",getRevolutions(velocity) * 60,"}",",");
    
    }
    //Old print statements just for visualization
//    System.out.print("\n\n");
//    
//    System.out.printf("%-22s%-22s%-22s%-22s", "Accel Points:",
//    									 "CV Points:",
//    									 "Decel Points:",
//    									 "Total Points:");
//    System.out.print("\n\n");
//    System.out.printf("%-22f%-22f%-22f%-22d", trajectory_Points_Acceleration,
//    										  trajectory_Points_Cruise_Velocity,
//    										  trajectory_Points_Deceleration,
//    										  trajectory_Points_Total);
//    System.out.print("\n\n");
//    System.out.printf("%-22s%-22s%-22s%-22s", "Accel Time:",
//			 							 "CV Time:",
//			 							 "Decel Time:",
//			 							 "Total Time:");
//    System.out.print("\n\n");
//    System.out.printf("%-22f%-22f%-22f%-22f", t_Accel,
//    										  time_Cruise_Velocity,
//    										  t_Decel,
//    										  time_Total);
//    System.out.print("\n\n");
//    System.out.printf("%-22s%-22s%-22s", "Accel Displacement:",
//			 							 "CV Displacement:",
//			 							 "Decel Displacement:");
//    System.out.print("\n\n");
//    System.out.printf("%-22f%-22f%-22f", d_Accel,
//    										  d_MaxVel,
//    										  d_Decel);

    return profile;
    
  }
  
  public double[][] generateTrajectoryForTurning(double radius, double angle){
	  double arcLength = radius * Math.abs(angle * (Math.PI/180));
	  double[][] profile = generateTrajectory(arcLength,false,angle);
	  return profile;
  }
  
  //Pretty sure I never used this method
  public double[][][] getTrajectoryTransitions(){
	  double differenceBetweenPositions = Math.abs(position2) - Math.abs(position1);
	  double[][] transition0To1 = generateTrajectory(position1,false,0);
	  double[][] transition0To2 = generateTrajectory(position2,false,0);
	  double[][] transition1To0 = generateTrajectory(position1,true,0);
	  double[][] transition2To0 = generateTrajectory(position2,true,0);
	  double[][] transition1To2 = generateTrajectory(differenceBetweenPositions, false,0);
	  double[][] transition2To1 = generateTrajectory(differenceBetweenPositions,true,0);
	  double[][][] transitions = new double[][][]{transition0To1, 
		  										  transition0To2, 
		  										  transition1To0, 
		  										  transition2To0, 
		  										  transition1To2, 
		  										  transition2To1};
	  return transitions;
  }
  
  public double getRevolutions(double disp){
//  	double pulses_Per_Inch = 9.8952855923;
//    double revolutions_Per_Pulse = 1/357.5;
  	  double revolutions = disp/35.342917352885173932704738061894;
  	  return revolutions;
//    double revolutions = ((disp * pulses_Per_Inch) * revolutions_Per_Pulse);
//    return revolutions;
  }
  
  
  
}