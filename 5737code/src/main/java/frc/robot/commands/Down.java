package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

/**
 * Robot down
 */
public class Down extends InstantCommand {
  /**
   * No one needs a safety captain
   */
  public Down() {
    super();
    requires(Robot.driveBase);
  }

  @Override
  protected void initialize() {
    Robot.driveBase.climbMove(0);
    Robot.driveBase.backDown();
    Robot.driveBase.frontDown();
  }

}
