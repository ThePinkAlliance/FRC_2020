package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.commands.ClimbersUp;
import frc.robot.commands.CollectorManual;
import frc.robot.commands.DriveManual;
import frc.robot.commands.FlywheelManual;
import frc.robot.commands.LightsController;
import frc.robot.commands.ShooterAim;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Base;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Collector;
import frc.robot.subsystems.Lights;

public class RobotContainer {
  private final Command   m_autoCommand = null;
  private final Base      m_base        = new Base();
  private final Shooter   m_shooter     = new Shooter();
  private final Collector m_collector   = new Collector();
  private final Climber   m_climber     = new Climber();
  private final Lights    m_lights      = new Lights();

  public static int baseRightFrontCANID   = 10; // Brushless
  public static int baseRightBackCANID    = 11; // Brushless
  public static int baseLeftFrontCANID    = 12; // Brushless
  public static int baseLeftBackCANID     = 13; // Brushless
  public static int collectorRollerCANID  = 20; // Brushed
  public static int conveyorBeltCANID     = 21; // Brushed
  public static int conveyorIndexerCANID  = 22; // Brushed
  public static int shooterRotateCANID    = 30; // Brushed
  public static int shooterFlywheelCANID  = 31; // Brushless
  public static int climberLeftCANID      = 40; // Brushless
  public static int climberRightCANID     = 41; // Brushless
  public static int panelSpinnerCANID     = 50; // Brushed
  public static int lightsPWMPort         = 9;
  public static int climberSolPort        = 0;

  Joystick mainJS = new Joystick(0);

  public RobotContainer() {
    configureButtonBindings();

    m_base.setDefaultCommand(new DriveManual(m_base, () -> mainJS.getRawAxis(1), () -> mainJS.getRawAxis(5)));
    m_shooter.setDefaultCommand(new FlywheelManual(m_shooter, () -> mainJS.getRawAxis(3)));
    m_collector.setDefaultCommand(new CollectorManual(m_collector, () -> mainJS.getRawAxis(2))); 
    m_lights.setDefaultCommand(new LightsController(m_lights, m_collector, m_shooter));
  }

  private void configureButtonBindings() {
    new JoystickButton(mainJS, 1).whenHeld(new ShooterAim(m_shooter));
    new JoystickButton(mainJS, 2).whenHeld(new ClimbersUp(m_climber));
  }

  public Command getAutonomousCommand() {
    return m_autoCommand;
  }
}