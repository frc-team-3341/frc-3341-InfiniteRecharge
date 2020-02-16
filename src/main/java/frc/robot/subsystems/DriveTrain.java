/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import option16.util.Constants;

public class DriveTrain extends SubsystemBase {
  /**
   * Creates a new DriveTrain.
   */
  private TalonSRX left = new TalonSRX(0);
  private TalonSRX right = new TalonSRX(3);
  private TalonSRX leftFollow = new TalonSRX(0);
  private TalonSRX rightFollow = new TalonSRX(0);
  private static DriveTrain instance;

  public enum DrivetrainSide {
    left, right;
}

  public DriveTrain() {
    left.configFactoryDefault();
    right.configFactoryDefault();
    leftFollow.configFactoryDefault();
    rightFollow.configFactoryDefault();

    right.setInverted(true);
    left.setInverted(false);
    rightFollow.setInverted(true);
    leftFollow.setInverted(false);

    left.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute);
    right.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute);
    left.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute);
    right.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute);

    left.setNeutralMode(NeutralMode.Brake);
    right.setNeutralMode(NeutralMode.Brake);
    leftFollow.setNeutralMode(NeutralMode.Brake);
    rightFollow.setNeutralMode(NeutralMode.Brake);

    left.configOpenloopRamp(0);
    right.configOpenloopRamp(0);
    leftFollow.configOpenloopRamp(0);
    rightFollow.configOpenloopRamp(0);

    left.configClosedloopRamp(0);
    right.configClosedloopRamp(0);
    leftFollow.configClosedloopRamp(0);
    rightFollow.configClosedloopRamp(0);

    left.configPeakOutputForward(1);
    right.configPeakOutputForward(1);
    leftFollow.configPeakOutputForward(1);
    rightFollow.configPeakOutputForward(1);

    left.configPeakOutputReverse(-1);
    right.configPeakOutputReverse(-1);
    leftFollow.configPeakOutputReverse(-1);
    rightFollow.configPeakOutputReverse(-1);

    right.config_kP(0, Constants.kP);
    right.config_kI(0, Constants.kI);
    right.config_kD(0, Constants.kD);
    right.config_kF(0, Constants.kF);

    left.config_kP(0, Constants.kP);
    left.config_kI(0, Constants.kI);
    left.config_kD(0, Constants.kD);
    left.config_kF(0, Constants.kF);

    rightFollow.config_kP(0, Constants.kP);
    rightFollow.config_kI(0, Constants.kI);
    rightFollow.config_kD(0, Constants.kD);
    rightFollow.config_kF(0, Constants.kF);

    leftFollow.config_kP(0, Constants.kP);
    leftFollow.config_kI(0, Constants.kI);
    leftFollow.config_kD(0, Constants.kD);
    leftFollow.config_kF(0, Constants.kF);
  }

  public static DriveTrain getInstance(){
    if (instance == null)
      instance = new DriveTrain();
    return instance;
  }
  
  public void tankDrive(double leftpower, double rightpower){
    left.set(ControlMode.PercentOutput, leftpower);
    right.set(ControlMode.PercentOutput, rightpower);
    leftFollow.set(ControlMode.Follower, 2);
    rightFollow.set(ControlMode.Follower, 3);
  }

  public void setVelocities(double leftVel, double rightVel){
    left.set(ControlMode.Velocity, leftVel);
    right.set(ControlMode.Velocity, rightVel);
    leftFollow.set(ControlMode.Follower, 2);
    rightFollow.set(ControlMode.Follower, 3);
  }

  public String getVelocities(){
    return (right.getSelectedSensorVelocity(0) + ", " + right.getSelectedSensorVelocity(0));
  }

  public TalonSRX getTalon(DrivetrainSide side){
    if (side.equals(DrivetrainSide.left)) {
      return left;
    } 
    else if (side.equals(DrivetrainSide.right)) {
      return right;
    } 
    else {
      return null;
    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    if (DriverStation.getInstance().isOperatorControl())
      tankDrive(Robot.m_robotContainer.getLeftJoy().getY(), Robot.m_robotContainer.getRightJoy().getY());
  }
}
