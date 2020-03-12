# Pink Robotics 2020

## Controls

### Autonomous

| Command             | Action                                                       |
| ------------------- | ------------------------------------------------------------ |
| AutoDriveAndShoot() | Aims and Shoots 3 Balls Before Driving 40 Encoder Counts off the Line |
| AutoShootOnly()     | Aims and Shoots 3 Balls                                      |

```java
private void configureAutoChooser() {
  m_chooser.setDefaultOption("Drive and shoot", m_autoDriveShoot);
  m_chooser.addOption("Shoot only", m_autoShootOnly);
  Shuffleboard.getTab("Autonomous").add(m_chooser);
}
```

### Teleop

For the teleoperated control period we make use of two [Logitech F310](https://www.logitechg.com/en-us/products/gamepads/f310-gamepad.html#940-000110 "Logitech Webpage") controllers in Ports 0 and 1 of our driver station

![Logitech F310 Controller](https://www.logitechg.com/content/dam/gaming/en/products/f310/f310-gallery-1.png)

```java
// Controller Definitions  
Joystick mainJS   = new Joystick(0);
Joystick gunnerJS = new Joystick(1);
```



#### Base

| Control                     | Action                                |
| --------------------------- | ------------------------------------- |
| Left Joystick (Raw Axis 1)  | Left Side Base Tank Drive             |
| Right Joystick (Raw Axis 3) | Right Side Base Tank Drive            |
| X (Button 1)                | Close Shot Automated Backup           |
| A (Button 2)                | Limelight Aim                         |
| B (Button 3)                | Close Shot Automated Shoot            |
| Y (Button 4)                | Eject Magazine and Collector          |
| Left Bumper (Button 5)      | Automated Aim and Spin Up (0.5 Power) |
| Right Bumper (Button 6)     | Automated Collect                     |

```java
private void configureBaseController() {
  m_base.setDefaultCommand(new DriveManual(m_base, () -> mainJS.getRawAxis(1), () -> mainJS.getRawAxis(3)));
  new JoystickButton(mainJS, 1).whenPressed(new DriveStraightByEncoder(m_base, 1.5, -0.75));
  new JoystickButton(mainJS, 2).whenHeld(new ShooterAim(m_shooter));
  new JoystickButton(mainJS, 3).whenHeld(new ShooterCloseAutomatic(m_shooter, m_conveyor));
  new JoystickButton(mainJS, 4).whenHeld(new Eject(m_conveyor, m_collector));
  new JoystickButton(mainJS, 5).whenHeld(new SpinUpAndAim(m_shooter));
  new JoystickButton(mainJS, 6).whenHeld(new CollectorManual(m_collector));
}
```

#### Operator

| Control                     | Action                               |
| --------------------------- | ------------------------------------ |
| Left Joystick (Raw Axis 1)  | Left Side Climber                    |
| Right Joystick (Raw Axis 3) | Right Side Climber                   |
| X (Button 1)                | Climb Preset Left                    |
| A (Button 2)                | Climb Preset Mid                     |
| B (Button 3)                | Climb Preset Right                   |
| Left Bumper (Button 5)      | Rotate Turret Counterclockwise       |
| Right Bumper (Button 6)     | Rotate Turret Clockwise              |
| Left Trigger (Button 7)     | Mid Shot Automated Aim and Shoot     |
| Right Trigger (Button 8)    | Far Shot Automated Aim and Shoot     |
| Back (Button 9)             | Toggle Climbers Lock/Unlock Solenoid |

```java
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
```

#### Passive

| Command             | Action                                                 |
| ------------------- | ------------------------------------------------------ |
| ConveyorAutomated() | Automatically Runs Conveyor Based on Breakbeam Sensors |
| LightsController()  | Sets Lights to Varied Patterns Based on Robot State    |

```java
private void configurePassiveCommands() {
  m_conveyor.setDefaultCommand(new ConveyorAutomated(m_conveyor));
  m_lights.setDefaultCommand(new LightsController(m_lights, m_conveyor));
}
```

## Robot Map

### Motors

| Motor                  | CAN ID |
| ---------------------- | ------ |
| Right Front Base       | 40     |
| Right Back Base        | 10     |
| Left Front Base        | 12     |
| Left Back Base         | 41     |
| Collector Roller       | 50     |
| Magazine Conveyor Belt | 62     |
| Turret                 | 60     |
| Flywheel               | 31     |
| Left Climber           | 21     |
| Right Climber          | 13     |
| Color Wheel Spinner    | 30     |

```java
// Motors
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
```

### Solenoids

| Solenoid                 | Port |
| ------------------------ | ---- |
| Climber Lock/Unlock      | 0    |
| Collector Extend/Retract | 1    |

```java
// Solenoids
public static int climberLeftSolPort = 0;
public static int collectorSolPort   = 1;
```

### PWM Devices

| PWM Device        | Port |
| ----------------- | ---- |
| Left Hood         | 0    |
| Right Hood        | 1    |
| Lights Controller | 2    |

```java
// PWM Devices
public static int shooterLeftServoPort  = 0;
public static int shooterRightServoPort = 1;
public static int lightsPWMPort         = 2;
```

### Digital Input Devices

| DIO Devices                | Port |
| -------------------------- | ---- |
| Magazine Breakbeam 1       | 9    |
| Magazine Breakbeam 2       | 8    |
| Shoot Breakbeam            | 5    |
| Left Climber Limit Switch  | 6    |
| Right Climber Limit Switch | 7    |

```java
// DIO Devices
public static int breakbeam1DIOPort          = 9;
public static int breakbeam2DIOPort          = 8;
public static int breakbeam3DIOPort          = 5;
public static int climberLeftLowLimitSwitch  = 6;
public static int climberRightLowLimitSwitch = 7;
```
