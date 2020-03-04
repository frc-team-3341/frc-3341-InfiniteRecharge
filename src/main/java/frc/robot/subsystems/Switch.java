/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;

public class Switch extends SubsystemBase {
    /**
     * Creates a new Switch.
     */
    private TalonSRX balance;
    private static Switch instance;

    public Switch() {
        balance = new TalonSRX(8);
        //setDefaultCommand(new Translating());
    }

    public static Switch getInstance(){
        if (instance == null)
            instance = new Switch();
        return instance;
    }

    public void move(double speed) {
        balance.set(ControlMode.PercentOutput, speed);
    }


    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        move(Robot.m_robotContainer.getMechJoy().getX());
    }

    public TalonSRX getBalanceTalon() {
        return balance;
    }
}