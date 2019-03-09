package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class FourBarOut extends Command {
   public FourBarOut() {
      this.requires(Robot.linkage);
   }

   protected void initialize() {
   }

   protected void execute() {
      Robot.linkage.goUp();
   }

   protected boolean isFinished() {
      return false;
   }

   protected void end() {
   }

   protected void interrupted() {
      Robot.linkage.stop();
   }
}
