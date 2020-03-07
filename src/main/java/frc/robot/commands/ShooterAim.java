package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Constants;
import frc.robot.subsystems.Shooter;

public class ShooterAim extends PIDCommand {
  Shooter m_shooter;
  public ShooterAim(Shooter subsystem) {
    super(
        new PIDController(Constants.shooterkP, Constants.shooterkI, Constants.shooterkD),
        subsystem::getLimelightError,
        () -> 0,
        output -> subsystem.setTurretSpeed(output)
        // subsystem
        ); 
      m_shooter = subsystem;    
  }

  @Override
  public boolean isFinished() {
    return m_shooter.getLimelightError() < 1;
  }
}