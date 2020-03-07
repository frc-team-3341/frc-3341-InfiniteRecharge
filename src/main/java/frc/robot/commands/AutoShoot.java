/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.BeltScorer;
import frc.robot.subsystems.FlyWheel;
import frc.robot.subsystems.Intake;

public class AutoShoot extends CommandBase {
  /**
   * Creates a new AutoShoot.
   */
  public AutoShoot() {
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    long currentTime = System.currentTimeMillis();

    while (System.currentTimeMillis() - currentTime < 2000){
      BeltScorer.getInstance().beltSpin(0.6);
      RobotContainer.flyWheel.flyWheelSpin(0.8);
    }
    return true;
  }
}
