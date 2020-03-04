/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;
import frc.robot.commands.Aquire;
import frc.robot.commands.Shoot;
import frc.robot.commands.RoofMove;
import frc.robot.subsystems.BallScorer;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class AcquireCG extends ParallelCommandGroup {
  /**
   * Creates a new AcquireCG.
   */
  public AcquireCG() {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());super();
    //super(BallScorer.getInstance().acquireBalls(true), BallScorer.getInstance().beltSpin(BallScorer.beltDirection.UP));
    super(BallScorer.getInstance().acquireBallsCommand(), BallScorer.getInstance().beltSpinCommand());
  }
}
