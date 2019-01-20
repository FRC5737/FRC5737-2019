/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.Auto;
import frc.robot.subsystems.DriveBase;



public class Robot extends TimedRobot {

  public static OI oi;
  public static final DriveBase driveBase = new DriveBase();
  public static PigeonIMU pigeon = new PigeonIMU(RobotMap.pidgeonPort); 

  Command autonomousCommand;
  SendableChooser<Command> chooser = new SendableChooser<>();


  //This command should be called whenever something ***** up
  public static void stop() {
    Scheduler.getInstance().removeAll(); //First stop all running commands
  }

  @Override
  public void robotInit() {
    oi = new OI();
    autonomousCommand = new Auto();
    SmartDashboard.putData("Auto mode", chooser);

    Robot.driveBase.leftBackEncoder.reset();
    Robot.driveBase.leftFrontEncoder.reset();
    Robot.driveBase.rightBackEncoder.reset();
    Robot.driveBase.rightFrontEncoder.reset();
  }

  @Override
  public void robotPeriodic() {
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
