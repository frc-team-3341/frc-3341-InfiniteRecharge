/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import option16.util.Limelight;
import frc.robot.subsystems.DriveTrain;

public class MoveAndAlignToBall extends CommandBase {
	DriveTrain d;
  /**
   * Creates a new AlignToBall.
   */
  public MoveAndAlignToBall(DriveTrain d) {
	// Use addRequirements() here to declare subsystem dependencies.
	this.d = d;
	addRequirements(d);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
	  Limelight.setMoveConstants(.5, 0, 0, 1.5);
	  Limelight.setAlignConstants(0.05, 0, 0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  	public void execute() {
		d.arcadeDrive(Limelight.move(), Limelight.align());
		System.out.println(Limelight.move() + " " + Limelight.align());
  	}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
	  d.arcadeDrive(0, 0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
