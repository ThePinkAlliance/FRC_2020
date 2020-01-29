package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Base;
import frc.robot.subsystems.Collector;
import frc.robot.subsystems.Lights;
import frc.robot.subsystems.Shooter;

public class LightsManual extends CommandBase {
  private final Lights m_lights;
  private final Collector m_collector;
  private final Base m_base;
  private final Shooter m_shooter;
  
  public LightsManual(Lights lights, Collector collector, Base base, Shooter shooter) {
    m_lights = lights;
    m_collector = collector;
    m_base = base;
    m_shooter = shooter;

    addRequirements(m_lights);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    if (!m_collector.getBreakbeam()) {
      m_lights.setLights(Constants.lightsYellow);
    } else if (m_shooter.getLockonState()) {
      m_lights.setLights(Constants.lightsGreen);
    } else {
      m_lights.setLights(Constants.lightsColor1HeartbeatFast);
    }
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
