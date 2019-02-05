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

  //Drive Base
    //Drive base actuators
  public static int leftFrontMotor = 4;
  public static int leftBackMotor = 1;
  public static int rightFrontMotor = 3;
  public static int rightBackMotor = 2;
    //Drive base constants
  public static double driveP = 0.002;
  public static double driveI = 0.0;
  public static double driveD = 0.0;
  public static double maxSpeed = 300.0; //Replaced with encoder
  public static double wheelRadius = 10.16; // 8 Inch wheel
    //Drive base sensors
  public static int pidgeonPort = 2;

  //Elevator
    //Elevator actuators
  public static int elevatorLeftMotor = 5;
  public static int elevatorRightMotor = 6;
    //Elevator sensors
    public static int limitSwitchBottom = 0;
    public static int limitSwitchTop = 1;



}
