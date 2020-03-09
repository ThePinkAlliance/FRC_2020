package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Constants;
import frc.robot.subsystems.Shooter;

public class ShooterAim extends PIDCommand {
  private Timer m_time = new Timer();
  Shooter m_shooter;
  public ShooterAim(Shooter subsystem) {
    super(
        new PIDController(Constants.shooterkP, Constants.shooterkI, Constants.shooterkD),
        subsystem::getLimelightXError,
        () -> 0,
        output -> subsystem.setTurretSpeed(output)
        // subsystem
        ); 
      m_shooter = subsystem;    
      m_time.start();
  }

  @Override
  public boolean isFinished() {
    if(m_time.getMatchTime() < 15)
      return m_shooter.getLimelightXError() < 0.5;
    else
      return false;
  }
}