package frc.robot.commands;

import frc.robot.subsystems.Shooter;
import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.CommandBase;


public class FlywheelManual extends CommandBase {
  // Declare Variables
  private final Shooter m_shooter;
  private DoubleSupplier JSValue;
  
  // Constuctor
  public FlywheelManual(Shooter subsystem, DoubleSupplier jsvalue) {
    // Recieve Inputs
    m_shooter = subsystem;
    JSValue = jsvalue;

    // Define Requirements
    addRequirements(m_shooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_shooter.setFlywheelSpeed(JSValue.getAsDouble());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
