/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.ManualElevator;

/**
 * The frontal elevator for use with the ball mechanism
 */

public class Elevator extends Subsystem {
  //Initialize two motors - One slaved to the other
  public WPI_TalonSRX elevatorMainMotor = new WPI_TalonSRX(RobotMap.elevatorLeftMotor);
  public WPI_TalonSRX elevatorSlaveMotor = new WPI_TalonSRX(RobotMap.elevatorRightMotor);

  //Initalize two limit switches - one top and one bottom
  public DigitalInput bottomSwitch = new DigitalInput(RobotMap.limitSwitchBottom);
  public DigitalInput topSwitch = new DigitalInput(RobotMap.limitSwitchTop);


  //Up and down, with checks in case already in position to prevent damage
  public void up(double speed) {
    if (topSwitch.get()) {
      elevatorMainMotor.set(ControlMode.PercentOutput,speed);
    } else { 
      elevatorMainMotor.set(0);
    }
  }

  public void down(double speed) {
    if (bottomSwitch.get()) {
      elevatorMainMotor.set(ControlMode.PercentOutput,-speed);
    } else { 
      elevatorMainMotor.set(0);
    }
  }

  @Override
  public void initDefaultCommand() {
    elevatorSlaveMotor.follow(elevatorMainMotor);
    elevatorSlaveMotor.setInverted(InvertType.InvertMotorOutput);

    //Default command
    setDefaultCommand(new ManualElevator());
  }
}