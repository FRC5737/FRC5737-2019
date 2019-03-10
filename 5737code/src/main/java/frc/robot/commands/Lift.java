/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class Lift extends Command {

  int timer;

  public Lift() {
    requires(Robot.driveBase);
  }

  @Override
  protected void initialize() {
    timer = 0;
  }

  @Override
  protected void execute() {
    if (timer == 0) {
      Robot.driveBase.backLift();
    }

    if (timer == 25) {
      //Robot.driveBase.climbMove(1);
      Robot.driveBase.frontLift();
    }

    //if (timer == 100) {
      //Robot.driveBase.backDown();
    //}

    //if (timer == 300) {
      //Robot.driveBase.frontDown();
    //}

    //System.out.println(timer);

    timer = timer + 1;
  }

  @Override
  protected boolean isFinished() {

    if (timer >= 5001 ) {
      //Robot.driveBase.climbMove(0);
      return true;
    } else {
      return false;
    }

  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.driveBase.climbMove(0);
  }
}
