package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Shooter;
import frc.robot.Constants;
import frc.robot.subsystems.Base;
import frc.robot.subsystems.Conveyor;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class AutoBackupAndShootClose extends SequentialCommandGroup {
  /**
   * Creates a new AutoBackupAndShootClose.
   */
  public AutoBackupAndShootClose(Base base, Shooter shooter, Conveyor conveyor) {
    // Add your commands in the super() call, e.g.
    addCommands(new DriveStraightByEncoder(base, -10, Constants.autoDriveSpeed),
          new TurretCenter(shooter));
  }
}
