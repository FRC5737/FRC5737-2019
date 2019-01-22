/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import java.lang.Math;

public class ManualDrive extends Command {
  public ManualDrive() {
    requires(Robot.driveBase);
  }


  @Override
  protected void initialize() {
  }

  // Drive robot with standard cartesian mecanum drive and squared inputs
  @Override
  protected void execute() {
    double y = Math.pow(Math.abs(Robot.oi.stick.getX()),2);
    if (Robot.oi.stick.getX() < 0) {
      y = -y;
    }
    double x =  -(Math.pow(Math.abs(Robot.oi.stick.getY()),2));
    if (Robot.oi.stick.getY() < 0) {
      x = -x;
    }
    double z = Math.pow(Math.abs(Robot.oi.stick.getZ()),2);
    if (Robot.oi.stick.getZ() < 0) {
      z = -z;
    }
    //System.out.println("Driving");
    Robot.driveBase.ManualDrive(x, y, z);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
