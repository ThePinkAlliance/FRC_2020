package frc.robot.commands;

import frc.robot.subsystems.Collector;
import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.CommandBase;


public class CollectorManual extends CommandBase {
  private final Collector      m_collector;
  private       DoubleSupplier JSValue;
  
  public CollectorManual(Collector subsystem, DoubleSupplier jsvalue) {
    m_collector = subsystem;
    JSValue   = jsvalue;
    
    addRequirements(m_collector);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    m_collector.setCollectorSpeed(JSValue.getAsDouble());
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}