package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;


public class Base extends SubsystemBase {
  private CANSparkMax rightFront = new CANSparkMax(RobotContainer.baseRightFrontCANID, MotorType.kBrushless);
  private CANSparkMax leftFront  = new CANSparkMax(RobotContainer.baseLeftFrontCANID, MotorType.kBrushless);
  private CANSparkMax rightBack  = new CANSparkMax(RobotContainer.baseRightBackCANID, MotorType.kBrushless);
  private CANSparkMax leftBack   = new CANSparkMax(RobotContainer.baseLeftBackCANID, MotorType.kBrushless);

  private DifferentialDrive diffDrive = new DifferentialDrive(leftFront, rightFront);

  CANEncoder rightFrontEncoder = new CANEncoder(rightFront);
  CANEncoder leftFrontEncoder  = new CANEncoder(leftFront);
  CANEncoder rightBackEncoder  = new CANEncoder(rightBack);
  CANEncoder leftBackEncoder   = new CANEncoder(leftBack);

  private double rightGovernor = 0;
  private double leftGovernor = 0;

  public Base() {
    rightFront.setInverted(false);
    leftFront.setInverted(false);
    rightBack.setInverted(false);
    leftBack.setInverted(false);

    rightBack.follow(rightFront);
    leftBack.follow(leftFront);
  }

  @Override
  public void periodic() {
  }

  public void tankDriveByJoystick(double left, double right) {
    rightGovernor = right * right;
    leftGovernor = left * left;
    if (right < 0) {
      rightGovernor = rightGovernor * -1;
    }
    if (left < 0) {
      leftGovernor = leftGovernor * -1;
    }

    rightGovernor = -right * Constants.baseMotorGain;
    leftGovernor = -left * Constants.baseMotorGain;

    diffDrive.tankDrive(leftGovernor, rightGovernor);
	}
}