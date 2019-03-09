package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;

public class FourBarLinkageSystem extends Subsystem {
   static TalonSRX winchPneumatic = new TalonSRX(3);

   public void initDefaultCommand() {
      winchPneumatic.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);
      winchPneumatic.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);
   }

   public void goDown() {
      winchPneumatic.set(ControlMode.PercentOutput, -1.0D);
   }

   public void goUp() {
      winchPneumatic.set(ControlMode.PercentOutput, 1.0D);
   }

   public void stop() {
      winchPneumatic.set(ControlMode.PercentOutput, 0.0D);
   }
}
