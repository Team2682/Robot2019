package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class HatchIn extends Command {
   public HatchIn() {
      this.requires(Robot.intake);
   }

   protected void initialize() {
   }

   protected void execute() {
      System.out.println(Robot.intake.hatchGrabber.get());
      Robot.intake.hatchGrabber.set(DoubleSolenoid.Value.kReverse);
   }

   protected boolean isFinished() {
      return false;
   }

   protected void end() {
      Robot.intake.hatchGrabber.set(DoubleSolenoid.Value.kForward);
   }

   protected void interrupted() {
      Robot.intake.hatchGrabber.set(DoubleSolenoid.Value.kForward);
   }
}
