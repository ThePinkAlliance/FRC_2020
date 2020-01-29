package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class Collector extends SubsystemBase {
  public Spark collector = new Spark(1);
  public DigitalInput breakbeam = new DigitalInput(0);

  public Collector() {
    collector.setInverted(true);
  }

  public void setCollectorSpeed(double collectorPower) {
    collectorPower = collectorPower * Constants.collectorMotorGain;
    
    System.out.println("Flywheel Power: " + collectorPower);

    collector.set(collectorPower);
  }

  public boolean getBreakbeam() {
    return breakbeam.get();
  }
}
