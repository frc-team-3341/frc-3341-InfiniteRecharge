/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;
import frc.robot.commands.Shoot;
import frc.robot.commands.gateblock;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class NotShootCG extends SequentialCommandGroup {
  /**
   * Creates a new NotShootCG.
   */
  public NotShootCG() {
    // Add your commands in the super() call, e.g.
    
    // super(new FooCommand(), new BarCommand());
    super(new Shoot(0), new gateblock(0.5));
  }
}
