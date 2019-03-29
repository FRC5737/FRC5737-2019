/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ManualElevator extends Command {
  public ManualElevator() {
    requires(Robot.elevator);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    double s = Robot.oi.functionStick.getY();
    double speed =  Math.pow(Math.abs(s),2);
    if (Math.abs(speed) < 0.3) {
      speed = 0;
    }
    if (s < 0) {
      Robot.elevator.down(speed);
      //Robot.elevator.setSetpoint(Robot.elevator.getSetpoint()+speed*5);
    } else {
      //Robot.elevator.setSetpoint(Robot.elevator.getSetpoint()-speed*5);
      Robot.elevator.up(speed);
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
