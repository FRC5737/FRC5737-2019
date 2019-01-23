/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import frc.robot.RobotMap;
import frc.robot.commands.ManualDrive;

/**
 * Drive base subsystem for robot. 
 */

public class DriveBase extends Subsystem {

  //Store the current robot position. Needs to be reset and recalculated whenever possible to maintain accuracy.
  public double xCoordinate = 0;
  public double yCoordinate = 0;
  public double angle = 0; //Positive is clockwise, negative is counter clockwise
  public double velocity = 0;

  public WPI_TalonSRX leftFrontTalon = new WPI_TalonSRX(RobotMap.leftFrontMotor);
  public WPI_TalonSRX leftBackTalon = new WPI_TalonSRX(RobotMap.leftBackMotor);
  public WPI_TalonSRX rightFrontTalon = new WPI_TalonSRX(RobotMap.rightFrontMotor);
  public WPI_TalonSRX rightBackTalon = new WPI_TalonSRX(RobotMap.rightBackMotor);

  /*public Encoder leftFrontEncoder = new Encoder (0, 1, false, EncodingType.k4X);
  public Encoder leftBackEncoder = new Encoder (2, 3, false, EncodingType.k4X);
  public Encoder rightFrontEncoder = new Encoder (4, 5, false, EncodingType.k4X);
  public Encoder rightBackEncoder = new Encoder (6, 7, false, EncodingType.k4X);*/ 
  //Code above is for use with new encoders

  public MecanumDrive mecanumDrive = new MecanumDrive(leftFrontTalon, leftBackTalon, rightFrontTalon, rightBackTalon);

  public void ManualDrive (double x, double y, double z){
    mecanumDrive.driveCartesian(y, x, z,angle);
  }
  
  public void PolarDrive (double magnitude, double angle, double zRotation) {
    mecanumDrive.drivePolar(magnitude, angle, zRotation);
  }

  @Override
  public void initDefaultCommand() {
    //leftFrontTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);

    setDefaultCommand(new ManualDrive());
  }
}
