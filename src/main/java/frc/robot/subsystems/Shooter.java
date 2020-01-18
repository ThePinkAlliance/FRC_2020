package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
// import frc.robot.RobotContainer;


public class Shooter extends SubsystemBase {
  public WPI_TalonSRX shooterFlywheel = null;

  public Shooter() {
    // shooterFlywheel = new WPI_TalonSRX(RobotContainer.shooterFlywheelCANID);
  }

  @Override
  public void periodic() {
  }

  public void setFlywheelSpeed(double speed) {
    shooterFlywheel.set(speed);
  }
}