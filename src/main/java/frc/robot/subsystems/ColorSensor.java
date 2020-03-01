/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.commands.MeasureColors;

import java.util.Set;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.ColorSensorV3;

//import org.graalvm.compiler.java.GraphBuilderPhase.Instance;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.util.Color;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorMatch;

/**
 * Add your docs here.
 */
public class ColorSensor extends SubsystemBase {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private final I2C.Port i2cPort;
  private final ColorSensorV3 m_colorSensor;
  Color detectedColor;
  private  ColorMatch m_colorMatcher; 
  private final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
  private final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
  private final Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
  private final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);
  private final TalonSRX wheel = new TalonSRX(5);
  public TalonSRX hinge = new TalonSRX(3);
  public Joystick joy = new Joystick(0);
  // the button is manual button!!!
  public JoystickButton button = new JoystickButton(joy, 8);
  private static ColorSensor instance;


  public ColorSensor()
  {
    i2cPort = I2C.Port.kOnboard;
    m_colorSensor = new ColorSensorV3(i2cPort);
    detectedColor  = null;
    m_colorMatcher = new ColorMatch();
    m_colorMatcher.addColorMatch(kBlueTarget);
    m_colorMatcher.addColorMatch(kGreenTarget);
    m_colorMatcher.addColorMatch(kRedTarget);
    m_colorMatcher.addColorMatch(kYellowTarget);
    wheel.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 1);
    wheel.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    wheel.setSelectedSensorPosition(0);
    System.out.println("color sensor constructor");
  }

  public static ColorSensor getInstance(){
    if (instance == null){
      instance = new ColorSensor();
    }
    return instance;
  }

  public String printColors(){ //don't need to know if sensing RGB
    String RGB = "";
    detectedColor = m_colorSensor.getColor();
    RGB = "Red: " + detectedColor.red + ", Green: " + detectedColor.green + ", Blue: " + detectedColor.blue;
    System.out.println(RGB);
    return RGB;
  }

  public String matchColor(){
    
    String colorString;
    ColorMatchResult match = m_colorMatcher.matchClosestColor(m_colorSensor.getColor());
    //System.out.println("I'm in matchColor !!" + match.color);

    if (match.color == kBlueTarget) {
      colorString = "blue";
    } else if (match.color == kRedTarget) {
      colorString = "red";
    } else if (match.color == kGreenTarget) {
      colorString = "green";
    } else if (match.color == kYellowTarget) {
      colorString = "yellow";
    } else {
      colorString = "unknown";
    }
    //System.out.println(colorString);
    return colorString;

    /*colorString += " , confidence: " + match.confidence();
    colorString += m_colorSensor.hasReset();
    return colorString;*/
  }

 public double getTicks(){
   return wheel.getSelectedSensorPosition();
 }
 public boolean getButton(){
   return button.get();
 }

 public void spinWheel(double speed){

  wheel.set(ControlMode.PercentOutput, speed);
 }

 public void spinHinge(double speed){
  hinge.set(ControlMode.PercentOutput, speed);
 }

 public void resetSensorPosition(){
   wheel.setSelectedSensorPosition(0);
 }

 public void colorControl(String c){
   wheel.set(ControlMode.PercentOutput, 0.2);
   if(c.equals("R")){
     System.out.println("input is red");
     if(matchColor().equals("red")){
     wheel.set(ControlMode.PercentOutput, 0);
     }
    }
   else if(c.equals("B")){
     if(matchColor().equals("blue")){
     wheel.set(ControlMode.PercentOutput, 0);
     }
   }
   else if(c.equals("G")){
     if(matchColor().equals("green")){
       wheel.set(ControlMode.PercentOutput, 0);
     }
   }
   else if(c.equals("Y")){
     if(matchColor().equals("yellow")){
       wheel.set(ControlMode.PercentOutput, 0);
     }
   }
   else{
     wheel.set(ControlMode.PercentOutput, 0.2);
   }
 }



 @Override
  public void periodic() {
    // This method will be called once per scheduler run
   // wheel.set(ControlMode.PercentOutput, 0.4);

  }
  

  //@Override
  //public void setDefaultCommand(new MeasureColors(this)) {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  // setDefaultCommand(new MeasureColors(this));
  //}
}
