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

public class ConveyorManual extends CommandBase {
  private Conveyor m_conveyor;

  public ConveyorManual(Conveyor conveyor) {
    m_conveyor = conveyor;

    addRequirements(m_conveyor);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    if(m_conveyor.getMagazineCapacity()) {
      m_conveyor.setConveyorSpeed(0);
    } else {
      m_conveyor.setConveyorSpeed(Constants.conveyorSpeed);
    }

  }

  @Override
  public void end(boolean interrupted) {
    m_conveyor.setConveyorSpeed(0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
