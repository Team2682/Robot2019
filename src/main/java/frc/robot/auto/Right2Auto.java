package frc.robot.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.DriveByGyro;
import frc.robot.commands.TurnByGyro;

public class Right2Auto extends CommandGroup {
   public Right2Auto() {
      this.addSequential(new DriveByGyro(-5.0D, 0.45D, 140.0D));
      this.addSequential(new TurnByGyro(90.0D, 0.2D, 2.0D, false));
   }
}
