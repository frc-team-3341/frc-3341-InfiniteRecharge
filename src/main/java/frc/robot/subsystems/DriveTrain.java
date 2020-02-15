/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
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
  //private WPI_TalonSRX leftFollow = new WPI_TalonSRX(4);
  //private WPI_TalonSRX rightFollow = new WPI_TalonSRX(5);
  private DifferentialDrive drive;
  public DriveTrain() {
    drive = new DifferentialDrive(left, right);
    left.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,0,10);
    right.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,0,10);
    //left.setInverted(true);
    //right.setInverted(true);
    left.setSensorPhase(true);
    right.setSensorPhase(true);

    left.setSelectedSensorPosition(0, 0, 10);
    right.setSelectedSensorPosition(0, 0, 10);
  }
  public void resetEncoders(){
    left.setSelectedSensorPosition(0, 0, 10);
    right.setSelectedSensorPosition(0, 0, 10);
  }
  public double getPosition(){
    return (left.getSelectedSensorPosition()-right.getSelectedSensorPosition())/2;
  }
  public double getLeftPosition(){
    return left.getSelectedSensorPosition();
  }
  public double getRightPosition(){
    return right.getSelectedSensorPosition();
  }
  /*public double getSpeed(){
    return left.getSensorCollection().getPulseWidthVelocity() * 1 / (4096 * 10);
  }*/
  public void tankDrive(double leftpower, double rightpower){
    //left.set(ControlMode.PercentOutput, leftpower);
	//right.set(ControlMode.PercentOutput, rightpower);
	drive.tankDrive(leftpower, rightpower);
    //leftFollow.set(ControlMode.Follower, 2);
    //rightFollow.set(ControlMode.Follower, 3);
  }
  public void arcadeDrive(double move, double turn) {
	  drive.arcadeDrive(move, turn);
	  //leftFollow.set(ControlMode.Follower, 2);
	  //rightFollow.set(ControlMode.Follower, 3);
  }
  int temp = 0;
  @Override
  
  public void periodic() {
    // This method will be called once per scheduler run
    
    tankDrive(-Robot.m_robotContainer.getLeftJoy().getY()+Robot.m_robotContainer.getLeftJoy().getZ(), -Robot.m_robotContainer.getLeftJoy().getY() - Robot.m_robotContainer.getLeftJoy().getZ());
    //System.out.println("periodic is running" + temp);
  }

}
