/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.commands.Screwing;

public class LeadScrew extends SubsystemBase {
  /**
   * Creates a new leadScrew.
   */
  private TalonSRX screw = new TalonSRX(2);

  public LeadScrew() {
    //this.setDefaultCommand(new Screwing());
  }
  public void spin(double speed){
    screw.set(ControlMode.PercentOutput,speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public TalonSRX getScrewTalon() {
    return screw;
  }
}
