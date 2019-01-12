/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import frc.robot.RobotMap;
import frc.robot.commands.ManualDrive;

/**
 * Drive base subsystem for robot. 
 */

public class DriveBase extends Subsystem {


  public WPI_TalonSRX leftFrontTalon = new WPI_TalonSRX(RobotMap.leftFrontMotor);
  public WPI_TalonSRX leftBackTalon = new WPI_TalonSRX(RobotMap.leftBackMotor);
  public WPI_TalonSRX rightFrontTalon = new WPI_TalonSRX(RobotMap.rightFrontMotor);
  public WPI_TalonSRX rightBackTalon = new WPI_TalonSRX(RobotMap.rightBackMotor);

  public MecanumDrive mecanumDrive = new MecanumDrive(leftFrontTalon, leftBackTalon, rightFrontTalon, rightBackTalon);

  public void ManualDrive (float x, float y, float z){
    mecanumDrive.driveCartesian(y, x, z);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ManualDrive());
  }
}
