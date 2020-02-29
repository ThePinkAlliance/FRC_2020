package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class Climber extends SubsystemBase {
  private Solenoid leftHoldClamp = new Solenoid(RobotContainer.climberLeftSolPort);

  private DigitalInput leftLowLimitSwitch = new DigitalInput(RobotContainer.climberLeftLowLimitSwitch);
  private DigitalInput rightLowLimitSwitch = new DigitalInput(RobotContainer.climberRightLowLimitSwitch);

  private CANSparkMax leftClimberMotor = new CANSparkMax(RobotContainer.climberLeftCANID, MotorType.kBrushless);
  private CANSparkMax rightClimberMotor = new CANSparkMax(RobotContainer.climberRightCANID, MotorType.kBrushless);

  private CANEncoder leftClimberEncoder = new CANEncoder(leftClimberMotor);
  private CANEncoder rightClimberEncoder = new CANEncoder(rightClimberMotor);

  private boolean armed = true;

  public Climber() {
    rightClimberMotor.setInverted(true);
  }

  @Override
  public void periodic() {
  }

  public void resetLeftClimber() {
    leftClimberEncoder.setPosition(0);  
  }

  public void resetRightClimber() {
    rightClimberEncoder.setPosition(0);
  }

  // Drives the climber motors to a target position using P control; true = left, false = right;
  public boolean setClimberPos(double right_target_position, double left_target_position) {
    boolean leftdone = false;
    boolean rightdone = false;
    SmartDashboard.putNumber("Climber Left Encoder Value", getClimberPos(Constants.leftClimber));
    SmartDashboard.putNumber("Climber Right Encoder Value", getClimberPos(Constants.rightClimber));
    if(leftClimberEncoder.getPosition() > left_target_position) {
      leftClimberMotor.set(-1);
    } else {
      leftClimberMotor.set(0); 
      leftdone = true;
    }
    
    if(rightClimberEncoder.getPosition() > right_target_position) {
      rightClimberMotor.set(-1);
    } else {
      rightClimberMotor.set(0); 
      rightdone = true;
    }

    return leftdone && rightdone;     
  }

  // Drive the climber motors seperately at the speeds input
  public void driveClimbers(double leftSpeed, double rightSpeed){
    if (leftSpeed < 0)
      leftSpeed = -leftSpeed * leftSpeed;
    else
      leftSpeed = leftSpeed * leftSpeed;
    if (rightSpeed < 0)
      rightSpeed = -rightSpeed * rightSpeed;
    else
      rightSpeed = rightSpeed * rightSpeed;      

    if ( (leftSpeed > 0 && getLeftLowLimitSwitch()) || (leftSpeed < 0 && (getClimberPos(Constants.leftClimber) < Constants.climberMax)) )
      leftClimberMotor.set(0);
    else
      leftClimberMotor.set(leftSpeed); 
    if ( (rightSpeed > 0 && getRightLowLimitSwitch()) || (rightSpeed < 0 && (getClimberPos(Constants.rightClimber) < Constants.climberMax)) )
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
  }

  public void setArmed(boolean arm) {
    armed = arm;
  }

  public boolean getArmed() {
    return armed;
  }
}