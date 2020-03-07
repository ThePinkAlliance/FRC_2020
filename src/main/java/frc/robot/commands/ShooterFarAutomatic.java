/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.Shooter;

public class ShooterFarAutomatic extends CommandBase {
  private final Shooter m_shooter;
  private final Conveyor m_conveyor;
  private Timer m_timer = new Timer();
  private Timer timer2 = new Timer();
  private enum Stage {
    SPIN_UP,
    WAIT, SHOOT
  }
  private Stage stage;

  public ShooterFarAutomatic(Shooter shooter, Conveyor conveyor) {
    m_shooter = shooter;
    m_conveyor = conveyor;

    addRequirements(m_shooter);
    addRequirements(m_conveyor);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_shooter.setServoPos(Constants.shooterFarPos);
    stage = Stage.SPIN_UP;
    m_timer.start();
    timer2.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    switch (stage) {
      case SPIN_UP:
        m_shooter.setFlywheelVelocityPID(m_shooter.getFarRPM());
        m_conveyor.setConveyorSpeed(0);
        if (m_shooter.getUptoSpeed(m_shooter.getFarRPM())) {
          SmartDashboard.putNumber("Time to Settle", timer2.get());
          m_timer.reset();
          stage = stage.WAIT;
        }
        break;
      
      case WAIT:
        m_shooter.setFlywheelVelocityPID(m_shooter.getFarRPM());
        m_conveyor.setConveyorSpeed(0);
        if (!m_shooter.getUptoSpeed(m_shooter.getFarRPM())) 
          stage = stage.SPIN_UP;
        else if (m_timer.hasPeriodPassed(0.13))
          stage= stage.SHOOT;
        break;  
      
      case SHOOT:
        m_shooter.setFlywheelVelocityPID(m_shooter.getFarRPM());
        if (m_shooter.shooterFlywheelEncoder.getVelocity() > m_shooter.getFarRPM() - 80)
          m_conveyor.setConveyorSpeed(1);
        else {
          m_conveyor.setConveyorSpeed(0);
        }  
        break;   
    }  
    System.out.println("Shooter Flywheel Velocity: " + m_shooter.shooterFlywheelEncoder.getVelocity());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_shooter.setFlywheelSpeed(0);
    m_conveyor.setConveyorSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
