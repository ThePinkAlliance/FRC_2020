package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class Climber extends SubsystemBase {
  private Solenoid leftHoldClamp = new Solenoid(RobotContainer.climberLeftSolPort);
  private Solenoid rightHoldClamp = new Solenoid(RobotContainer.climberRightSolPort);

  private DigitalInput leftLowLimitSwitch = new DigitalInput(RobotContainer.climberLeftLowLimitSwitch);
  private DigitalInput rightLowLimitSwitch = new DigitalInput(RobotContainer.climberRightLowLimitSwitch);

  private CANSparkMax leftClimberMotor = new CANSparkMax(RobotContainer.climberLeftCANID, MotorType.kBrushed);
  private CANSparkMax rightClimberMotor = new CANSparkMax(RobotContainer.climberRightCANID, MotorType.kBrushed);

  private boolean armed = false;

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
    if (leftSpeed < 0 && getLeftLowLimitSwitch())
      leftClimberMotor.set(0);
    else
      leftClimberMotor.set(leftSpeed); 
    if (rightSpeed < 0 && getRightLowLimitSwitch())
      rightClimberMotor.set(0);
    else
      rightClimberMotor.set(rightSpeed); 
  }

  // Returns the encoder position of the specified climber motor; true = left, false = right
  public double getClimberPos(boolean motor) {
    return motor ? leftClimberMotor.getEncoder().getPosition() : rightClimberMotor.getEncoder().getPosition();
  }

  public boolean getLeftLowLimitSwitch() {
    System.out.println(leftLowLimitSwitch.get());
    return leftLowLimitSwitch.get();
  }

  public boolean getRightLowLimitSwitch() {
    System.out.println(rightLowLimitSwitch.get());
    return rightLowLimitSwitch.get();
  }

  public void setSolenoids(boolean pos) {
    leftHoldClamp.set(pos);
    rightHoldClamp.set(pos);
  }

  public void setArmed(boolean arm) {
    armed = arm;
  }

  public boolean getArmed() {
    return armed;
  }
}