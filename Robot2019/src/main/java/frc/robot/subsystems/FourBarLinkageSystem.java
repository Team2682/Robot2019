/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class FourBarLinkageSystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  static TalonSRX winchPneumatic = new TalonSRX(1);

  Solenoid pusher = new Solenoid(0);

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void goDown() {
    winchPneumatic.set(ControlMode.PercentOutput, -1);
  }

  public void goUp() {
    pusher.set(true);
    winchPneumatic.set(ControlMode.PercentOutput, 0);
  }

  public void looosen() {
    pusher.set(false);
  }
  public void stop() {
    winchPneumatic.set(ControlMode.PercentOutput, 0);
  }
}
