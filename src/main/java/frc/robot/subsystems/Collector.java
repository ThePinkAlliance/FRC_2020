package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class Collector extends SubsystemBase {
  private Solenoid collectorExtendSol = new Solenoid(RobotContainer.collectorSolPort);
  CANSparkMax collector = new CANSparkMax(RobotContainer.collectorRollerCANID, MotorType.kBrushed);

  public Collector() {
    collector.setInverted(true);
  }

  public void setCollectorSpeed(double collectorPower) {
    collectorPower = collectorPower * Constants.collectorMotorGain;
    
    System.out.println("Flywheel Power: " + collectorPower);

    collector.set(collectorPower);
  }

  public void setCollectorExtendSol(boolean extend) {
    collectorExtendSol.set(extend);
  }

  public boolean getCollectorExtendSol() {
    return collectorExtendSol.get();
  }
}
