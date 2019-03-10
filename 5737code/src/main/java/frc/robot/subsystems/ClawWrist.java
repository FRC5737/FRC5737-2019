/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import frc.robot.commands.ManualClaw;
import edu.wpi.first.wpilibj.AnalogPotentiometer;

/**
 * This subsystem controls the angle of the claw
 */
public class ClawWrist extends PIDSubsystem {

  public WPI_TalonSRX clawMotor = new WPI_TalonSRX(RobotMap.elevatorMotor);

  public AnalogPotentiometer clawPot = new AnalogPotentiometer(RobotMap.clawPotentiometer,270,10);
  public double angle;

  public ClawWrist() {
    // Intert a subsystem name and PID values here
    super("ClawWrist", 2, 0, 0);
    setAbsoluteTolerance(0.05);
    getPIDController().setContinuous(false);
    enable();
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ManualClaw());
  }

  @Override
  protected double returnPIDInput() {
    return clawPot.get();
  }

  @Override
  protected void usePIDOutput(double output) {
    clawMotor.pidWrite(output);
  }
}
