/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {
  /**
   * Creates a new intake.
   */
  private TalonSRX storer = new TalonSRX(10);
  private boolean ballAcquired, motorStarted;
  private long motorStartTime;
  private final static double threshold = 1;

  public static Intake instance;

  public static Intake getInstance(){
    if (instance == null)
      instance = new Intake();
    return instance;
  }

  public Intake() {
  }

  public void storerSpin(double speed){
    
    long currentTime = System.currentTimeMillis();
    while(System.currentTimeMillis() - currentTime < 200){}

    if(speed == 0)
      ballAcquired = false;

    storer.set(ControlMode.PercentOutput, speed);
  }

  /**
   * @return the ballAcquired
   */
  public boolean isBallAcquired() {
    return ballAcquired;
  }

  public void setBallAcquired(boolean ballAcquired){
    this.ballAcquired = ballAcquired;
  }

  @Override
  public void periodic() {
    double current = storer.getSupplyCurrent();
    if (current > threshold){
      ballAcquired = true;
    }
    SmartDashboard.putNumber("Intake Current", current);
    SmartDashboard.putBoolean("Ball Acquired", ballAcquired);
  }
}
