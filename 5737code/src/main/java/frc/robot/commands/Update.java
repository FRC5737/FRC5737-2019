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
 * Runs every iteration, updates robot position and any other required values
 */
public class Update extends InstantCommand {

  public Update() {
    super();
    requires(Robot.driveBase);
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    //Get pigeon values and update values for drive base
  }

}
