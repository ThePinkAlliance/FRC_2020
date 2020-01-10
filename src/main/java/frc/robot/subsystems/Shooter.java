package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;


public class Shooter extends SubsystemBase {
  // Motors
  public WPI_TalonSRX shooterFlywheel = null;

  /**
   * Creates a new ExampleSubsystem.
   */
  public Shooter() {
    shooterFlywheel = new WPI_TalonSRX(RobotContainer.shooterFlywheelCANID);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setFlywheelSpeed(double speed) {
    shooterFlywheel.set(speed);
  }
}
