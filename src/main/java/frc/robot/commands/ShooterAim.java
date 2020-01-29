package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Constants;
import frc.robot.subsystems.Shooter;


public class ShooterAim extends PIDCommand {
  public ShooterAim(Shooter subsystem) {
    super(
        // The controller that the command will us
        new PIDController(Constants.shooterkP, Constants.shooterkI, Constants.shooterkD),
        // This should return the measurement
        subsystem::getLimelightError,
        // This should return the setpoint (can also be a constant)
        () -> 0,
        // This uses the output
        output -> {
          subsystem.setTurretSpeed(output);
        });

    addRequirements(subsystem);
  }

  @Override
  public boolean isFinished() {
    return getController().atSetpoint();
  }
}
