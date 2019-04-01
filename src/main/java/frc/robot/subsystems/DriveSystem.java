package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.commands.TeleopDriveCommand;

public class DriveSystem extends Subsystem {
   TalonSRX motorLeftMaster = new TalonSRX(2);
   TalonSRX motorRightMaster = new TalonSRX(6);
   TalonSRX motorLeftSlave = new TalonSRX(1);
   TalonSRX motorRightSlave = new TalonSRX(7);
   PigeonIMU pigeon;
   double error;
   JoystickButton resetPid;

   private boolean m_LimelightHasValidTarget = false;
   private double m_LimelightDriveCommand = 0.0;
   private double m_LimelightSteerCommand = 0.0;

   public DriveSystem(int canIdLeftMaster, int canIdRightMaster) {
      this.resetPid = new JoystickButton(Robot.oi.driveStick, 2);
      this.initializeMotors();
   }

   public void initializeMotors() {
      this.motorRightSlave.follow(this.motorRightMaster);
      this.motorLeftSlave.follow(this.motorLeftMaster);
      this.motorLeftMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 30);
      this.motorRightMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 30);
      this.motorLeftMaster.config_kP(0, 0.006D);
      this.motorLeftMaster.configAllowableClosedloopError(0, 1, 30);
      this.error = (double)this.motorLeftMaster.getClosedLoopError(0);
      this.pigeon = new PigeonIMU(this.motorLeftSlave);
      this.resetYaw();
   }

   public void initDefaultCommand() {
      this.setDefaultCommand(new TeleopDriveCommand());
   }

   public void tankDrive(double left, double right) {
      this.motorLeftMaster.set(ControlMode.PercentOutput, left);
      this.motorRightMaster.set(ControlMode.PercentOutput, -right);
   }

   public void arcadeDrive(double forward, double turn) {
      this.error = (double)this.motorLeftMaster.getClosedLoopError(0);
      this.motorLeftMaster.set(ControlMode.PercentOutput, turn, DemandType.ArbitraryFeedForward, forward - this.error);
      this.motorRightMaster.set(ControlMode.PercentOutput, -turn, DemandType.ArbitraryFeedForward, forward);
      if (this.resetPid.get()) {
         this.error = (double)this.motorLeftMaster.getClosedLoopError(0);
         this.resetEncoders();
      }

   }

   public void stop() {
      this.motorLeftMaster.set(ControlMode.PercentOutput, 0.0D);
      this.motorRightMaster.set(ControlMode.PercentOutput, 0.0D);
   }

   public double getDistanceLeft() {
      return (double)this.motorLeftMaster.getSelectedSensorPosition(0);
   }

   public double getDistanceRight() {
      return (double)this.motorRightMaster.getSelectedSensorPosition(0);
   }

   public double getYaw() {
      return this.pigeon.getFusedHeading();
   }

   public void resetEncoders() {
      this.motorLeftMaster.setSelectedSensorPosition(0);
      this.motorRightMaster.setSelectedSensorPosition(0);
      System.out.println("reset encs");
   }

   public void resetYaw() {
      this.pigeon.setFusedHeading(0.0D);
   }

   public void driveToTape() {
      final double STEER_K = 0.0275;                    // how hard to turn toward the target
      final double DRIVE_K = 0.16;                    // how hard to drive fwd toward the target
      final double DESIRED_TARGET_AREA = 3.1;        // Area of the target when the robot reaches the wall
      final double MAX_DRIVE = 0.4;                   // Simple speed limit so we don't drive too fast

      double tv = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
      double tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
      double ty = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
      double ta = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0);

      if (tv < 1.0)
      {
        m_LimelightHasValidTarget = false;
        m_LimelightDriveCommand = 0.0;
        m_LimelightSteerCommand = 0.0;
        return;
      }

      m_LimelightHasValidTarget = true;

      // Start with proportional steering
      double steer_cmd = -tx * STEER_K;
      m_LimelightSteerCommand = steer_cmd;

      // try to drive forward until the target area reaches our desired area
      double drive_cmd = (DESIRED_TARGET_AREA - ta) * DRIVE_K;

      // don't let the robot drive too fast into the goal
      if (drive_cmd > MAX_DRIVE)
      {
        drive_cmd = MAX_DRIVE;
      }
      m_LimelightDriveCommand = drive_cmd;

      arcadeDrive(-m_LimelightSteerCommand, m_LimelightDriveCommand);
   }
}
