package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class Conveyor extends SubsystemBase {
  public CANSparkMax conveyorMotor = new CANSparkMax(RobotContainer.conveyorBeltCANID, MotorType.kBrushless);
  
  public DigitalInput breakbeam1 = new DigitalInput(RobotContainer.breakbeam1DIOPort);
  public DigitalInput breakbeam2 = new DigitalInput(RobotContainer.breakbeam2DIOPort);

  public Conveyor() {
  }

  @Override
  public void periodic() {
  }

  public boolean getBreakbeam() {
    return !breakbeam1.get() || !breakbeam2.get();
  }

  public void setConveyorSpeed(double speed) {
    conveyorMotor.set(speed);
  }
}
