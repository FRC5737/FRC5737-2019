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

	//Main joystick for driver
  public Joystick driveStick = new Joystick(RobotMap.lowJoyPort);
  public JoystickButton button1 = new JoystickButton(driveStick, 1);
	public JoystickButton button2 = new JoystickButton(driveStick, 2);
	public JoystickButton button3 = new JoystickButton(driveStick, 3);
	public JoystickButton button4 = new JoystickButton(driveStick, 4);
	public JoystickButton button5 = new JoystickButton(driveStick, 5);
	public JoystickButton button6 = new JoystickButton(driveStick, 6);
	public JoystickButton button7 = new JoystickButton(driveStick, 7);
  public JoystickButton button8 = new JoystickButton(driveStick, 8);

	//Seperate joystick for completing tasks
  public Joystick functionStick = new Joystick(RobotMap.highJoyPort);

  public OI() {
		button1.whenPressed(new Move(0.5,100,0,0));
		button2.whenPressed(new Move(0.5,100,90,0));
		button3.whenPressed(new Move(0.3,100,-90,90));
		button4.whenPressed(new Move(0.5,100,180,0));
		button5.whenPressed(new Move(0.5,100,45,0));
	}
}
