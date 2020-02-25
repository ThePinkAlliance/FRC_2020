/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Climber;

public class ClimbersManual extends CommandBase {
  private Climber m_climber;
  private DoubleSupplier leftSpeed;
  private DoubleSupplier rightSpeed;

  public ClimbersManual(Climber climber, DoubleSupplier leftStick, DoubleSupplier rightStick) {
    m_climber = climber;
    leftSpeed = leftStick;
    rightSpeed = rightStick;

    addRequirements(m_climber);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_climber.setSolenoids(Constants.climbersUnlocked);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    SmartDashboard.putBoolean("Climber Left Low Switch", m_climber.getLeftLowLimitSwitch());
    SmartDashboard.putBoolean("Climber Right Low Switch", m_climber.getRightLowLimitSwitch());
    SmartDashboard.putNumber("Climber Left Encoder Value", m_climber.getClimberPos(Constants.leftClimber));
    SmartDashboard.putNumber("Climber Right Encoder Value", m_climber.getClimberPos(Constants.rightClimber));
    SmartDashboard.putNumber("Climber Right Commanded Value", rightSpeed.getAsDouble());
    SmartDashboard.putNumber("Climber Left Commanded Value", leftSpeed.getAsDouble());

    if (m_climber.getArmed()) {
      if (Math.abs(leftSpeed.getAsDouble()) > 0.2 || (Math.abs(rightSpeed.getAsDouble()) > 0.2)) {
        m_climber.driveClimbers(leftSpeed.getAsDouble(), rightSpeed.getAsDouble());
        m_climber.setSolenoids(Constants.climbersUnlocked); 
      } else
        m_climber.setSolenoids(Constants.climbersLocked);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_climber.driveClimbers(0, 0);
    m_climber.setSolenoids(Constants.climbersLocked);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
