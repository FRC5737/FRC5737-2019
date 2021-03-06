/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ManualClaw extends Command {

  double prevAngle;

  public ManualClaw() {
    requires(Robot.clawWrist);
  }

  @Override
  protected void initialize() {
    prevAngle = 0.0;
  }

  @Override
  protected void execute() {
    double angle = ((Robot.oi.functionStick.getThrottle()*-1+1)*50)+4;
    if (Math.abs(angle - prevAngle) > 10) {
      //System.out.println("Moving");
      prevAngle = angle;
      Robot.clawWrist.setSetpoint(angle);
    }
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
  }
}
