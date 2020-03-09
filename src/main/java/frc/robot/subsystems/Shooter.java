package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.EncoderType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class Shooter extends SubsystemBase {
  public CANSparkMax shooterTurret = new CANSparkMax(RobotContainer.shooterRotateCANID, MotorType.kBrushed);
  public CANSparkMax shooterFlywheel = new CANSparkMax(RobotContainer.shooterFlywheelCANID, MotorType.kBrushless);

  public double shooterLimelight = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);

  public CANEncoder shooterFlywheelEncoder = new CANEncoder(shooterFlywheel);
  public CANEncoder shooterTurretEncoder = new CANEncoder(shooterTurret, EncoderType.kQuadrature, 15);

  public Servo shooterRightServo = new Servo(RobotContainer.shooterRightServoPort);
  public Servo shooterLeftServo = new Servo(RobotContainer.shooterLeftServoPort);

  private CANPIDController shooterPIDController;
  public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput, closeRPM, midRPM, farRevRPM, farMixRPM, farForRPM, turretPower;

  public Shooter() {
    shooterFlywheel.setInverted(true);
    shooterPIDController = shooterFlywheel.getPIDController();
   
    kP = 0.0004;
    kI = 6e-7;
    kD = 0.00015;
    kIz = 500;
    kFF = 0.00015;
    kMaxOutput = 1;
    kMinOutput = 0;
    closeRPM = 4000; 
    midRPM = 4000;
    farRevRPM = 4400; // Backwards
    farForRPM = 4650; // Forwards
    farMixRPM = 4550; // Mix

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
    SmartDashboard.putNumber("Shooter Flywheel Far For RPM: ", farForRPM);
    SmartDashboard.putNumber("Shooter Flywheel Far Rev RPM", farRevRPM);

  }

  @Override
  public void periodic() {
    // read PID coefficients from SmartDashboard
    // double p = SmartDashboard.getNumber("Shooter P Gain: ", 0);
    // double i = SmartDashboard.getNumber("Shooter I Gain: ", 0);
    // double d = SmartDashboard.getNumber("Shooter D Gain: ", 0);
    // double iz = SmartDashboard.getNumber("Shooter Iz Gain: ", 0);
    // double ff = SmartDashboard.getNumber("Shooter FF Gain: ", 0);
    // double max = SmartDashboard.getNumber("Shooter Max Output: ", 0);
    // double min = SmartDashboard.getNumber("Shooter Min Output: ", 0);
    // double cRPM = SmartDashboard.getNumber("Shooter Flywheel Close RPM: ", 0);
    // double fRPM = SmartDashboard.getNumber("Shooter Flywheel Far RPM: ", 0);

    // // if PID coefficients on SmartDashboard have changed, write new values to controller
    // if((p != kP)) { shooterPIDController.setP(p); kP = p; }
    // // if((i != kI)) { shooterPIDController.setI(i); kI = i; }
    // if((d != kD)) { shooterPIDController.setD(d); kD = d; }
    // if((iz != kIz)) { shooterPIDController.setIZone(iz); kIz = iz; }
    // if((ff != kFF)) { shooterPIDController.setFF(ff); kFF = ff; }
    // if((max != kMaxOutput) || (min != kMinOutput)) { 
    //   shooterPIDController.setOutputRange(min, max); 
    //   kMinOutput = min; kMaxOutput = max;}
    // if((cRPM != closeRPM)) { closeRPM = cRPM; }
    // if((fRPM != farRPM)) { farRPM = fRPM; }  

    SmartDashboard.putNumber("Shooter Flywheel Power", shooterFlywheel.get());
    SmartDashboard.putNumber("Shooter Flywheel Velocity", shooterFlywheelEncoder.getVelocity());
    SmartDashboard.putNumber("Shooter Turret Power", turretPower);
    SmartDashboard.putNumber("Shooter Turret Position", shooterTurretEncoder.getPosition());
    SmartDashboard.putNumber("Shooter Limelight X Error", getLimelightXError());
    SmartDashboard.putNumber("Shooter Limelight Y Error", getLimelightYError());
    SmartDashboard.putNumber("Shooter Servo Position", getServoPos());
  }

  public double getLimelightXError() {
    return NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
  }

  public double getLimelightYError() {
    return NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
  }

  public boolean getLockonState() {
    return  -5 < getLimelightXError() || getLimelightXError() < 5;
  }

  public void setTurretSpeed(double turret_power) {
    turretPower = turret_power * Constants.shooterTurretMotorGain;
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

  public double getMidRPM() {
    return midRPM;
  }

  public double getFarForRPM() {
    return farForRPM;
  }

  public double getFarRevRPM() {
    return farRevRPM;
  }

  public double getMixFarRPM() {
    return farMixRPM;
  }

  public CANPIDController getShooterPIDController() {
    return shooterPIDController;
  }

  public void centerTurret() {
    if(shooterTurretEncoder.getPosition() < 0)
      shooterTurret.set(0.5);
    else if (shooterTurretEncoder.getPosition() > 0)
      shooterTurret.set(-0.5);  
  }
}