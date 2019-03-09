package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class FourBarIn extends Command {
   public FourBarIn() {
      this.requires(Robot.linkage);
   }

   protected void initialize() {
   }

   protected void execute() {
      Robot.linkage.goDown();
   }

   protected boolean isFinished() {
      return false;
   }

   protected void end() {
      Robot.linkage.stop();
   }

   protected void interrupted() {
      Robot.linkage.stop();
   }
}
