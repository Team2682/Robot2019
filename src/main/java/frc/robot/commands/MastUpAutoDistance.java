package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class MastUpAutoDistance extends Command {
   double ticks;

   public MastUpAutoDistance(double inches) {
      this.requires(Robot.mast);
      this.ticks = inches * 0.0D;
   }

   protected void initialize() {
   }

   protected void execute() {
      Robot.mast.move(0.5D);
   }

   protected boolean isFinished() {
      return Robot.mast.getDistance() >= this.ticks;
   }

   protected void end() {
      Robot.mast.stop();
   }

   protected void interrupted() {
      Robot.mast.stop();
   }
}
