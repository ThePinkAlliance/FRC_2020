package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class Shooter extends SubsystemBase {
  public CANSparkMax shooterTurret = new CANSparkMax(RobotContainer.shooterRotateCANID, MotorType.kBrushed);
  public CANSparkMax shooterFlywheel = new CANSparkMax(RobotContainer.shooterFlywheelCANID, MotorType.kBrushless);

  public double shooterLimelight = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);

  public CANEncoder shooterFlywheelEncoder = new CANEncoder(shooterFlywheel);

  public Servo shooterRightServo = new Servo(RobotContainer.shooterRightServoPort);
  public Servo shooterLeftServo = new Servo(RobotContainer.shooterLeftServoPort);

  public Shooter() {
    shooterFlywheel.setInverted(true);
  }

  @Override
  public void periodic() {
  }

  public double getLimelightError() {
    return shooterLimelight;
  }

  public boolean getLockonState() {
    return  -5 < getLimelightError() || getLimelightError() < 5;
  }

  public void setTurretSpeed(double turretPower) {
    turretPower = turretPower * Constants.shooterTurretMotorGain;
    
    shooterTurret.set(turretPower);
  }

  public void setFlywheelSpeed(double flywheelPower) {    
    System.out.println("Flywheel Power: " + flywheelPower);
    System.out.println("Flywheel Velocity: " + shooterFlywheelEncoder.getVelocity() + " RPM");

    shooterFlywheel.set(flywheelPower);
  }

  public boolean getUptoSpeed(double velocity) {
    if (shooterFlywheel.getEncoder().getVelocity() > velocity)
      return true;
    else
      return false;
  }

  public void setServoPos(double pos) {
    shooterRightServo.set(pos);
    shooterLeftServo.set(1-pos);
  }
}