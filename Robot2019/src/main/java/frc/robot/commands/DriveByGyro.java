/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

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
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.drive);
    this.setPoint = setPoint;
    this.basePower = basePower;
    this.ticks = distance * 1000;
    pid = new PIDCorrection(0.006);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double correction = pid.getCorretion(setPoint, Robot.drive.getYaw());

    double left = basePower;
    double right = basePower;

    if (Math.abs(correction) >= .25) {
      correction = .25;
    }

    if (correction < 0) {
      left -= correction;
      right += correction;
    } else {
      left += correction;
      right += correction;
    }

    Robot.drive.tankDrive(left, right);

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Robot.drive.getDistanceLeft() > ticks;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.drive.stop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.drive.stop();
  }
}
