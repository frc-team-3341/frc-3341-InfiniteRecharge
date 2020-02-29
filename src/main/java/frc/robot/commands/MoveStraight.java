/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class MoveStraight extends CommandBase {
  /**
   * Creates a new MoveStraight.
   */
  private double d;
  private double dInitial;
  private DriveTrain drive;
  public MoveStraight(double distance, DriveTrain drive) {
    // Use addRequirements() here to declare subsystem dependencies.s
    d = distance;
    this.drive = drive;
    //dInitial = drive.getPosition();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    drive.resetEncoders();
    dInitial = drive.getPosition();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    System.out.println(drive.getPosition() + " position");
    System.out.println(drive.getPosition() - dInitial + " position - initial");
    System.out.println(drive.getLeftPosition() + " left pos");
    System.out.println(drive.getRightPosition() + " right pos");

    // ONLY FOR GREEN ROBOT! ADJUST MOTORS FOR DIFFERENT ROBOTS
    if(d>0)
      drive.turn(0.5, 0.5);
    else 
      drive.turn(-0.5,-0.5);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(d>0)
      return drive.getPosition() - dInitial> d;
    else 
      return drive.getPosition() - dInitial < d;
  }
}
