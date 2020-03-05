/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;

import frc.robot.subsystems.BallScorer;
import frc.robot.subsystems.intake;
import frc.robot.subsystems.Roof;
import frc.robot.subsystems.Switch;
import frc.robot.commands.AcquireCG;
import frc.robot.commands.ShootCG;
import frc.robot.subsystems.LeadScrew;
import frc.robot.commands.gateblock;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.AlignToBall;
import frc.robot.commands.Move;
import frc.robot.commands.MoveAndAlignToBall;
import frc.robot.commands.MovePivot;
import frc.robot.commands.Path1;
import frc.robot.commands.Path2;
import frc.robot.commands.Path3;
import frc.robot.commands.Turn;

import frc.robot.commands.ReverseTankDrive;
import frc.robot.commands.Screwing;
import frc.robot.commands.TankDrive;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.FlyWheel;
import frc.robot.subsystems.NavX;
import frc.robot.subsystems.Pivot;
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
  public static BallScorer scorer;
  public static intake m_intake;
  public static FlyWheel flyWheel;
  //public static Roof m_Roof;
  public static LeadScrew screwer;
  public static Pivot m_pivot;
  public static Switch m_switch;
  private double AcquirePow;
  private double ShootPow;
  private double RoofPow;
  private AcquireCG m_AquireCG;
  private ShootCG m_ShootCG;
  private Joystick mechJoy;
  private JoystickButton screwUp;
  private JoystickButton screwDown;
  public JoystickButton intakeBallButton;
  public JoystickButton dropBallButton;
  public JoystickButton conveyorEmergencyButton;
  public JoystickButton intakeEmergencyButton;
  public JoystickButton gateButton;
  //public final MoveAndAlignToBall moveAndAlignToBall = new MoveAndAlignToBall();
 // public final AlignToBall alignToBall = new AlignToBall();
  public final Turn turn = new Turn(90);
  public final Move move = new Move(10000);

  //private NetworkTableEntry delay = Shuffleboard.getTab("SmartDashboard").add("delay", 5).getEntry();

  public final Path1 path1 = new Path1();
  //public final Path2 path2 = new Path2(delay);
  public final Path3 path3 = new Path3();

  private final Joystick leftJoy;
  private final Joystick rightJoy;


  public static DriveTrain drive;
  public NavX navx = new NavX();

  public JoystickButton reverseButton;
  /*
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
  scorer = new BallScorer();
  m_intake = new intake();
  flyWheel = new FlyWheel();
  //m_Roof = new Roof();
  
  
  m_pivot = new Pivot();
  m_switch = new Switch();
  screwer = new LeadScrew();
  drive = new DriveTrain();
  leftJoy = new Joystick(0);
  rightJoy = new Joystick(1);
  mechJoy = new Joystick(2);
  intakeBallButton = new JoystickButton(rightJoy, 3);
  conveyorEmergencyButton = new JoystickButton(rightJoy, 5);
  intakeEmergencyButton = new JoystickButton(rightJoy, 6);
  dropBallButton = new JoystickButton(rightJoy, 4);
  gateButton = new JoystickButton(rightJoy, 7);
  screwUp = new JoystickButton(mechJoy, 3);
  screwDown = new JoystickButton(mechJoy, 4);
  reverseButton = new JoystickButton(rightJoy, 2);
  m_pivot.setDefaultCommand(new MovePivot());
  drive.setDefaultCommand(new TankDrive());

  configureButtonBindings();
  }



  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */

    
    // Configure the button bindings
 
  public Joystick getLeftJoy() {
    return leftJoy;
  }
  public Joystick getRightJoy() {
    return rightJoy;
  }
  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    
    // shootButton.whenPressed(new ShootCG());
    // shootButton.whenReleased(new NotShootCG());
    // storeButton.whenPressed(new AcquireCG(0.5, 0.5, 0.2));
    // storeButton.whenReleased(new AcquireCG(0, 0, 0));
    //fill in lines below with nueva command
    //storeButton.whenPressed();
    //storeButton.whenPressed()
    //new Aquire(0.5), new Shoot(0.3), new RoofMove(0.2)
    //storeButton.whenPressed(new )
   // new JoystickButton(rightJoy, 1).whileHeld(alignToBall);
    screwUp.whileHeld(new Screwing(0.5));
    screwDown.whileHeld(new Screwing(-0.5));
    reverseButton.whenPressed(new ReverseTankDrive());
    intakeBallButton.whenPressed(new AcquireCG(0.7, 0.7));
    intakeBallButton.whenReleased(new AcquireCG(0, 0));
    dropBallButton.whenPressed(new ShootCG(0.5,1, 1));
    dropBallButton.whenReleased(new ShootCG(0,0, 0));
    gateButton.whenPressed(new gateblock(0));
    gateButton.whenReleased(new gateblock(0.5));
    //conveyorEmergencyButton.whenPressed(new ShootCG(2, ));
    intakeEmergencyButton.whenPressed(new AcquireCG(-0.7, 0));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
   // return moveAndAlignToBall;
    return null;
  }
  public Joystick getMechJoy() {
    return mechJoy;
  }
  public boolean leftGetRawButton(int n) {
	  return leftJoy.getRawButton(n);
  }
  public boolean rightGetRawButton(int n) {
	  return rightJoy.getRawButton(n);
  }
}
