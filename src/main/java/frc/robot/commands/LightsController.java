package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.Lights;

public class LightsController extends CommandBase {
  private final Lights m_lights;
  private final Conveyor m_conveyor;
  
  public LightsController(Lights lights, Conveyor conveyor) {
    m_lights    = lights;
    m_conveyor  = conveyor;

    addRequirements(m_lights);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    if (m_conveyor.getMagazineCapacity()) {
      m_lights.setLights(Constants.lightsBlueGreen);
    } else if (m_conveyor.getBreakbeam())
      m_lights.setLights(Constants.lightsYellow);
    else
      m_lights.setLights(Constants.lightsColor1LarsonScanner);
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}