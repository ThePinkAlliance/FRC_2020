package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.commands.ClimbersManual;
import frc.robot.commands.ClimbersUp;
import frc.robot.commands.CollectorManual;
import frc.robot.commands.ConveyorManual;
import frc.robot.commands.DriveManual;
import frc.robot.commands.FlywheelManual;
import frc.robot.commands.LightsController;
import frc.robot.commands.ShooterAim;
import frc.robot.commands.ShooterFarAutomatic;
import frc.robot.commands.ShooterCloseAutomatic;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Base;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Collector;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.Lights;

public class RobotContainer {
  private final Command   m_autoCommand = null;
  private final Base      m_base        = new Base();
  private final Shooter   m_shooter     = new Shooter();
  private final Collector m_collector   = new Collector();
  private final Climber   m_climber     = new Climber();
  private final Conveyor  m_conveyor    = new Conveyor();
  private final Lights    m_lights      = new Lights();

  public static int baseRightFrontCANID   = 10; // Brushless
  public static int baseRightBackCANID    = 11; // Brushless
  public static int baseLeftFrontCANID    = 12; // Brushless
  public static int baseLeftBackCANID     = 13; // Brushless
  public static int collectorRollerCANID  = 20; // Brushed
  public static int conveyorBeltCANID     = 21; // Brushless
  // public static int conveyorIndexerCANID  = 22; // Brushed
  public static int shooterRotateCANID    = 30; // Brushed
  public static int shooterFlywheelCANID  = 31; // Brushless
  public static int climberLeftCANID      = 40; // Brushless
  public static int climberRightCANID     = 41; // Brushless
  public static int panelSpinnerCANID     = 50; // Brushed
  
  public static int lightsPWMPort         = 9;
  public static int climberLeftSolPort = 99;
  public static int climberRightSolPort = 99;
  public static int climberLeftLowLimitSwitch = 99;
  public static int climberRightLowLimitSwitch = 99;
  public static int collectorSolPort = 99;
  public static int shooterRightServoPort = 99;
  public static int shooterLeftServoPort = 99;

  Joystick mainJS = new Joystick(0);
  Joystick gunnerJS = new Joystick(1);

  public RobotContainer() {
    configureButtonBindings();

    m_base.setDefaultCommand(new DriveManual(m_base, () -> mainJS.getRawAxis(1), () -> mainJS.getRawAxis(5)));
    m_shooter.setDefaultCommand(new FlywheelManual(m_shooter, () -> mainJS.getRawAxis(3)));
    m_lights.setDefaultCommand(new LightsController(m_lights, m_conveyor, m_shooter));
    m_climber.setDefaultCommand(new ClimbersManual(m_climber, () -> gunnerJS.getRawAxis(1), () -> gunnerJS.getRawAxis(5)));
  }

  private void configureButtonBindings() {
    new JoystickButton(mainJS, 4).whenHeld(new CollectorManual(m_collector));
    new JoystickButton(mainJS, 1).whenHeld(new ShooterAim(m_shooter));
    new JoystickButton(mainJS, 2).whenHeld(new ClimbersUp(m_climber));
    new JoystickButton(mainJS, 3).whenHeld(new ShooterCloseAutomatic(m_shooter, m_conveyor));
    new JoystickButton(mainJS, 6).whenHeld(new ShooterFarAutomatic(m_shooter, m_conveyor));
    new JoystickButton(mainJS, 5).whenHeld(new ConveyorManual(m_conveyor));
  }

  public Command getAutonomousCommand() {
    return m_autoCommand;
  }
}