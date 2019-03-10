/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.ClawWrist;

public class ManualClaw extends Command {
  public ManualClaw() {
    requires(Robot.clawWrist);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.clawWrist.setSetpoint(Robot.oi.functionStick.getThrottle());
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
    Robot.claw.up(0.0);
  }
}
