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
import frc.robot.Robot;

public class DriveTrain extends SubsystemBase {
  /**
   * Creates a new DriveTrain.
   */
  private TalonSRX left = new TalonSRX(2);
  private TalonSRX right = new TalonSRX(3);
  private TalonSRX leftFollow = new TalonSRX(4);
  private TalonSRX rightFollow = new TalonSRX(5);
  public DriveTrain() {
    
  }

  
  public void tankDrive(double leftpower, double rightpower){
    left.set(ControlMode.PercentOutput, leftpower);
    right.set(ControlMode.PercentOutput, rightpower);
    leftFollow.set(ControlMode.Follower, 2);
    rightFollow.set(ControlMode.Follower, 3);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    tankDrive(Robot.m_robotContainer.getLeftJoy().getY(), Robot.m_robotContainer.getRightJoy().getY());
  }
}
