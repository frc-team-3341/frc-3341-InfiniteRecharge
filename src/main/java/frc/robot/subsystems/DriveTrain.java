/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


//TankDrive with two traditional joysticks 
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;


public class DriveTrain extends SubsystemBase {
  /**
   * Creates a new DriveTrain.
   */
  private WPI_TalonSRX left = new WPI_TalonSRX(2);
  private WPI_TalonSRX right = new WPI_TalonSRX(3);
  private WPI_TalonSRX leftFollow = new WPI_TalonSRX(4);
  private WPI_TalonSRX rightFollow = new WPI_TalonSRX(5);
  private static DriveTrain instance;
  public DriveTrain() {
    
  }

  public static DriveTrain getInstance(){
    if (instance == null){
      instance = new DriveTrain();
    }

    return instance;
  }
  public void tankDrive(double leftpower, double rightpower){
    //set left motor inverted
    left.set(ControlMode.PercentOutput, leftpower);
    right.set(ControlMode.PercentOutput, rightpower);
    leftFollow.set(ControlMode.Follower, 2);
    rightFollow.set(ControlMode.Follower, 3);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    //moved tankDrive to its own command
    //tankDrive(Robot.m_robotContainer.getLeftJoy().getY(), Robot.m_robotContainer.getRightJoy().getY());
  }
}
