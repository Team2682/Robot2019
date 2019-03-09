package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.util.PIDCorrection;

public class TurnByGyro extends Command {
   PIDCorrection pidCorrection = new PIDCorrection(0.006D);
   double setPoint;
   double basePower;
   double threshold;
   double correction;
   boolean isStabalizing;
   boolean debug = false;
   Timer timer = new Timer();

   public TurnByGyro(double setAngle, double basePower, double threshold, boolean debug) {
      this.setPoint = setAngle;
      this.basePower = basePower;
      this.threshold = threshold;
      this.debug = debug;
   }

   protected void initialize() {
      this.timer.reset();
   }

   protected void execute() {
      double currentHeading = Robot.drive.getYaw();
      double correction = this.pidCorrection.getCorretion(this.setPoint, currentHeading);
      if (correction > 0.15D) {
         correction = 0.15D;
      }

      double leftPower;
      double rightPower;
      if (this.setPoint < currentHeading) {
         leftPower = this.basePower + correction;
         rightPower = this.basePower + correction;
      } else {
         leftPower = -(this.basePower + correction);
         rightPower = -(this.basePower + correction);
      }

      Robot.drive.tankDrive(leftPower, rightPower);
      if (!this.isStabalizing && Robot.drive.getYaw() <= this.setPoint + this.threshold && Robot.drive.getYaw() >= this.setPoint - this.threshold) {
         this.timer.start();
         this.isStabalizing = true;
      }

   }

   protected boolean isFinished() {
      return this.isStabalizing && this.timer.get() > 0.5D;
   }

   protected void end() {
      Robot.drive.stop();
   }

   protected void interrupted() {
      Robot.drive.stop();
   }
}
