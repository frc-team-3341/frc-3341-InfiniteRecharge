/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * Add your docs here.
 */
public class Gate extends SubsystemBase {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private static Gate instance;

  private Servo backGate = new Servo(0);

  public void backGateSpin(double position) {
    System.out.println("UPDATED - Gate Angle is: " + position);
    backGate.set(position);
  }

  public static Gate getInstance(){
    if (instance == null)
      instance = new Gate();
    return instance;
  }
}
