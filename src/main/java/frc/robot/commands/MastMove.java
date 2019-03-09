package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class MastMove extends Command {
   boolean auxStick = false;

   public MastMove() {
      this.requires(Robot.mast);
   }

   public MastMove(boolean auxStick) {
      this.auxStick = auxStick;
      this.requires(Robot.mast);
   }

   protected void initialize() {
   }

   protected void execute() {
      Robot.mast.move(Robot.oi.auxStick.getY());
   }

   protected boolean isFinished() {
      return false;
   }

   protected void end() {
      Robot.mast.stop();
   }

   protected void interrupted() {
      Robot.mast.stop();
   }
}
