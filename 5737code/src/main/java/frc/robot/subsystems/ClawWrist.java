/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.ManualClaw;
import edu.wpi.first.wpilibj.AnalogPotentiometer;

/**
 * This subsystem controls the angle of the claw
 */
public class ClawWrist extends PIDSubsystem {

  public WPI_TalonSRX clawMotor = new WPI_TalonSRX(RobotMap.clawMotor);
  public AnalogPotentiometer clawPot = new AnalogPotentiometer(RobotMap.clawPotentiometer,270,10);

  public double clawZero;

  public ClawWrist() {
    super("ClawWrist", 0.025, 0, 0);
    setAbsoluteTolerance(3);
    getPIDController().setContinuous(false);
    enable();
  }

  @Override
  public void initDefaultCommand() {
    SmartDashboard.putNumber("ClawAngle", getPosition());

    clawZero = clawPot.get();
    setDefaultCommand(new ManualClaw());
  }

  @Override
  protected double returnPIDInput() {
    return (clawPot.get()-clawZero)*-1;
  }

  @Override
  protected void usePIDOutput(double output) {
    //System.out.println("Claw");
    //System.out.println(getPosition());
    clawMotor.pidWrite(output);
  }
}
