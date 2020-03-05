/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;
import frc.robot.commands.Aquire;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class AcquireCG extends ParallelCommandGroup {
  /**
   * Creates a new AcquireCG.
   */
  public AcquireCG(double pow1, double pow2) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());super();
    //super(BallScorer.getInstance().acquireBalls(true), BallScorer.getInstance().beltSpin(BallScorer.beltDirection.UP));
    super(new Aquire(pow1));
    //These are using instances of the commands in the subsystems, make sure to change this to the commands themselves - Rishi
  }
}
