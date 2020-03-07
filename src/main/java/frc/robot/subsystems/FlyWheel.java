/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class FlyWheel extends SubsystemBase {
  /**
   * Creates a new FlyWheel.
   */
  private TalonSRX wheelLeft = new TalonSRX(13);
  private TalonSRX wheelRight = new TalonSRX(14);
  public FlyWheel() {
    wheelRight.setInverted(true);

    wheelRight.configPeakOutputForward(1);
    wheelRight.configPeakOutputReverse(-1);
    wheelRight.setNeutralMode(NeutralMode.Coast);
    
    wheelLeft.configPeakOutputForward(1);
    wheelLeft.configPeakOutputReverse(-1);
    wheelLeft.setNeutralMode(NeutralMode.Coast);
    
  }
  public void flyWheelSpin(double speed) {
    wheelLeft.set(ControlMode.PercentOutput,speed);
    wheelRight.set(ControlMode.Follower,13);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
