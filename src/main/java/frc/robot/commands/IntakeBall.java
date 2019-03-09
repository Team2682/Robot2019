package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class IntakeBall extends Command {
   double speed;
   int time;
   Timer timer = new Timer();
   boolean isFinished = false;

   public IntakeBall(double percentSpeed, int deciSeconds) {
      this.requires(Robot.intake);
      this.speed = percentSpeed;
      this.time = deciSeconds;
   }

   protected void initialize() {
      this.timer.reset();
      this.timer.start();
   }

   protected void execute() {
      if (!Robot.intake.intakeLimit.get()) {
         Robot.intake.intake(this.speed, this.speed);
      }

   }

   protected boolean isFinished() {
      return Robot.intake.intakeLimit.get();
   }

   protected void end() {
      Robot.intake.hasBall = true;
      Robot.intake.stop();
   }

   protected void interrupted() {
      Robot.intake.stop();
   }
}
