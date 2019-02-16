/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.TeleopDriveCommand;

/**
 * Add your docs here.
 */
public class DriveSystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  TalonSRX motorLeftMaster;
  TalonSRX motorRightMaster;

  TalonSRX motorLeftSlave;
  TalonSRX motorRightSlave;

  PigeonIMU pigeon;

  double error;

  public DriveSystem(int canIdLeftMaster, int canIdRightMaster) {

    motorLeftMaster = new TalonSRX(canIdLeftMaster);
    motorRightMaster = new TalonSRX(canIdRightMaster);

    motorLeftSlave = new TalonSRX(canIdLeftMaster + 2);
    motorRightSlave = new TalonSRX(canIdLeftMaster + 2);

    initializeMotors();

  }

  public void initializeMotors() {
    motorRightSlave.follow(motorRightMaster);
    motorLeftMaster.follow(motorLeftMaster);

    motorLeftMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 30);
    motorRightMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 30);

    motorLeftMaster.config_kP(0, 0.006);
    motorLeftMaster.configAllowableClosedloopError(0, 1, 30);

    error = motorLeftMaster.getClosedLoopError(0);

    pigeon = new PigeonIMU(motorLeftSlave);

  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new TeleopDriveCommand());
  }

  public void tankDrive(double left, double right) {
    motorLeftMaster.set(ControlMode.PercentOutput, left);
    motorRightMaster.set(ControlMode.PercentOutput, -right);
  }

  public void arcadeDrive(double forward, double turn) {
    motorLeftMaster.set(ControlMode.PercentOutput, turn, DemandType.ArbitraryFeedForward, forward - error);
    motorRightMaster.set(ControlMode.PercentOutput, -turn, DemandType.ArbitraryFeedForward, forward);
  }

  public void stop() {
    motorLeftMaster.set(ControlMode.PercentOutput, 0);
    motorRightMaster.set(ControlMode.PercentOutput, 0);
  }

  public double getDistanceLeft() {
    return motorLeftMaster.getSelectedSensorPosition(0);
  }

  public double getDistanceRight() {
    return motorRightMaster.getSelectedSensorPosition(0);
  }

  public double getYaw() {
    return pigeon.getFusedHeading();
  }

  public void resetYaw() {
    pigeon.setFusedHeading(0);
  }
}
