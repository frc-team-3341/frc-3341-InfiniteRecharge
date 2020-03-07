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
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import frc.robot.subsystems.BeltScorer;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Gate;
import frc.robot.subsystems.Switch;
import frc.robot.commands.AcquireCG;
import frc.robot.commands.ShootCG;
import frc.robot.subsystems.LeadScrew;
import frc.robot.commands.GateBlock;
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
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.FlyWheel;
import frc.robot.subsystems.Gate;
import frc.robot.subsystems.NavX;
import frc.robot.subsystems.Pivot;
import frc.robot.commands.MeasureColors;
import frc.robot.commands.RotationControl;
import frc.robot.subsystems.ColorSensor;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.commands.ColorControl;
import frc.robot.commands.ColorControlCounter;
import frc.robot.commands.HingeControl;
import frc.robot.commands.MotorControl;
//import edu.wpi.first.wpilibj.util.Color;
//import edu.wpi.first.wpilibj2.command.CommandScheduler;
//import frc.robot.commands.ExampleCommand;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  public static BeltScorer scorer;
  public static Intake m_intake;
  public static FlyWheel flyWheel;
  public static Gate gate;
  // public static Roof m_Roof;
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
  public JoystickButton ACBeltEmergencyButton;
  public JoystickButton gateButton;
  // public final MoveAndAlignToBall moveAndAlignToBall = new
  // MoveAndAlignToBall();
  public final AlignToBall alignToBall = new AlignToBall();
  public final Turn turn = new Turn(90);
  public final Move move = new Move(10000);

  private NetworkTableEntry delay = Shuffleboard.getTab("SmartDashboard").add("delay", 5).getEntry();
  public ColorSensor colorSensor;
  public MeasureColors measureColors;
  public RotationControl rotational;
  public ColorControlCounter colorControlCounter;
  public ColorControl colorControl;
  public JoystickButton button;
  public MotorControl motorControl;

  public String targetColor;
  public JoystickButton colorButton;
  public JoystickButton buttonRed;
  public JoystickButton buttonBlue;
  public JoystickButton buttonGreen;
  public JoystickButton buttonYellow;
  public JoystickButton colorCountingControler;
  public JoystickButton motorControler;
  public JoystickButton lastballbutton;
  public JoystickButton hingeButton;

  public final Path1 path1 = new Path1();
  public final Path2 path2 = new Path2(delay);
  public final Path3 path3 = new Path3();

  private final Joystick leftJoy;
  private final Joystick rightJoy;

  public static DriveTrain drive;
  public NavX navx = new NavX();

  public JoystickButton reverseButton;

  /*
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    scorer = new BeltScorer();
    m_intake = new Intake();
    flyWheel = new FlyWheel();
    // m_Roof = new Roof();
    gate = Gate.getInstance();
    m_pivot = new Pivot();
    m_switch = new Switch();
    screwer = new LeadScrew();
    drive = new DriveTrain();
    leftJoy = new Joystick(0);
    rightJoy = new Joystick(1);
    mechJoy = new Joystick(2);
    intakeBallButton = new JoystickButton(rightJoy, 3);
    ACBeltEmergencyButton = new JoystickButton(rightJoy, 5);
    dropBallButton = new JoystickButton(rightJoy, 4);
    gateButton = new JoystickButton(rightJoy, 6);
    screwUp = new JoystickButton(mechJoy, 3);
    screwDown = new JoystickButton(mechJoy, 4);
    lastballbutton = new JoystickButton(mechJoy, 5);
    reverseButton = new JoystickButton(rightJoy, 2);
    m_pivot.setDefaultCommand(new MovePivot());
    // // COLOR CONTROL TESTING BUTTONS (NOT FOR COMPETITION)
    // buttonRed = new JoystickButton(mechJoy, 5);
    // buttonBlue = new JoystickButton(mechJoy, 6);
    // buttonGreen = new JoystickButton(mechJoy, 7);
    // buttonYellow = new JoystickButton(mechJoy, 8);
    // COLOR WHEEL CONTROL BUTTONS
    colorButton = new JoystickButton(mechJoy, 12); // Color Control
    colorCountingControler = new JoystickButton(mechJoy, 10); // Rotatate 3 time
    motorControler = new JoystickButton(mechJoy, 11); // Manual Control
    hingeButton = new JoystickButton(mechJoy, 9); // Hinges Up

    // Configure the button bindings
    System.out.println("a");
    System.out.println("b");
    colorSensor = new ColorSensor();
    System.out.println("c");
    measureColors = new MeasureColors();
    System.out.println("d");
    rotational = new RotationControl();
    System.out.println("e");
    System.out.println("f");
    // colorControl = new ColorControl(0, colorSensor);
    colorControlCounter = new ColorControlCounter();
    motorControl = new MotorControl();
    System.out.println("g");
    configureButtonBindings();
    System.out.println("h");

    // CommandScheduler.getInstance().schedule(measureColors);

  }

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */

  // Configure the button bindings

  public Joystick getLeftJoy() {
    return leftJoy;
  }

  public Joystick getRightJoy() {
    return rightJoy;
  }

  public void setTagertColor(String color) {
    targetColor = color;
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // shootButton.whenPressed(new ShootCG());
    // shootButton.whenReleased(new NotShootCG());
    // storeButton.whenPressed(new AcquireCG(0.5, 0.5, 0.2));
    // storeButton.whenReleased(new AcquireCG(0, 0, 0));
    // fill in lines below with nueva command
    // storeButton.whenPressed();
    // storeButton.whenPressed()
    // new Aquire(0.5), new Shoot(0.3), new RoofMove(0.2)
    // storeButton.whenPressed(new )
    // new JoystickButton(rightJoy, 1).whileHeld(alignToBall);
    screwUp.whileHeld(new Screwing(1));
    screwDown.whileHeld(new Screwing(-1));
    reverseButton.whenPressed(new ReverseTankDrive());
    intakeBallButton.whenPressed(new ShootCG(0, 2, 0.3));
    intakeBallButton.whenReleased(new ShootCG(0, 2, 0));
    dropBallButton.whenPressed(new ShootCG(1, 1, 0.6, 0));
    dropBallButton.whenReleased(new ShootCG(0, 0, 2, 0));
    lastballbutton.whenPressed(new ShootCG(0, 0, 2, 0.3));
    lastballbutton.whenReleased(new ShootCG(0, 0, 2, 0));

    // gateButton.whileHeld(new gateblock(0.6));
    // gateButton.whenReleased(new gateblock(2));

    ACBeltEmergencyButton.whenPressed(new AcquireCG(-0.4, -0.4));
    // button = new JoystickButton(mechJoy, 1);

    // motorControler = new JoystickButton(mechJoy, 8);
    // button.whenPressed(new RotationControl());
    ///////////////////// Individual buttons for color control
    // testing///////////////
    /*
     * buttonRed.whenPressed(new ColorControl("R", colorSensor));
     * buttonBlue.whenPressed(new ColorControl("B", colorSensor));
     * buttonGreen.whenPressed(new ColorControl("G", colorSensor));
     * buttonYellow.whenPressed(new ColorControl("Y", colorSensor));
     */
    colorButton.whenPressed(new ColorControl(targetColor, colorSensor));
    colorCountingControler.whenPressed(new ColorControlCounter());
    motorControler.whileHeld(new MotorControl());
    hingeButton.whileHeld(new HingeControl(0.5));
    hingeButton.whenInactive(new HingeControl(-0.3));
    // Robot.m_robotContainer.sensor1.button.whileActive( new RotationControl());
    // button2 = new JoystickButton(joy,2);
    // button2.whenPressed(new PrintCommand("Command"

    new JoystickButton(leftJoy, 3).whileHeld(alignToBall);
  }

  /*
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
