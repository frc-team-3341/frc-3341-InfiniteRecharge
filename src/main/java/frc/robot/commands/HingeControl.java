/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ColorSensor;

public class HingeControl extends CommandBase {
  /**
   * Creates a new HingeControl.
   */
  
  boolean startsDown;
  double speed;
  boolean top = ColorSensor.getInstance().hinge.getSensorCollection().isFwdLimitSwitchClosed();
  boolean bottom = ColorSensor.getInstance().hinge.getSensorCollection().isRevLimitSwitchClosed();
  public HingeControl(double m_speed) {
    // Use addRequirements() here to declare subsystem dependencies.
    speed = m_speed;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    startsDown = !ColorSensor.getInstance().hinge.getSensorCollection().isRevLimitSwitchClosed();
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // System.out.println("bottom: " + ColorSensor.getInstance().hinge.getSensorCollection().isRevLimitSwitchClosed());
    // System.out.println("top: " + ColorSensor.getInstance().hinge.getSensorCollection().isFwdLimitSwitchClosed());
    // if(startsDown == true){
    //   ColorSensor.getInstance().spinHinge(0.5);//
    // }
    // else{
    //   ColorSensor.getInstance().spinHinge(-0.3);
    // }
    // //positive is down, negative is up

    // //gets the state of the limites swiches after it stop getting contact
    // top = !ColorSensor.getInstance().hinge.getSensorCollection().isFwdLimitSwitchClosed();
    // bottom = !ColorSensor.getInstance().hinge.getSensorCollection().isRevLimitSwitchClosed();

    ColorSensor.getInstance().spinHinge(speed);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    ColorSensor.getInstance().spinHinge(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    // if(top && startsDown){
    //   return true;
    // }
    // else if (bottom && !startsDown){
    //   return true;
    // }
    // else{
    //   return false;
    // }
    
    return false;
  }
}
