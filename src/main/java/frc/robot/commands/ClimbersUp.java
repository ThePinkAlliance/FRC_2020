package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;
import frc.robot.Constants;

public class ClimbersUp extends CommandBase {
  private final Climber m_climber;

  public ClimbersUp(Climber subsystem) {
    m_climber = subsystem;

    addRequirements(m_climber);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    m_climber.setClimberPos(Constants.climberTop, Constants.leftClimber);
    m_climber.setClimberPos(Constants.climberTop, Constants.rightClimber);
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
