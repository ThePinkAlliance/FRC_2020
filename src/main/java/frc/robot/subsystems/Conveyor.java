package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class Conveyor extends SubsystemBase {
  public CANSparkMax conveyorMotor = new CANSparkMax(RobotContainer.conveyorBeltCANID, MotorType.kBrushed);
  
  public DigitalInput breakbeam = new DigitalInput(0);

  public Conveyor() {
  }

  @Override
  public void periodic() {
  }

  public boolean getBreakbeam() {
    return !breakbeam.get();
  }

  public void setConveyorSpeed(double speed) {
    conveyorMotor.set(speed);
  }
}
