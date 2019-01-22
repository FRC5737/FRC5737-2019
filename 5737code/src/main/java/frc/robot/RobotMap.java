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
  public static int joyPort = 0;

  //Drive Base
  public static int leftFrontMotor = 4;
  public static int leftBackMotor = 1;
  public static int rightFrontMotor = 3;
  public static int rightBackMotor = 2;

  public static double wheelCircumference = 63.84;

  //Sensors
  public static int pidgeonPort = 2;

  //PID
  //Drive Base
  public static double driveP = 0.01;
  public static double driveI = 0.0;
  public static double driveD = 0.0;


}
