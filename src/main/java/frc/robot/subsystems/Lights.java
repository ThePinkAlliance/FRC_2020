package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class Lights extends SubsystemBase {
  public Spark lights = new Spark(RobotContainer.lightsPWMPort);

  public Lights() {
  }

  @Override
  public void periodic() {
  }

  public void setLights(double pattern) {
    lights.set(pattern);
  }
}
