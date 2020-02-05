package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class Climber extends SubsystemBase {
  private Solenoid _holdClamp = new Solenoid(RobotContainer.climberSolPort);;

  private CANSparkMax leftClimberMotor = new CANSparkMax(RobotContainer.climberLeftCANID, MotorType.kBrushed);
  private CANSparkMax rightClimberMotor = new CANSparkMax(RobotContainer.climberRightCANID, MotorType.kBrushed);

  public Climber() {
  }

  @Override
  public void periodic() {
  }

  // Drives the climber motors to a target position using P control; true = left, false = right;
  public void setClimberPos(double target_position, boolean motor) {
    double climberMotorCommand = 0.00005 * (target_position - getClimberPos(motor));

    if (!motor)
      leftClimberMotor.set(climberMotorCommand);
    else
      rightClimberMotor.set(climberMotorCommand);
  }

  // Drive the climber motors seperately at the speeds input
  public void driveClimbers(double leftSpeed, double rightSpeed){
    leftClimberMotor.set(leftSpeed);
    rightClimberMotor.set(rightSpeed);
  }

  // Returns the encoder position of the specified climber motor; true = left, false = right
  public double getClimberPos(boolean motor) {
    return motor ? leftClimberMotor.getEncoder().getPosition() : rightClimberMotor.getEncoder().getPosition();
  }
}
