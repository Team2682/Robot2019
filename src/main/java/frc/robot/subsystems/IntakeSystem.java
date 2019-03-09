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
   public VictorSPX ballAssist = new VictorSPX(11);
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
         this.horz.set(ControlMode.PercentOutput, -horizontal);
      }

   }

   public void outtake(double vertical, double horizontal) {
      this.hasBall = false;
      this.vertR.set(ControlMode.PercentOutput, vertical);
   }

   public void assistDown() {
      this.ballAssist.set(ControlMode.PercentOutput, 0.3D);
   }

   public void assistUp() {
      this.ballAssist.set(ControlMode.PercentOutput, -0.7D);
   }

   public void assistStop() {
      this.ballAssist.set(ControlMode.PercentOutput, 0.0D);
   }

   public void stop() {
      this.vertR.set(ControlMode.PercentOutput, 0.0D);
      this.horz.set(ControlMode.PercentOutput, 0.0D);
   }
}
