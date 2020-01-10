package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.DriveManual;
import frc.robot.subsystems.Base;
// import frc.robot.subsystems.Shooter;


public class RobotContainer {
  // Define Subsystems abd Commands
  private final Command m_autoCommand = null;
  private final Base m_base = new Base();
  // private final Shooter m_shooter = new Shooter();

  // Define Controls
  private double baseRightJoystick = 0;
  private double baseLeftJoystick = 0;

  // Define Motor Ports
  public static int baseRightFrontCANID = 0;
  public static int baseLeftFrontCANID = 1;
  public static int baseRightBackCANID = 2;
  public static int baseLeftBackCANID = 3;
  public static int shooterFlywheelCANID = 10;

  // Define Joysticks
  Joystick baseJS = new Joystick(0);

  // Constructor
  public RobotContainer() {
    // Setup Controllers
    configureButtonBindings();

    // Set Default Commands
    m_base.setDefaultCommand(new DriveManual(m_base, () -> baseRightJoystick, () -> baseLeftJoystick));
    // m_shooter.setDefaultCommand(new FlywheelManual(m_shooter, () -> baseJS.getRawAxis(1)));
  }

  // Setup Controllers
  private void configureButtonBindings() {
    // Define Axes
    baseRightJoystick = baseJS.getRawAxis(3);
    baseLeftJoystick = baseJS.getRawAxis(1);
  }

  // Use this to pass the autonomous command to the main {@link Robot} class.
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }
}
