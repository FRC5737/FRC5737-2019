/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Ultrasonic;
import frc.robot.RobotMap;
import frc.robot.commands.ManualElevator;

/**
 * This is the elevator class, but with ultrasonic sensor for PID
 */
public class ElevatorPID extends PIDSubsystem {
  /**
   * Init all variables - ultrasonic and motor
   */

   //Initialize two motors - One slaved to the other
  public WPI_TalonSRX elevatorMainMotor = new WPI_TalonSRX(RobotMap.elevatorMotor);

  //Initalize two limit switches - one top and one bottom
  public DigitalInput bottomSwitch = new DigitalInput(RobotMap.limitSwitchBottom);
  public DigitalInput topSwitch = new DigitalInput(RobotMap.limitSwitchTop);

  //Ultrasonic sensor
  public Ultrasonic ultra = new Ultrasonic(RobotMap.ultrasonicPing, RobotMap.ultrasonicEcho);
  //Up and down, with checks in case already in position to prevent damage
  public void up(double speed) {
    if (topSwitch.get() == false) {
      elevatorMainMotor.set(ControlMode.PercentOutput,speed);
    } else {
      elevatorMainMotor.set(0.25);
    }
  }

  public void down(double speed) {
    if (bottomSwitch.get() == false) {
      elevatorMainMotor.set(ControlMode.PercentOutput,-speed);
    } else {
      elevatorMainMotor.set(0.25);
    }
  }

  public ElevatorPID() {
    super("ElevatorPID", 0.004, 0, 0.0);
    setSetpoint(500);
    setAbsoluteTolerance(30);
    disable();
  }

  @Override
  public void initDefaultCommand() {
    SmartDashboard.putNumber("Elevator",getPosition());
    ultra.setAutomaticMode(true);
    setDefaultCommand(new ManualElevator());
  }

  @Override
  protected double returnPIDInput() {
    return ultra.getRangeMM();
  }

  @Override
  protected void usePIDOutput(double output) {

    //System.out.println(ultra.getRangeMM());

    if (getSetpoint() < 70) {
      setSetpoint(70);
    } else if (getSetpoint() > 820) {
      setSetpoint(820);
    }

    if (output < 0) {
      down(output*-1);
    } else if (output > 0) {
      up(output);
    }
  }
}
