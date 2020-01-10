package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.Base;
import frc.robot.subsystems.Shooter;


public class Robot extends TimedRobot {
  // Declare Subsystems and Commands
  private RobotContainer m_robotContainer;
  private Command m_autonomousCommand;
  public Shooter m_shooter;
  public Base m_base;

  // Initialization
  @Override
  public void robotInit() {
    // Define Subsystems and Commands
    m_robotContainer = new RobotContainer();
    m_shooter = new Shooter();
    m_base = new Base();
  }

  // Called Every Packet Regardless of Robot State
  @Override
  public void robotPeriodic() {
    // Run Scheduler
    CommandScheduler.getInstance().run();
  }

  // Run Once @ Disabled
  @Override
  public void disabledInit() {
  }

  // Run While Disabled
  @Override
  public void disabledPeriodic() {
  }

  // Runs Once @ Auto
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  // Runs While in Auto
  @Override
  public void autonomousPeriodic() {
  }

  // Run Once @ Teleop
  @Override
  public void teleopInit() {
    // Stop Auto @ Teleop Start
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  // Runs While in Teleop
  @Override
  public void teleopPeriodic() {
  }

  // Runs Once @ Test
  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  // Runs While in Test
  @Override
  public void testPeriodic() {
  }
}
