package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.Shooter;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class ShootandAimClose extends ParallelCommandGroup {
  /**
   * Creates a new ShootandAimClose.
   */
  public ShootandAimClose(Shooter shooter, Conveyor conveyor) {
    // Add your commands in the super() call, e.g.
    super(new ShooterAim(shooter), new ShooterCloseAutomatic(shooter, conveyor));
    // super(new FooCommand(), new BarCommand());super();
  }
}
