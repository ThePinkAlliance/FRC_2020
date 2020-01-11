package frc.robot.commands;

import frc.robot.subsystems.Shooter;
import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.CommandBase;


public class FlywheelManual extends CommandBase {
  private final Shooter        m_shooter;
  private       DoubleSupplier JSValue;
  
  public FlywheelManual(Shooter subsystem, DoubleSupplier jsvalue) {
    m_shooter = subsystem;
    JSValue   = jsvalue;

    addRequirements(m_shooter);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    m_shooter.setFlywheelSpeed(JSValue.getAsDouble());
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}