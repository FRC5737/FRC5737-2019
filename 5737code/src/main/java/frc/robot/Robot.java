/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.ManualDrive;
import frc.robot.commands.Update;
import frc.robot.subsystems.Claw;
import frc.robot.subsystems.ClawWrist;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.Elevator;



public class Robot extends TimedRobot {

  public static OI oi;

  //Subsystem init
  public static final DriveBase driveBase = new DriveBase();
  public static final Elevator elevator = new Elevator();
  public static final Claw claw = new Claw();
  public static final ClawWrist clawWrist = new ClawWrist();

  public static final Compressor c = new Compressor(RobotMap.compressor);

  Command autonomousCommand;
  Command updateCommand;
  SendableChooser<Command> chooser = new SendableChooser<>();


  //This command should be called whenever something ***** up
  public static void stop() {
    Scheduler.getInstance().removeAll(); //First stop all running commands
  }

  @Override
  public void robotInit() {
    oi = new OI();
    updateCommand = new Update();
    autonomousCommand = new ManualDrive();

    driveBase.pigeon.setAccumZAngle(0);
    driveBase.pigeon.setYaw(0);

    SmartDashboard.putData("Auto mode", chooser);
    SmartDashboard.putData(driveBase);

    c.setClosedLoopControl(true);

    /*Robot.driveBase.leftBackEncoder.reset();
    Robot.driveBase.leftFrontEncoder.reset();
    Robot.driveBase.rightBackEncoder.reset();
    Robot.driveBase.rightFrontEncoder.reset();*/
  }

  @Override
  public void robotPeriodic() {
    updateCommand.start();
  }

  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void autonomousInit() {
    autonomousCommand = chooser.getSelected();

    if (autonomousCommand != null) {
      autonomousCommand.start();
    }
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {

    if (autonomousCommand != null) {
      autonomousCommand.cancel();
    }
  }


  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void testPeriodic() {
  }
}
