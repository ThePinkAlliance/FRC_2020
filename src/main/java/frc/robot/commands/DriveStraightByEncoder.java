package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Base;

public class DriveStraightByEncoder extends CommandBase {

  private final Base m_base;
  private final double distance;
  private final double speed;

  /**
   * Creates a new DriveStraightByEncoder.
   */
  public DriveStraightByEncoder(Base base, double pdistance, double pspeed) {

    m_base = base;
    distance = pdistance;
    speed = pspeed;

    addRequirements(m_base);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

    //reset the encoders and drive forward at specified speed
    m_base.resetEncoders();

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_base.tankDriveByJoystick(speed, speed);
    SmartDashboard.putNumber("Average Base Encoder Value", m_base.getAverageEncoderDistance());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    System.out.println("Done Driving");
    m_base.tankDriveByJoystick(0,0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Math.abs(m_base.getAverageEncoderDistance()) >= distance;
  }
}
