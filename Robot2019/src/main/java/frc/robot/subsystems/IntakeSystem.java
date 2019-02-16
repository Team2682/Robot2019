/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;
/**
 * Add your docs here.
 */
public class IntakeSystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public VictorSPX vertR= new VictorSPX(0);
  public VictorSPX vertL= new VictorSPX(0);
  public VictorSPX horz= new VictorSPX(0); 

  public IntakeSystem() {

  }
  @Override
  public void initDefaultCommand() {
    
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void intake(double vertical, double horizontal ) {
    vertR.set(ControlMode.PercentOutput, vertical);
    vertL.set(ControlMode.PercentOutput, vertical);
    horz.set(ControlMode.PercentOutput, horizontal);
  }
  public void stop(){
    vertR.set(ControlMode.PercentOutput,0);
    vertL.set(ControlMode.PercentOutput,0);
    horz.set(ControlMode.PercentOutput,0);
  }
}
