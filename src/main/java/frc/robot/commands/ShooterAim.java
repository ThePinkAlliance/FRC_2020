/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Constants;
import frc.robot.subsystems.Shooter;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class ShooterAim extends PIDCommand {
  /**
   * Creates a new ShooterAim.
   */
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
          // Use the output here
        });

    addRequirements(subsystem);
    // Configure additional PID options by calling `getController` here.
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return getController().atSetpoint();
  }
}
