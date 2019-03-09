package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.MastMove;

public class MastSystem extends Subsystem {
   public TalonSRX mastMotor = new TalonSRX(2);
   public DigitalInput bottom = new DigitalInput(0);
   public DigitalInput top = new DigitalInput(1);

   public MastSystem() {
      this.mastMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 30);
   }

   public void initDefaultCommand() {
      this.setDefaultCommand(new MastMove());
   }

   public void move(double speed) {
      System.out.println(this.bottom.get());
      this.mastMotor.set(ControlMode.PercentOutput, speed);
   }

   public void goDown(double speed) {
      if (!this.bottom.get()) {
         this.mastMotor.set(ControlMode.PercentOutput, -speed);
      }

   }

   public void stop() {
      this.mastMotor.set(ControlMode.PercentOutput, 0.0D);
   }

   public double getDistance() {
      return (double)this.mastMotor.getSelectedSensorPosition(0);
   }

   public void resetDistance() {
      this.mastMotor.setSelectedSensorPosition(0, 0, 30);
   }
}
