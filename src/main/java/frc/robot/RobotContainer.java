package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.commands.DriveManual;
// import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Base;


public class RobotContainer {
  private final Command m_autoCommand = null;
  private final Base    m_base        = new Base();
  // private final Shooter m_shooter     = new Shooter();

  private double baseRightJoystick = 0;
  private double baseLeftJoystick  = 0;

  public static int baseRightFrontCANID   = 10; // Brushless
  public static int baseRightBackCANID    = 11; // Brushless
  public static int baseLeftFrontCANID    = 12; // Brushless
  public static int baseLeftBackCANID     = 13; // Brushless
  public static int collectorRollerCANID  = 20; // Brushed
  public static int conveyorBeltCANID     = 21; // Brushed
  public static int conveyorIndexerCANID  = 22; // Brushed
  public static int shooterRotateCANID    = 30; // Brushed
  public static int shooterFlywheelCANID  = 31; // Brushless
  // public static int shooterFlywheel2CANID = 32; // Brushless
  public static int climberLeftCANID      = 40; // Brushless
  public static int climberRightCANID     = 41; // Brushless
  public static int panelSpinnerCANID     = 50; // Brushed

  Joystick mainJS = new Joystick(0);

  public RobotContainer() {
    configureButtonBindings();

    m_base.setDefaultCommand(new DriveManual(m_base, () -> baseRightJoystick, () -> baseLeftJoystick));
    // m_shooter.setDefaultCommand(new FlywheelManual(m_shooter, () -> baseJS.getRawAxis(1)));
  }

  private void configureButtonBindings() {
    baseRightJoystick = mainJS.getRawAxis(3);
    baseLeftJoystick  = mainJS.getRawAxis(1);
  }

  public Command getAutonomousCommand() {
    return m_autoCommand;
  }
}