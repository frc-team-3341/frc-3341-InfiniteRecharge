/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;
import edu.wpi.first.wpilibj.Servo;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class BallScorer extends SubsystemBase {
  /**
   * Creates a new BallScorer.
   */
  private TalonSRX belt = new TalonSRX(4);
  private TalonSRX storer = new TalonSRX(5);
  private TalonSRX adjust = new TalonSRX(6);
  private Servo gate = new Servo(1);

  public BallScorer() {

  }
  public void beltSpin(double speed){
    belt.set(ControlMode.PercentOutput, speed);
  }

  public void storerSpin(double speed){
    storer.set(ControlMode.PercentOutput, speed);
  }

  public void roofAdjust(double speed){
    adjust.set(ControlMode.PercentOutput, speed);
  }

  public void gateSpin(double position) {
    gate.setAngle(position);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
