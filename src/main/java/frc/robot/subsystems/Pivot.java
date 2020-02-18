/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;



public class Pivot extends SubsystemBase {
  /**
   * Creates a new Pivot.
   */
    private static Pivot pivot;
    private final TalonSRX pivotMotor = new TalonSRX(6);
  // private final TalonSRX motorRight = new TalonSRX(3);
  public boolean lock = false;
 // jiofjoerf

  public void pivot(final double JOY) {
    pivotMotor.set(ControlMode.PercentOutput, JOY);
    
}
  public Pivot() {
   // RotatePivot r = new RotatePivot();
  }
  public void setLock(boolean lock) {
    this.lock = lock;
  }
  public boolean getLock() {
    return lock;
  }
  public boolean atTop() {
    return pivotMotor.getSensorCollection().isRevLimitSwitchClosed();
  }
  public boolean atBottom() {
    return pivotMotor.getSensorCollection().isFwdLimitSwitchClosed();
  }

  public Pivot returnPivot() {
    if (pivot == null) {
      pivot = new Pivot();
    }
    return pivot;
  }

  @Override
  public void periodic() {
    //setDefaultCommand(new RotatePivot());
    // This method will be called once per scheduler run
    //Pivot(Robot.m_robotContainer.getPivotJoy().getY());
  }
  public TalonSRX getPivotTalon() {
    return pivotMotor;
  }
}
