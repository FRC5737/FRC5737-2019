/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.*;
/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	//Main joystick for driver
  public Joystick driveStick = new Joystick(RobotMap.lowJoyPort);
  public JoystickButton mainButton1 = new JoystickButton(driveStick, 1);
	public JoystickButton mainButton2 = new JoystickButton(driveStick, 2);
	public JoystickButton mainButton3 = new JoystickButton(driveStick, 3);
	public JoystickButton mainButton4 = new JoystickButton(driveStick, 4);
	public JoystickButton mainButton5 = new JoystickButton(driveStick, 5);
	public JoystickButton mainButton6 = new JoystickButton(driveStick, 6);
	public JoystickButton mainButton7 = new JoystickButton(driveStick, 7);
  public JoystickButton mainButton8 = new JoystickButton(driveStick, 8);

	//Seperate joystick for completing tasks
	public Joystick functionStick = new Joystick(RobotMap.highJoyPort);
  public JoystickButton funcButton1 = new JoystickButton(functionStick, 1);
	public JoystickButton funcButton2 = new JoystickButton(functionStick, 2);
	public JoystickButton funcButton3 = new JoystickButton(functionStick, 3);
	public JoystickButton funcButton4 = new JoystickButton(functionStick, 4);
	public JoystickButton funcButton5 = new JoystickButton(functionStick, 5);
	public JoystickButton funcButton6 = new JoystickButton(functionStick, 6);
	public JoystickButton funcButton7 = new JoystickButton(functionStick, 7);
	public JoystickButton funcButton8 = new JoystickButton(functionStick, 8);
	public JoystickButton funcButton9 = new JoystickButton(functionStick, 9);
	public JoystickButton funcButton10 = new JoystickButton(functionStick, 10);
	public JoystickButton funcButton11 = new JoystickButton(functionStick, 11);
	public JoystickButton funcButton12 = new JoystickButton(functionStick, 12);

  public OI() {
		//funcButton1.whenPressed(new ClawIn());
		//funcButton2.whenPressed(new ClawOut());
		//funcButton3.whenPressed(new ClawOut2());
		//funcButton8.whenPressed(new PigeonReset());
		//funcButton11.whenPressed(new Setpoint1());
		//funcButton12.whenPressed(new Setpoint2());
		funcButton1.whenPressed(new Lift());
		funcButton2.whenPressed(new Down());
	}
}
