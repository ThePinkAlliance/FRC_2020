/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.Shooter;

public class ShooterCloseAutomatic extends CommandBase {
  private final Shooter m_shooter;
  private final Conveyor m_conveyor;

  public ShooterCloseAutomatic(Shooter shooter, Conveyor conveyor) {
    m_shooter = shooter;
    m_conveyor = conveyor;

    addRequirements(m_shooter);
    addRequirements(m_conveyor);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_shooter.setServoPos(Constants.shooterClosePos);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(m_shooter.getUptoSpeed(Constants.shooterVelocity))
      m_conveyor.setConveyorSpeed(Constants.conveyorSpeed);

    m_shooter.setFlywheelSpeed(0.7);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
