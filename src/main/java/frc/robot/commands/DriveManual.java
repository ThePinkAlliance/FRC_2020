package frc.robot.commands;

import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Base;


public class DriveManual extends CommandBase {
  private final Base           m_base;
  private       DoubleSupplier rightJSValue;
  private       DoubleSupplier leftJSValue;

  public DriveManual(Base subsystem, DoubleSupplier rightjsvalue, DoubleSupplier leftjsvalue) {
    m_base       = subsystem;
    rightJSValue = rightjsvalue;
    leftJSValue  = leftjsvalue;

    addRequirements(m_base);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    m_base.tankDriveByJoystick(leftJSValue.getAsDouble(), rightJSValue.getAsDouble());
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}