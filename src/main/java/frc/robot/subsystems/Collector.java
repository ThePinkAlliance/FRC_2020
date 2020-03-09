package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class Collector extends SubsystemBase {
  private Solenoid collectorExtendSol = new Solenoid(RobotContainer.collectorSolPort);
  CANSparkMax collector = new CANSparkMax(RobotContainer.collectorRollerCANID, MotorType.kBrushless);
  CANEncoder collectorEncoder = new CANEncoder(collector); 
  private double collectorPower = 0;

  public Collector() {
    collector.setInverted(true);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Collector Power", collector.get());
    SmartDashboard.putNumber("Collector Velocity: ", collectorEncoder.getVelocity());
    SmartDashboard.putBoolean("Collector Solenoid", collectorExtendSol.get());
  }

  public void setCollectorSpeed(double collector_power) {
    collectorPower = collector_power * Constants.collectorMotorGain;
    collector.set(collectorPower);
  }

  public void setCollectorExtendSol(boolean extend) {
    collectorExtendSol.set(extend);
  }

  public boolean getCollectorExtendSol() {
    return collectorExtendSol.get();
  }
}
