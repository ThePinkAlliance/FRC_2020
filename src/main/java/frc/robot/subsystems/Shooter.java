package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class Shooter extends SubsystemBase {
  public CANSparkMax shooterTurret = new CANSparkMax(RobotContainer.shooterRotateCANID, MotorType.kBrushed);
  public CANSparkMax shooterFlywheel = new CANSparkMax(RobotContainer.shooterFlywheelCANID, MotorType.kBrushless);

  public double shooterLimelight = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);

  public CANEncoder shooterFlywheelEncoder = new CANEncoder(shooterFlywheel);

  public Servo shooterRightServo = new Servo(RobotContainer.shooterRightServoPort);
  public Servo shooterLeftServo = new Servo(RobotContainer.shooterLeftServoPort);

  private CANPIDController shooterPIDController;
  public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput, closeRPM, farRPM;

  public Shooter() {
    shooterFlywheel.setInverted(true);
    shooterPIDController = shooterFlywheel.getPIDController();
   
    kP = 0.0005;
    kI = 0.5e-7;
    kD = 0.0002;
    kIz = 0.000;
    kFF = 0.000165;
    kMaxOutput = 1;
    kMinOutput = 0;
    closeRPM = 3500; 
    farRPM = 4800;

    shooterPIDController.setP(kP);
    shooterPIDController.setI(kI);
    shooterPIDController.setD(kD);
    shooterPIDController.setIZone(kIz);
    shooterPIDController.setFF(kFF);
    shooterPIDController.setOutputRange(kMinOutput, kMaxOutput);

    SmartDashboard.putNumber("Shooter P Gain: ", kP);
    SmartDashboard.putNumber("Shooter I Gain: ", kI);
    SmartDashboard.putNumber("Shooter D Gain: ", kD);
    SmartDashboard.putNumber("Shooter Iz Gain: ", kIz);
    SmartDashboard.putNumber("Shooter FF Gain: ", kFF);
    SmartDashboard.putNumber("Shooter Max Output: ", kMaxOutput);
    SmartDashboard.putNumber("Shooter Min Output: ", kMinOutput);
    SmartDashboard.putNumber("Shooter Flywheel Close RPM: ", closeRPM);
    SmartDashboard.putNumber("Shooter Flywheel Far RPM: ", farRPM);

  }

  @Override
  public void periodic() {
    // read PID coefficients from SmartDashboard
    double p = SmartDashboard.getNumber("Shooter P Gain: ", 0);
    double i = SmartDashboard.getNumber("Shooter I Gain: ", 0);
    double d = SmartDashboard.getNumber("Shooter D Gain: ", 0);
    double iz = SmartDashboard.getNumber("Shooter Iz Gain: ", 0);
    double ff = SmartDashboard.getNumber("Shooter FF Gain: ", 0);
    double max = SmartDashboard.getNumber("Shooter Max Output: ", 0);
    double min = SmartDashboard.getNumber("Shooter Min Output: ", 0);
    double cRPM = SmartDashboard.getNumber("Shooter Close RPM: ", 0);
    double fRPM = SmartDashboard.getNumber("Shooter Flywheel Far RPM: ", 0);

    // if PID coefficients on SmartDashboard have changed, write new values to controller
    if((p != kP)) { shooterPIDController.setP(p); kP = p; }
    // if((i != kI)) { shooterPIDController.setI(i); kI = i; }
    if((d != kD)) { shooterPIDController.setD(d); kD = d; }
    if((iz != kIz)) { shooterPIDController.setIZone(iz); kIz = iz; }
    if((ff != kFF)) { shooterPIDController.setFF(ff); kFF = ff; }
    if((max != kMaxOutput) || (min != kMinOutput)) { 
      shooterPIDController.setOutputRange(min, max); 
      kMinOutput = min; kMaxOutput = max;}
    if((cRPM != closeRPM)) { closeRPM = cRPM; }
    if((fRPM != farRPM)) { farRPM = fRPM; }  

    SmartDashboard.putNumber("Shooter Flywheel Power", shooterFlywheel.get());
    SmartDashboard.putNumber("Shooter Flywheel Velocity", shooterFlywheelEncoder.getVelocity());
    // SmartDashboard.putNumber("Shooter Turret Power", turretPower);
    SmartDashboard.putNumber("Shooter Limelight Error", getLimelightError());
    SmartDashboard.putNumber("Shooter Servo Position", getServoPos());
    // SmartDashboard.putData("Shooter Flywheel PID", (Sendable) shooterPIDController);
  }

  public double getLimelightError() {
    return NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
  }

  public boolean getLockonState() {
    return  -5 < getLimelightError() || getLimelightError() < 5;
  }

  public void setTurretSpeed(double turretPower) {
    turretPower = turretPower * Constants.shooterTurretMotorGain;
    shooterTurret.set(turretPower);
  }

  public void setFlywheelSpeed(double flywheelPower) {    
    shooterFlywheel.set(flywheelPower);
  }

  public boolean getUptoSpeed(double velocity) {
    if (Math.abs(shooterFlywheel.getEncoder().getVelocity() - velocity) < 100)
      return true;
    else
      return false;
  }

  public void setServoPos(double pos) {
    shooterRightServo.set(pos);
    shooterLeftServo.set(1-pos);
  }

  public double getServoPos() {
    return shooterRightServo.get();
  }

  public void setFlywheelVelocityPID(double setpoint) {
    shooterPIDController.setReference(setpoint, ControlType.kVelocity);
  }

  public double getCloseRPM() {
    return closeRPM;
  }

  public double getFarRPM() {
    return farRPM;
  }
}