/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.subsystems.Base;

public class DriveStraightByEncoder extends CommandBase {

  private final Base m_base;
  private final double distance;
  private final double speed;

  /**
   * Creates a new DriveStraightByEncoder.
   */
  public DriveStraightByEncoder(Base base, double pdistance, double pspeed) {

    m_base = base;
    distance = pdistance;
    speed = pspeed;

    addRequirements(m_base);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

    //reset the encoders and drive forward at specified speed
    m_base.resetEncoders();
    m_base.tankDriveByJoystick(speed, speed);

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_base.tankDriveByJoystick(0,0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Math.abs(m_base.getAverageEncoderDistance()) >= distance;
  }
}