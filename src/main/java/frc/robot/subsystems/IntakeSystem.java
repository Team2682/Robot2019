package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

public class IntakeSystem extends Subsystem {
   public VictorSPX vertR = new VictorSPX(12);
   public VictorSPX vertL = new VictorSPX(11);
   public TalonSRX horz = new TalonSRX(4);
   public DoubleSolenoid hatchGrabber = new DoubleSolenoid(13, 7, 6);
   public DigitalInput intakeLimit = new DigitalInput(2);
   public boolean hasBall = false;
   Timer timer = new Timer();

   public void initDefaultCommand() {
   }

   public void intake(double vertical, double horizontal) {
      if (!this.hasBall) {
         this.vertR.set(ControlMode.PercentOutput, vertical);
         this.vertL.set(ControlMode.PercentOutput, -vertical);
      }

   }

   public void outtake(double vertical, double horizontal) {
      this.hasBall = false;
      this.vertR.set(ControlMode.PercentOutput, vertical);
      this.vertL.set(ControlMode.PercentOutput, -vertical);
   }

   public void stop() {
      this.vertR.set(ControlMode.PercentOutput, 0.0D);
      this.vertL.set(ControlMode.PercentOutput, 0.0D);
      this.horz.set(ControlMode.PercentOutput, 0.0D);
   }
}
