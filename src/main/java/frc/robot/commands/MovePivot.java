/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotContainer;

public class MovePivot extends CommandBase {
  /**
   * Creates a new MovePivot.
   */
  public MovePivot() {
    addRequirements(RobotContainer.m_pivot);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
//  if(RobotContainer.m_pivot.atBottom()) {
//   RobotContainer.screwer.setLock(true);

// } else if (RobotContainer.m_pivot.atTop()) {
//   RobotContainer.screwer.setLock(false);
// } else {
//   RobotContainer.screwer.setLock(true);
// }
// if(!(RobotContainer.m_pivot.getLock())) {
RobotContainer.m_pivot.pivot(Robot.m_robotContainer.getMechJoy().getY());
//}

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
