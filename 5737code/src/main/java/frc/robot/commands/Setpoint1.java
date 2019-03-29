/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;
import frc.robot.subsystems.ElevatorPID;

/**
 * Setpoint for 
 */
public class Setpoint1 extends InstantCommand {
  /**
   * Hot keys for driver to several predefined positions
   */
  public Setpoint1() {
    super();
    requires(Robot.elevator);
    requires(Robot.clawWrist);
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    Robot.elevator.setSetpoint(500);
    Robot.clawWrist.setSetpoint(8);
  }

}
