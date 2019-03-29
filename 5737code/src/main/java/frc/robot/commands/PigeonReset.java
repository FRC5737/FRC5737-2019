/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

/**
 * Reset pigeon angle to be 0
 */
public class PigeonReset extends InstantCommand {
  /**
   * RESET!
   */
  public PigeonReset() {
    super();
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    Robot.driveBase.pigeon.setYaw(0);
    Robot.driveBase.xCoordinate = 0;
    Robot.driveBase.yCoordinate = 0;
  }

}
