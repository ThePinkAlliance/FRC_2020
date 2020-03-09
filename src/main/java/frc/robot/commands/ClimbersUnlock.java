package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Climber;

public class ClimbersUnlock extends CommandBase {
  private Climber m_climber;
  private boolean finished = false;

  public ClimbersUnlock(Climber climber) {
    m_climber = climber;

    addRequirements(m_climber);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_climber.setSolenoids(Constants.climbersLocked);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // m_climber.setSolenoids(Constants.climbersLocked);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_climber.setSolenoids(Constants.climbersUnlocked);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return finished;
  }
}
