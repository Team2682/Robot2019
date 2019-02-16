/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class MastGoDown extends Command {
  boolean auxStick = false;

  public MastGoDown() {
    requires(Robot.mast);

  }

  public MastGoDown(boolean auxStick) {
    this.auxStick = auxStick;
    requires(Robot.mast);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (auxStick) {
      if (Robot.oi.auxStick.getRawAxis(1) > .3 || Robot.oi.auxStick.getRawAxis(1) > -.3) {
        Robot.mast.goUp(Robot.oi.auxStick.getRawAxis(1));
      } else {
        Robot.mast.stop();
      }
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.mast.stop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.mast.stop();
  }
}
