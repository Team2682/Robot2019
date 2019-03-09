package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class TeleopDriveCommand extends Command {
   public TeleopDriveCommand() {
      this.requires(Robot.drive);
   }

   protected void initialize() {
   }

   protected void execute() {
      double turn = Robot.oi.driveStick.getX();
      double forward = Robot.oi.driveStick.getY();
      Robot.drive.arcadeDrive(turn, -forward);
   }

   protected boolean isFinished() {
      return false;
   }

   protected void end() {
      Robot.drive.stop();
   }

   protected void interrupted() {
      Robot.drive.stop();
   }
}
