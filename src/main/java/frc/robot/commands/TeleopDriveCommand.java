package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick.ButtonType;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class TeleopDriveCommand extends Command {
   public TeleopDriveCommand() {
      this.requires(Robot.drive);
   }

   protected void initialize() {
   }

   protected void execute() {

      if (!Robot.oi.driveStick.getRawButton(1)) {
         Robot.limeVision = false;

         double turn = Robot.oi.driveStick.getX();
         double forward = Robot.oi.driveStick.getY();
         Robot.drive.arcadeDrive(turn, -forward);
      } else {

         Robot.limeVision = true;
         Robot.drive.driveToTape();
      }
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
