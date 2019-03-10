/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
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

  //Pigeon 
  public PigeonIMU pigeon = new PigeonIMU(RobotMap.pidgeonPort);

  public MecanumDrive mecanumDrive = new MecanumDrive(leftFrontTalon, leftBackTalon, rightFrontTalon, rightBackTalon);

  //L3 Climb
  public DoubleSolenoid frontSolenoid = new DoubleSolenoid(RobotMap.climbFrontF, RobotMap.climbFrontR);
  public DoubleSolenoid backSolenoid = new DoubleSolenoid(RobotMap.climbBackF, RobotMap.climbBackR);
  public WPI_TalonSRX leftClimbTalon = new WPI_TalonSRX(RobotMap.climbMotorLeft);
  public WPI_TalonSRX rightClimbTalon = new WPI_TalonSRX(RobotMap.climbMotorRight);

  //Driving with cartesian
  public void ManualDrive (double x, double y, double z){
    mecanumDrive.driveCartesian(y, x, z);
  }
  
  //Driving with polar
  public void PolarDrive (double magnitude, double angle, double zRotation) {
    mecanumDrive.drivePolar(magnitude, angle, zRotation);
  }

  //Lifting up to L3
  public void frontLift() {
    frontSolenoid.set(DoubleSolenoid.Value.kForward);
  }

  public void frontDown() {
    frontSolenoid.set(DoubleSolenoid.Value.kReverse);
  }

  public void backLift() {
    backSolenoid.set(DoubleSolenoid.Value.kForward);
  }

  public void backDown() {
    backSolenoid.set(DoubleSolenoid.Value.kReverse);
  }

  public void climbMove(double speed) {
    leftClimbTalon.set(speed);
    rightClimbTalon.set(-speed);
  }

  @Override
  public void initDefaultCommand() {
    //The encoders we use are https://www.andymark.com/products/srx-magnetic-encoder
    //Init encoders
    //leftFrontTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    //leftBackTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    //rightFrontTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    //rightBackTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    //Adjust frame of each iteration to fit with each loop
    //20 ms
    //leftFrontTalon.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 20);
    //leftBackTalon.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 20);
    //rightFrontTalon.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 20);
    //rightBackTalon.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 20);

    setDefaultCommand(new ManualDrive());
  }
}
