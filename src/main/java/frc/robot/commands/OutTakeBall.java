package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class OutTakeBall extends Command {
   double speed;
   int time;
   boolean isFinished = false;

   public OutTakeBall(double percentSpeed, int deciSeconds) {
      this.requires(Robot.intake);
      this.speed = -percentSpeed;
      this.time = deciSeconds;
   }

   protected void initialize() {
   }

   protected void execute() {
      Robot.intake.outtake(this.speed, this.speed);
   }

   protected boolean isFinished() {
      return false;
   }

   protected void end() {
      Robot.intake.stop();
   }

   protected void interrupted() {
      Robot.intake.stop();
   }
}
