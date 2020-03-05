package frc.robot.commands;

import frc.robot.subsystems.Collector;
import frc.robot.subsystems.Conveyor;
import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class CollectorManual extends CommandBase {
  private final Collector      m_collector;
  private final Conveyor m_conveyor;
  
  public CollectorManual(Collector subsystem, Conveyor conveyor) {
    m_collector = subsystem;
    m_conveyor = conveyor;
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