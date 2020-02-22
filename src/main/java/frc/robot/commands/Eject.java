/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Collector;
import frc.robot.subsystems.Conveyor;

public class Eject extends CommandBase {
  private Conveyor m_conveyor;
  private Collector m_collector;

  public Eject(Conveyor conveyor, Collector collector) {
    m_conveyor = conveyor;
    m_collector = collector;

    addRequirements(m_conveyor);
    addRequirements(m_collector);
  }

  @Override
  public void initialize() {
    m_collector.setCollectorExtendSol(Constants.collectorExtended);
  }

  @Override
  public void execute() {
    m_conveyor.setConveyorSpeed(-1);
    m_collector.setCollectorSpeed(-1);
  }

  @Override
  public void end(boolean interrupted) {
    m_collector.setCollectorExtendSol(Constants.collectorRetracted);
    m_collector.setCollectorSpeed(0);
    m_conveyor.setConveyorSpeed(0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
