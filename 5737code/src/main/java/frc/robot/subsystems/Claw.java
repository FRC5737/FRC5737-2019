/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Claw mechanism
 */
public class Claw extends Subsystem {
  
  public DoubleSolenoid leftSole = new DoubleSolenoid(RobotMap.leftSoleF, RobotMap.leftSoleR);
  public DoubleSolenoid centerSole = new DoubleSolenoid(RobotMap.centerSoleF, RobotMap.centerSoleR);
  //public DoubleSolenoid rightSole = new DoubleSolenoid(RobotMap.rightSoleF, RobotMap.rightSoleR);

  boolean in = true;

  @Override
  public void initDefaultCommand() {
  }

  public void in() {
    if (in == false) { 
      //Claw in
      leftSole.set(DoubleSolenoid.Value.kForward);
      //rightSole.set(DoubleSolenoid.Value.kForward);
      in = true;
    }
  }

  public void out() {
    if (in == true) { 
      //Claw in
      centerSole.set(DoubleSolenoid.Value.kForward);
      in = false;
      Timer.delay(0.1);
      leftSole.set(DoubleSolenoid.Value.kReverse);
      //rightSole.set(DoubleSolenoid.Value.kReverse);
      Timer.delay(0.5);
      centerSole.set(DoubleSolenoid.Value.kReverse);
    }
  }

  public void out2() {
    if (in == true) { 
      leftSole.set(DoubleSolenoid.Value.kReverse);
      centerSole.set(DoubleSolenoid.Value.kReverse);
      in = false;
    } 
  }

}
