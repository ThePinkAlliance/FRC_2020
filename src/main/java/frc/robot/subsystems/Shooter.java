package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;


public class Shooter extends SubsystemBase {
  public CANSparkMax shooterFlywheel = new CANSparkMax(RobotContainer.shooterFlywheelCANID, MotorType.kBrushless);
  CANEncoder rightFrontEncoder = new CANEncoder(shooterFlywheel);

  public Shooter() {
    shooterFlywheel.setInverted(true);
  }

  @Override
  public void periodic() {
  }

  public void setFlywheelSpeed(double speed) {
    System.out.println("Flywheel Command: " + speed);
    shooterFlywheel.set(speed);
  }
}