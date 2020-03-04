/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class Screwing extends CommandBase {
  /**
   * Creates a new Screwing.
   */
  private double speed;

  public Screwing(double p) {
    // Use addRequirements() here to declare subsystem dependencies.
    speed = p;
    addRequirements(RobotContainer.screwer);
    
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  //   if(RobotContainer.screwer.atTop()) {
  //     RobotContainer.m_pivot.setLock(true);;
     
  //  } else if (RobotContainer.screwer.atBottom()) {
  //    RobotContainer.m_pivot.setLock(false); 
  //  }
  //  else {
  //    RobotContainer.m_pivot.setLock(true);;
  //  }
  // // System.out.println(RobotContainer.m_pivot.canUseLeadScrew);
  //  if (!(RobotContainer.screwer.getLock())) {
     RobotContainer.screwer.spin(speed);
 //  }
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
