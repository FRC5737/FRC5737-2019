/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class Move extends Command {
  //This command controls the Polar Drive command
  //Set speed to move at, distance to move at what angle, and amount to rotate while moving
  //Please give this command a target angle or rotation of -179 to 180
  private double pMove[] = new double[4];
  private boolean destination;
  private double integral, prevError, derivative, rotationError, adjustedAngle, targetRot = 0.0;
  private double tolerance = 3.0; //Allow 3 degrees of error to rotation.
  private double pDistance = 0.0;
  private double pSpeed = 0.0;
  private double targetRotMinimum = 0.2;

  public Move(double speed, double distance, double angle, double rotation) {
    requires(Robot.driveBase);
    pMove[0] = speed;
    pSpeed = speed;
    pMove[1] = distance;
    pDistance = distance;
    pMove[2]  = angle;
    pMove[3] = rotation;

  }

  @Override
  protected void initialize() {
    destination  = false;
    prevError = 0.0;
    targetRot = 0.0;
    rotationError = 0.0;
    pMove[1] = pDistance;
    pMove[0] = pSpeed;
  }

  @Override
  protected void execute() {

    //Find angle to move to
    adjustedAngle = (pMove[2] + 180) - (Robot.driveBase.angle + 180);
    if (adjustedAngle > 180) {
      adjustedAngle = -1 * (360 - adjustedAngle);
    } else if (adjustedAngle < -180) {
      adjustedAngle = 360 - -1 * adjustedAngle;
    }
    adjustedAngle = - adjustedAngle;
    //Use PID to move at adjusted angle while rotating to target rotation
    rotationError = (pMove[3] + 180) - (Robot.driveBase.angle + 180);
    if (rotationError > 180) {
      rotationError = -1 * (360 - rotationError);
    } else if (rotationError < -180) {
      rotationError = 360 - -1 * rotationError;
    } //No shit  -pMove[3] is setpoint, Robot.driveBase should contain angle
    if (Math.abs(rotationError) > tolerance) {
      integral += (rotationError * .02);
      derivative = (rotationError - prevError) / .02;
      prevError = rotationError; //Uses algo found on https://frc-pdr.readthedocs.io/en/latest/control/pid_control.html#using-pid-on-your-robot
      targetRot = RobotMap.driveP * rotationError + integral * RobotMap.driveI + derivative * RobotMap.driveD;
    } 
    else {
      targetRot = 0.0;
    }
    
    SmartDashboard.putNumber("Rotation Error", rotationError);
    SmartDashboard.putNumber("Target Rotation", targetRot);
    SmartDashboard.putNumber("Distance", pMove[1]);

    if (targetRot != 0 && Math.abs(targetRot) < targetRotMinimum) {
      if (targetRot > 0){
        targetRot = targetRotMinimum;
      } else {
        targetRot = - targetRotMinimum;
      }
    } //Make sure target rot is above a minimum so robot does not get stuck

    //Driving the robot through polar drive
    Robot.driveBase.PolarDrive(pMove[0], adjustedAngle, targetRot * -1);
    //pMove[1] -= Robot.driveBase.velocity * .02; //Decrease the distance needed to be moved by the speed of the robot multiplied by the time of each iteration
    pMove[1] -= pMove[0] * RobotMap.maxSpeed * 0.02; //Temp "stupid" distance estimate
    SmartDashboard.putNumber("Distance2", pMove[1]);
    if (targetRot == 0.0 && pMove[1] <= 0) {
      destination = true;
    } else if (pMove[1] <= 0) {
      pMove[0] = 0;
    } //Check if destination reached

  }

  @Override
  protected boolean isFinished() {
    return destination;
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
  }
}
