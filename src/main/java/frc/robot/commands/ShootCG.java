/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class ShootCG extends ParallelCommandGroup {
  /**
   * Creates a new ShootCG.
   */
  public ShootCG(double flywheelpower, double degrees, double acquirepower) {
    super(new GateBlock(degrees), new FlywheelShoot(flywheelpower), new Acquire(acquirepower));
  }

  public ShootCG(double beltpower, double flywheelpower, double degrees, double acquirepower) {
      super(new GateBlock(degrees), new MoveBelt(beltpower), new FlywheelShoot(flywheelpower), new Acquire(acquirepower));
  }

  public boolean isFinished(){
    return true;
  }
}
