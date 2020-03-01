/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;

import edu.wpi.first.hal.sim.mockdata.RoboRioDataJNI;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ColorSensor;

public class HingeControl extends CommandBase {
  /**
   * Creates a new HingeControl.
   */

  boolean startsDown;
  boolean top = ColorSensor.getInstance().hinge.getSensorCollection().isRevLimitSwitchClosed();
  boolean bottom = ColorSensor.getInstance().hinge.getSensorCollection().isFwdLimitSwitchClosed();
  public HingeControl() {
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    startsDown = ColorSensor.getInstance().hinge.getSensorCollection().isFwdLimitSwitchClosed();
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    System.out.println("top: " + ColorSensor.getInstance().hinge.getSensorCollection().isRevLimitSwitchClosed());
    System.out.println("bottom: " + ColorSensor.getInstance().hinge.getSensorCollection().isFwdLimitSwitchClosed());
    if(startsDown == true){
      ColorSensor.getInstance().spinHinge(-0.2);
    }
    else{
      ColorSensor.getInstance().spinHinge(0.2);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    ColorSensor.getInstance().spinHinge(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(!startsDown && ColorSensor.getInstance().hinge.getSensorCollection().isFwdLimitSwitchClosed()){
      return true;
    }
    if(startsDown && ColorSensor.getInstance().hinge.getSensorCollection().isRevLimitSwitchClosed()){
      return true;
    }
    else{
      return false;
    }
  }
}
