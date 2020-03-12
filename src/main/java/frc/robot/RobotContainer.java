package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import frc.robot.commands.AutoDriveAndShoot;
import frc.robot.commands.AutoShootOnly;
import frc.robot.commands.ClimbersManual;
import frc.robot.commands.ClimbersUnlock;
import frc.robot.commands.ClimbersUp;
import frc.robot.commands.CollectorManual;
import frc.robot.commands.ConveyorAutomated;
import frc.robot.commands.DriveManual;
import frc.robot.commands.DriveStraightByEncoder;
import frc.robot.commands.Eject;
import frc.robot.commands.LightsController;
import frc.robot.commands.ShootandAimFar;
import frc.robot.commands.ShootandAimMid;
import frc.robot.commands.ShooterAim;
import frc.robot.commands.TurretRotate;
import frc.robot.commands.ShooterCloseAutomatic;
import frc.robot.commands.ShooterMidAutomatic;
import frc.robot.commands.SpinUpAndAim;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Base;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Collector;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.Lights;

public class RobotContainer {
  // Subsystems
  private final Base      m_base        = new Base();
  private final Shooter   m_shooter     = new Shooter();
  private final Collector m_collector   = new Collector();
  private final Climber   m_climber     = new Climber();
  private final Conveyor  m_conveyor    = new Conveyor();
  private final Lights    m_lights      = new Lights();

  //Auto command setups, per WPI example
  private final SequentialCommandGroup m_autoShootOnly = new AutoShootOnly(m_shooter, m_conveyor);
  private final SequentialCommandGroup m_autoDriveShoot = new AutoDriveAndShoot(m_base, m_shooter, m_conveyor); 
  SendableChooser<SequentialCommandGroup> m_chooser = new SendableChooser<>();

  // CAN IDs
  public static int baseRightFrontCANID   = 40; // Brushless
  public static int baseRightBackCANID    = 10; // Brushless
  public static int baseLeftFrontCANID    = 12; // Brushless
  public static int baseLeftBackCANID     = 41; // Brushless
  public static int collectorRollerCANID  = 50; // Brushless
  public static int conveyorBeltCANID     = 62; // Brushless
  public static int shooterRotateCANID    = 60; // Brushed
  public static int shooterFlywheelCANID  = 31; // Brushless
  public static int climberLeftCANID      = 21; // Brushless
  public static int climberRightCANID     = 13; // Brushless
  public static int panelSpinnerCANID     = 30; // Brushed
  
  // Solenoid Ports
  public static int climberLeftSolPort = 0;
  public static int collectorSolPort = 1;
 
  // PWM Ports
  public static int shooterLeftServoPort = 0;
  public static int shooterRightServoPort = 1;
  public static int lightsPWMPort = 2;

  // DIO Ports
  public static int breakbeam1DIOPort = 9;
  public static int breakbeam2DIOPort = 8;
  public static int breakbeam3DIOPort = 5;
  public static int climberLeftLowLimitSwitch = 6;
  public static int climberRightLowLimitSwitch = 7;

  // Controller Ports
  Joystick mainJS = new Joystick(0);
  Joystick gunnerJS = new Joystick(1);

  public RobotContainer() {
    configureBaseController();
    configureOperatorController();
    configurePassiveCommands();
    configureAutoChooser();
  }

  private void configureOperatorController() {
    m_climber.setDefaultCommand(new ClimbersManual(m_climber, () -> gunnerJS.getRawAxis(1), () -> gunnerJS.getRawAxis(3)));
    new JoystickButton(gunnerJS, 1).whenPressed(new ClimbersUp(m_climber, Constants.climbToLeft));
    new JoystickButton(gunnerJS, 2).whenPressed(new ClimbersUp(m_climber, Constants.climbToMid));
    new JoystickButton(gunnerJS, 3).whenPressed(new ClimbersUp(m_climber, Constants.climbToRight));
    new JoystickButton(gunnerJS, 5).whenHeld(new TurretRotate(m_shooter, Constants.turretLeft));
    new JoystickButton(gunnerJS, 6).whenHeld(new TurretRotate(m_shooter, Constants.turretRight));
    new JoystickButton(gunnerJS, 7).whenHeld(new ShootandAimMid(m_shooter, m_conveyor));
    new JoystickButton(gunnerJS, 8).whenHeld(new ShootandAimFar(m_shooter, m_conveyor));
    new JoystickButton(gunnerJS, 9).toggleWhenPressed(new ClimbersUnlock(m_climber));
  }

  private void configureBaseController() {
      m_base.setDefaultCommand(new DriveManual(m_base, () -> mainJS.getRawAxis(1), () -> mainJS.getRawAxis(3)));
      new JoystickButton(mainJS, 1).whenPressed(new DriveStraightByEncoder(m_base, 1.5, -0.75));
      new JoystickButton(mainJS, 2).whenHeld(new ShooterAim(m_shooter));
      new JoystickButton(mainJS, 3).whenHeld(new ShooterCloseAutomatic(m_shooter, m_conveyor));
      new JoystickButton(mainJS, 4).whenHeld(new Eject(m_conveyor, m_collector));
      new JoystickButton(mainJS, 5).whenHeld(new SpinUpAndAim(m_shooter));
      new JoystickButton(mainJS, 6).whenHeld(new CollectorManual(m_collector));
  }

  private void configurePassiveCommands() {
    m_conveyor.setDefaultCommand(new ConveyorAutomated(m_conveyor));
    m_lights.setDefaultCommand(new LightsController(m_lights, m_conveyor));
  }

  private void configureAutoChooser() {
    m_chooser.setDefaultOption("Drive and shoot", m_autoDriveShoot);
    m_chooser.addOption("Shoot only", m_autoShootOnly);
    Shuffleboard.getTab("Autonomous").add(m_chooser);
  }

  public Command getAutonomousCommand() {
    return m_chooser.getSelected();
  }
}