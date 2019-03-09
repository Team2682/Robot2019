package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class HatchOut extends Command {
   public HatchOut() {
      this.requires(Robot.intake);
   }

   protected void initialize() {
   }

   protected void execute() {
      Robot.intake.hatchGrabber.set(DoubleSolenoid.Value.kReverse);
   }

   protected boolean isFinished() {
      return false;
   }

   protected void end() {
      Robot.intake.hatchGrabber.set(DoubleSolenoid.Value.kReverse);
   }

   protected void interrupted() {
      Robot.intake.hatchGrabber.set(DoubleSolenoid.Value.kReverse);
   }
}
