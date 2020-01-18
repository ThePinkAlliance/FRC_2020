package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;


public class Shooter extends SubsystemBase {
  public CANSparkMax shooterFlywheel = new CANSparkMax(RobotContainer.shooterFlywheelCANID, MotorType.kBrushless);
  CANEncoder shooterFlywheelEncoder = new CANEncoder(shooterFlywheel);

  public Shooter() {
    shooterFlywheel.setInverted(true);
  }

  @Override
  public void periodic() {
  }

  public void setFlywheelSpeed(double flywheelPower) {
    flywheelPower = flywheelPower * Constants.shooterFlywheelMotorGain;
    
    System.out.println("Flywheel Power: " + flywheelPower);
    System.out.println("Flywheel Velocity: " + shooterFlywheelEncoder.getVelocity() + " RPM");

    shooterFlywheel.set(flywheelPower);
  }
}