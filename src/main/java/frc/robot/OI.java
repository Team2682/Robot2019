package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.FourBarIn;
import frc.robot.commands.FourBarOut;
import frc.robot.commands.HatchIn;
import frc.robot.commands.IntakeBall;
import frc.robot.commands.OutTakeBall;

public class OI {
   public Joystick driveStick = new Joystick(0);
   public Joystick auxStick = new Joystick(1);

   public OI() {
      JoystickButton intake = new JoystickButton(this.auxStick, 1);
      JoystickButton outTake = new JoystickButton(this.driveStick, 3);
      JoystickButton hatchIn = new JoystickButton(this.auxStick, 3);
      JoystickButton linkUp = new JoystickButton(this.auxStick, 6);
      JoystickButton linkDown = new JoystickButton(this.auxStick, 5);
      intake.whileHeld(new IntakeBall(1.0D, 0));
      outTake.whileHeld(new OutTakeBall(1.0D, 0));
      hatchIn.whileHeld(new HatchIn());
      linkUp.whileHeld(new FourBarOut());
      linkDown.whileHeld(new FourBarIn());
   }
}
