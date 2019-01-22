/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.ctre.phoenix.sensors.PigeonIMU.PigeonState;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

/**
 * Runs every iteration, updates robot position and any other required values
 */
public class Update extends InstantCommand {

  public Update() {
    super();
  }

  // Called once when the command executes
  @Override
  protected void initialize() {

    //Debuggings
    //System.out.println("Update starting");

    //Get pigeon values and update values for drive base
    PigeonState state = Robot.pigeon.getState();
    if (state == PigeonState.Ready) { //Safe programming ftw
      double ypr[] = new double [3];

      Robot.pigeon.getYawPitchRoll(ypr); //Make sure angle is always between -180 and 180
      if (ypr[0] >= 180) {
        ypr[0] -= 360;
        Robot.pigeon.setYaw(ypr[0]);
      }
      else if (ypr[0] < -180) {
        ypr[0] += 360;
        Robot.pigeon.setYaw(ypr[0]);
      }
      Robot.driveBase.angle = ypr[0];
    } 

    //Now we just have to get the speed of the robot 
    //Now in theory the speed of the mecanum movement is stable at the speed of one rotation
    //However there is the issue of angular velocity

    else { //Connection issue with pigeon
      System.out.println("Something just ****** the connection");
      Robot.stop();
    }
  }

}
