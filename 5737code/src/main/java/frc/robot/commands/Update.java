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
import frc.robot.RobotMap;

/**
 * Runs every iteration
 * Updates the position of robot and accesses values for all sensors
 */

public class Update extends InstantCommand {

  public Update() {
    super();
  }

  // Called once when the command executes
  @Override
  protected void initialize() {


    /**Get pigeon values and update values for drive base
      *First check connection to pigeon to prevent error. If error, stop all commands
      *Positive is clockwise, negative is counter clockwise
    */
    PigeonState state = Robot.driveBase.pigeon.getState();
    if (state == PigeonState.Ready) { 
      double ypr[] = new double [3];

      Robot.driveBase.pigeon.getYawPitchRoll(ypr); //Make sure angle is always between -180 and 180
      if (ypr[0] >= 180) {
        ypr[0] -= 360;
        Robot.driveBase.pigeon.setYaw(ypr[0]); //And update value to drivebase
      }
      else if (ypr[0] < -180) {
        ypr[0] += 360;
        Robot.driveBase.pigeon.setYaw(ypr[0]);
      }
      Robot.driveBase.angle = ypr[0];
    } else { //Connection issue with pigeon
      System.out.println("Something just ****** the connection");
      Robot.stop();
    }

    //Obtaining the movement of robot
    /** This code takes in the speed of the four seperate mecanum wheels and proceeds 
        to estimate a new position for said wheels 
     * a - front left
     * b - front right
     * c - back left
     * d - back right
     * Algorithm courtesy of https://research.ijcaonline.org/volume113/number3/pxc3901586.pdf
    */
    //First obtain wheel angular velocity in radians travelled in last 0.02 seconds
    double a,b,c,d;
    a = Math.toRadians(Robot.driveBase.leftFrontTalon.getSelectedSensorVelocity() * 0.087890625);
    b = Math.toRadians(Robot.driveBase.rightFrontTalon.getSelectedSensorVelocity() * 0.087890625);
    c = Math.toRadians(Robot.driveBase.leftBackTalon.getSelectedSensorVelocity() * 0.087890625);
    d = Math.toRadians(Robot.driveBase.rightBackTalon.getSelectedSensorVelocity() * 0.087890625);
    //0.087890625 is from 360 (fraction to degrees) / 4096 (units per rotation)

    //Now the distance travelled in the last iteration (0.02 seconds)
    double longitudinalDistance = (a+b+c+d) * (RobotMap.wheelRadius/4); //Forward
    double transverseDistance = (-b+c+d-a) * (RobotMap.wheelRadius/4); //Right

    //And add this value to the x and y values of the robot drive, in centimeters (Imperial is gay)
    //Of course the value calculated is in reference to the robot's local frame
    //Thus we need to use the orthogonal rotation matrix to convert this value to the robots global frame
    //Note - 
    //// Current version of this program uses heading measured by the pigeon.
    double e = Math.toRadians(Robot.driveBase.angle + 90);
    double globalY = Math.cos(e) *  longitudinalDistance - Math.sin(e) * transverseDistance;
    double globalX = Math.sin(e) *  longitudinalDistance + Math.cos(e) * transverseDistance;

    //Finally update this value to the robot drivebase
    Robot.driveBase.xCoordinate += globalX;
    Robot.driveBase.yCoordinate += globalY;

  }

}
