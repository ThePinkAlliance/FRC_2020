package frc.robot.commands;

import frc.robot.subsystems.Collector;
import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class CollectorManual extends CommandBase {
  private final Collector      m_collector;
  
  public CollectorManual(Collector subsystem) {
    m_collector = subsystem;
    addRequirements(m_collector);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    m_collector.setCollectorExtendSol(Constants.collectorExtended);
    m_collector.setCollectorSpeed(Constants.collectorCollectSpeed);
  }

  @Override
  public void end(boolean interrupted) {
    m_collector.setCollectorExtendSol(Constants.collectorRetracted);
    m_collector.setCollectorSpeed(0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}