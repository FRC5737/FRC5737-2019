/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.Move;
/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

  public Joystick stick = new Joystick(RobotMap.joyPort);
  public JoystickButton button1 = new JoystickButton(stick, 1);
	public JoystickButton button2 = new JoystickButton(stick, 2);
	public JoystickButton button3 = new JoystickButton(stick, 3);
	public JoystickButton button4 = new JoystickButton(stick, 4);
	public JoystickButton button5 = new JoystickButton(stick, 5);
	public JoystickButton button6 = new JoystickButton(stick, 6);
	public JoystickButton button7 = new JoystickButton(stick, 7);
  public JoystickButton button8 = new JoystickButton(stick, 8);

  public OI() {
		button1.whenPressed(new Move(0,0,0,90));
	}
}
