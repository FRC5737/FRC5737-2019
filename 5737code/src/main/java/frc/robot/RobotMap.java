/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  //Joystick
  public static int lowJoyPort = 0;
  public static int highJoyPort = 1;

  //Compressor
  public static int compressor = 0;

  //Drive Base
    //Drive base actuators
  public static int leftFrontMotor = 1;
  public static int leftBackMotor = 2;
  public static int rightFrontMotor = 3;
  public static int rightBackMotor = 4;
  public static int climbMotorLeft = 5;
  public static int climbMotorRight = 6;
  public static int climbFrontF = 6;
  public static int climbFrontR = 7;
  public static int climbBackF = 0;
  public static int climbBackR = 1;
    //Drive base constants
  public static double driveP = 0.002;
  public static double driveI = 0.0;
  public static double driveD = 0.0;
  public static double wheelRadius = 10.16;
    //Drive base sensors
  public static int pidgeonPort = 0;

  //Elevator
    //Elevator actuators
  public static int elevatorMotor = 7;
    //Elevator sensors
  public static int limitSwitchBottom = 7;
  public static int limitSwitchTop = 8;

  //Claw
    //Claw actuators
  public static int clawMotor = 8;
  public static int leftSoleF = 4;
  public static int leftSoleR = 5;
  public static int centerSoleF = 3;
  public static int centerSoleR = 2;
  public static int rightSoleF = 4;
  public static int rightSoleR = 5;
    //Claw sensors
  public static int clawPotentiometer = 0;

}
