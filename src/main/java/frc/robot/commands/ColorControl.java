/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.ColorSensor;

public class ColorControl extends CommandBase {
  /**
   * Creates a new ColorControl.
   */
  //private final ColorSensor colorSensor;
  private String measuredColor;
  private String inputColor;

  public ColorControl(String c, ColorSensor sensor) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(sensor);
    inputColor = c; 
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
      ColorSensor.getInstance().spinWheel(0.2);
       //ColorSensor.getInstance().colorControl(inputColor);
       ColorSensor.getInstance().printColors(); 
       ColorSensor.getInstance().matchColor();  
       measuredColor = ColorSensor.getInstance().matchColor();
       

       
   
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    ColorSensor.getInstance().spinWheel(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return(FMStoColor(inputColor).equals(measuredColor));
  }

  public static String FMStoColor(String input){
    if(input.equals("R")){
      return "red";
    }
    else if(input.equals("B")){
      return "blue";
    }
    else if(input.equals("G")){
      return "green";
    }
    else if(input.equals("Y")){
      return "yellow";
    }
    else{
      return "unknown";
    }
  }
}
