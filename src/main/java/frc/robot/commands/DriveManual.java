package frc.robot.commands;

import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Base;


public class DriveManual extends CommandBase {
  // Declare Variables
  private final Base m_base;
  private DoubleSupplier rightJSValue;
  private DoubleSupplier leftJSValue;

  // Constructor
  public DriveManual(Base subsystem, DoubleSupplier rightjsvalue, DoubleSupplier leftjsvalue) {
    // Recieve Values
    m_base = subsystem;
    rightJSValue = rightjsvalue;
    leftJSValue = leftjsvalue;

    // Define Requirements
    addRequirements(m_base);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_base.tankDriveByJoystick(leftJSValue.getAsDouble(), rightJSValue.getAsDouble());
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
