/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import java.util.Set;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import option16.util.Constants;
import option16.util.Units;

public class DriveTrain extends SubsystemBase {
  /**
   * Creates a new DriveTrain.
   */

  private static DriveTrain instance;

  public enum DrivetrainSide {
    left, right;
  }

  private WPI_TalonSRX left = new WPI_TalonSRX(2);
  private WPI_TalonSRX right = new WPI_TalonSRX(3);
  private WPI_TalonSRX leftFollow = new WPI_TalonSRX(4);
  private WPI_TalonSRX rightFollow = new WPI_TalonSRX(5);

  private DifferentialDrive drive;
  private boolean inverted = false;

  public DriveTrain() {
    left.configFactoryDefault();
    right.configFactoryDefault();
    leftFollow.configFactoryDefault();
    rightFollow.configFactoryDefault();

    right.setInverted(false);
    left.setInverted(false);
    rightFollow.setInverted(false);
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

    drive = new DifferentialDrive(left, right);

  }

  public static DriveTrain getInstance() {
    if (instance == null)
      instance = new DriveTrain();
    return instance;
  }

  public void tankDrive(double left, double right, boolean squareInputs) {
    drive.tankDrive(left, right, squareInputs);
    leftFollow.set(ControlMode.Follower, 2);
    rightFollow.set(ControlMode.Follower, 3);
  }

  public void setVelocities(double leftVel, double rightVel) {
    left.set(ControlMode.Velocity, leftVel);
    right.set(ControlMode.Velocity, rightVel);
    leftFollow.set(ControlMode.Follower, 2);
    rightFollow.set(ControlMode.Follower, 3);
  }

  public void resetEncoders() {
    left.setSelectedSensorPosition(0, 0, 10);
    right.setSelectedSensorPosition(0, 0, 10);
  }

  public double getPosition() {
    return (Math.abs(left.getSelectedSensorPosition()) + Math.abs(right.getSelectedSensorPosition())) / 2;
  }

  public double getLeftPosition() {
    return left.getSelectedSensorPosition();
  }

  public double getRightPosition() {
    return right.getSelectedSensorPosition();
  }
  /*
   * public double getSpeed(){ return
   * left.getSensorCollection().getPulseWidthVelocity() * 1 / (4096 * 10); }
   */

  public String getVelocities() {
    return (right.getSelectedSensorVelocity(0) + ", " + right.getSelectedSensorVelocity(0));
  }

  public WPI_TalonSRX getTalon(DrivetrainSide side) {
    if (side.equals(DrivetrainSide.left)) {
      return left;
    } else if (side.equals(DrivetrainSide.right)) {
      return right;
    } else {
      return null;
    }
  }

  public void arcadeDrive(double move, double turn, boolean squareInputs) {
    drive.arcadeDrive(move, turn, squareInputs);
    leftFollow.set(ControlMode.Follower, 2);
    rightFollow.set(ControlMode.Follower, 3);
  }

  public void reverseMotors() {
    inverted = !inverted;
  }

  public boolean isInverted() {
    return inverted;
  }

  public void moveDistance(double distance) {
    this.resetEncoders();
    if (distance < 0) { 
      while (this.getPosition() < Math.abs(distance)) {
        this.tankDrive(-0.5, -0.5, false);
      }
    }
    if (distance > 0) {
      while (this.getPosition() < Math.abs(distance)) {
        this.tankDrive(0.5, 0.5, false);
      }
    }
  }

  public Command autonomousPart1(){
    return new Command(){

		  @Override
		  public Set<Subsystem> getRequirements() {
			  // TODO Auto-generated method stub
			  return null;
	  	}
    
      public boolean isFinished(){
        moveDistance(Units.feetToTicks(-6.5));
        return true;
      }
    };
  }

  
  public Command autonomousPart2(){
    return new Command(){

		  @Override
		  public Set<Subsystem> getRequirements() {
			  // TODO Auto-generated method stub
			  return null;
	  	}
    
      public boolean isFinished(){
        moveDistance(Units.feetToTicks(15));
        return true;
      }
    };
  }

  @Override
  public void periodic() {
    if (inverted) {
      instance.tankDrive(-Robot.m_robotContainer.getRightJoy().getY(), -Robot.m_robotContainer.getLeftJoy().getY(), true);
    } else {
      instance.tankDrive(Robot.m_robotContainer.getLeftJoy().getY(), Robot.m_robotContainer.getRightJoy().getY(), true);
    }
  }

}
