/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class MastSystem extends Subsystem {

  public TalonSRX mastMotor = new TalonSRX(0);

  public MastSystem() {

    mastMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 30);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void goUp(double speed) {
    mastMotor.set(ControlMode.PercentOutput, speed);
  }

  public void goDown(double speed) {
    mastMotor.set(ControlMode.PercentOutput, -speed);

  }

  public void stop() {
    mastMotor.set(ControlMode.PercentOutput, 0);

  }

  public double getDistance() {
    return mastMotor.getSelectedSensorPosition(0);

  }

  public void resetDistance() {
    mastMotor.setSelectedSensorPosition(0, 0, 30);

  }

}
