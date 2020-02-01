/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class Climber extends SubsystemBase {
  
  private Solenoid _holdClamp = null;

  private CANSparkMax leftClimberMotor = new CANSparkMax(RobotContainer.climberLeftCANID, MotorType.kBrushed);
  private CANSparkMax rightClimberMotor = new CANSparkMax(RobotContainer.climberRightCANID, MotorType.kBrushed);

  
  /**
   * Creates a new Climber.
   */
  public Climber() {

    _holdClamp = new Solenoid(RobotContainer.climberSolPort);
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  //this method drives the motors to 
  public void driveClimbers(double leftSpeed, double rightSpeed){
    leftClimberMotor.set(leftSpeed);
    rightClimberMotor.set(rightSpeed);
  }
}
