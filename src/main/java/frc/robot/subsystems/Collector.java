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

  public Collector() {
    collector.setInverted(true);
  }

  public void setCollectorSpeed(double collectorPower) {
    collectorPower = collectorPower * Constants.collectorMotorGain;
    
    System.out.println("Collector Power: " + collectorPower);
    SmartDashboard.putNumber("Collector Velocity: ", collectorEncoder.getVelocity());
    collector.set(collectorPower);
  }

  public void setCollectorExtendSol(boolean extend) {
    collectorExtendSol.set(extend);
  }

  public boolean getCollectorExtendSol() {
    return collectorExtendSol.get();
  }
}
