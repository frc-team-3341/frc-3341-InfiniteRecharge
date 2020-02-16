/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.BallScorer;
import frc.robot.subsystems.intake;
import frc.robot.subsystems.Roof;
import frc.robot.commands.AcquireCG;
import frc.robot.commands.ShootCG;
import frc.robot.commands.NotShootCG;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem;
  public static BallScorer scorer;
  public static intake m_intake;
  public static Roof m_Roof;
  private double AcquirePow;
  private double ShootPow;
  private double RoofPow;
  private final ExampleCommand m_autoCommand;
  private final AcquireCG m_AquireCG;
  private final ShootCG m_ShootCG;
  private final NotShootCG m_NotShootCG;
  private Joystick shooterJoy;
  private JoystickButton storeButton;
  private JoystickButton shootButton;
  /*
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
  m_exampleSubsystem = new ExampleSubsystem();
  scorer = new BallScorer();
  m_intake = new intake();
  m_Roof = new Roof();
  m_autoCommand = new ExampleCommand(m_exampleSubsystem);
  m_AquireCG = new AcquireCG(AcquirePow, ShootPow, RoofPow);
  m_ShootCG = new ShootCG();
  m_NotShootCG = new NotShootCG();
  shooterJoy = new Joystick(2);
  storeButton = new JoystickButton(shooterJoy, 3);
  shootButton = new JoystickButton(shooterJoy, 4);
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    
    shootButton.whenPressed(new ShootCG());
    shootButton.whenReleased(new NotShootCG());
    storeButton.whenPressed(new AcquireCG(0.5, 0.5, 0.2));
    storeButton.whenReleased(new AcquireCG(0, 0, 0));
    //fill in lines below with nueva command
    //storeButton.whenPressed();
    //storeButton.whenPressed()
    //new Aquire(0.5), new Shoot(0.3), new RoofMove(0.2)
    //storeButton.whenPressed(new )
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }
}
