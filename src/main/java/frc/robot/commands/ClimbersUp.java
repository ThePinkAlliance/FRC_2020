package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;
import frc.robot.Constants;

public class ClimbersUp extends CommandBase {
  private final Climber m_climber;
  private Timer time;

  public ClimbersUp(Climber subsystem) {
    m_climber = subsystem;

    addRequirements(m_climber);
  }

  @Override
  public void initialize() {
    m_climber.setArmed(true);
    m_climber.setSolenoids(Constants.climbersUnlocked);
    time.start();
  }

  @Override
  public void execute() {
    if (time.hasPeriodPassed(0.25)) {
      m_climber.setClimberPos(Constants.climberTop, Constants.leftClimber);
      m_climber.setClimberPos(Constants.climberTop, Constants.rightClimber);
    }
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return Math.abs(m_climber.getClimberPos(true) - Constants.climberTop) > 10;
  }
}
