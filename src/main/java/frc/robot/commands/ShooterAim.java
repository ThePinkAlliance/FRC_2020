package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Constants;
import frc.robot.subsystems.Shooter;

public class ShooterAim extends PIDCommand {
  public ShooterAim(Shooter subsystem) {
    super(
        new PIDController(Constants.shooterkP, Constants.shooterkI, Constants.shooterkD),
        subsystem::getLimelightError,
        () -> 0,
        output -> subsystem.setTurretSpeed(output),
        subsystem
        );

    // addRequirements(subsystem);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}