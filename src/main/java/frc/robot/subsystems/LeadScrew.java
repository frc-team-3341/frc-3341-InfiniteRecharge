/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class LeadScrew extends SubsystemBase {
  /**
   * Creates a new leadScrew.
   */
  public boolean lock;
  private TalonSRX screw = new TalonSRX(7);

  public LeadScrew() {
    //this.setDefaultCommand(new Screwing());
    screw.setInverted(true);
    screw.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyClosed);
    screw.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyClosed);

  }
  public void spin(double speed){
    screw.set(ControlMode.PercentOutput,speed);
  }
  public void setLock(boolean lock) {
    this.lock = lock;
}
public boolean getLock() {
    return lock;
}
public boolean atTop() {
    return screw.getSensorCollection().isFwdLimitSwitchClosed();
}
public boolean atBottom() {
    return screw.getSensorCollection().isRevLimitSwitchClosed();
}


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    System.out.println(screw.getSupplyCurrent());
  }
  public TalonSRX getScrewTalon() {
    return screw;
  }
}
