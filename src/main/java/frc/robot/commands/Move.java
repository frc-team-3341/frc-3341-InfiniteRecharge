/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;
import option16.util.PID;

public class Move extends CommandBase {
	private double distance;

	private DriveTrain d;
	
	private PID leftPID;
	private PID rightPID;
  /**
   * Creates a new Move.
   */
  public Move(double distance, DriveTrain d) {
	// Use addRequirements() here to declare subsystem dependencies.
	this.distance = distance;
	this.d = d;
	addRequirements(d);
	leftPID = new PID(0, 0, 0);
	rightPID = new PID(0, 0, 0);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
	  leftPID.update(distance - d.getLeftPosition());
	  rightPID.update(distance - d.getRightPosition());
	  d.tankDrive(leftPID.getPower(), rightPID.getPower(), false);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
	return Math.abs(distance - d.getLeftPosition()) < 4000 && leftPID.getPower() < .2 &&
			Math.abs(distance - d.getRightPosition()) < 4000 && rightPID.getPower() < .2;
  }
}
