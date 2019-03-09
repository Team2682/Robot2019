package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.util.PIDCorrection;

public class DriveByGyro extends Command {
   double setPoint;
   double basePower;
   double ticks;
   PIDCorrection pid;

   public DriveByGyro(double setPoint, double basePower, double distance) {
      this.requires(Robot.drive);
      this.setPoint = setPoint;
      this.basePower = basePower;
      this.ticks = distance * 20.0D;
      this.pid = new PIDCorrection(0.006D);
   }

   protected void initialize() {
   }

   protected void execute() {
      double correction = this.pid.getCorretion(this.setPoint, Robot.drive.getYaw());
      double left = this.basePower;
      double right = this.basePower;
      if (Math.abs(correction) >= 0.25D) {
         correction = 0.25D;
      }

      if (correction < 0.0D) {
         left -= correction;
         right += correction;
      } else {
         left += correction;
         right -= correction;
      }

      Robot.drive.tankDrive(left, right);
   }

   protected boolean isFinished() {
      return Robot.drive.getDistanceLeft() > this.ticks;
   }

   protected void end() {
      Robot.drive.stop();
   }

   protected void interrupted() {
      Robot.drive.stop();
   }
}
