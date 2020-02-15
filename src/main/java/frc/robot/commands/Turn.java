/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.NavX;

public class Turn extends CommandBase {
  DriveTrain drive;
  NavX navx;
  double initialAngle;
  double turnAngle;
  /**
   * Creates a new Turn.
   */
  public Turn(NavX navx, double turnAngle) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.navx = navx;
    this.turnAngle = turnAngle;
    drive = new DriveTrain();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    navx.resetAngle();
    initialAngle = navx.getAngle();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    System.out.println(navx.getAngle()- initialAngle + " angle");
    if (turnAngle > navx.getAngle() - initialAngle) {
      drive.tankDrive(0.5, -0.5);
    } else {
      drive.tankDrive(-0.5, 0.5);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (turnAngle > 0) {
      return navx.getAngle() - initialAngle > turnAngle;
    } else {
      return navx.getAngle() - initialAngle < turnAngle;
    }
  }
}
