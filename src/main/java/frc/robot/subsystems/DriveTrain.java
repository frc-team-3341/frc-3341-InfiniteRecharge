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
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;

public class DriveTrain extends SubsystemBase {
  /**
   * Creates a new DriveTrain.
   */
  private WPI_TalonSRX left = new WPI_TalonSRX(2);
  private WPI_TalonSRX right = new WPI_TalonSRX(3);
  private WPI_TalonSRX leftFollow = new WPI_TalonSRX(4);
  private WPI_TalonSRX rightFollow = new WPI_TalonSRX(5);
  private DifferentialDrive drive;
  public DriveTrain() {
	left.setNeutralMode(NeutralMode.Brake);
	leftFollow.setNeutralMode(NeutralMode.Brake);
	right.setNeutralMode(NeutralMode.Brake);
	rightFollow.setNeutralMode(NeutralMode.Brake);
    drive = new DifferentialDrive(left, right);
  }

  
  public void tankDrive(double left, double right, boolean squareInputs){
	drive.tankDrive(left, right, squareInputs);
    leftFollow.set(ControlMode.Follower, 2);
    rightFollow.set(ControlMode.Follower, 3);
  }
  public void arcadeDrive(double move, double turn, boolean squareInputs) {
	  drive.arcadeDrive(move, turn, squareInputs);
	  leftFollow.set(ControlMode.Follower, 2);
	  rightFollow.set(ControlMode.Follower, 3);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    tankDrive(-Robot.m_robotContainer.getLeftJoy().getY(), -Robot.m_robotContainer.getRightJoy().getY(), true);
  }
}
