# Robot Package Guide

This package contains the primary command-based robot application.

- `Main.java` should remain minimal: it starts the robot class and must not initialize robot hardware.
- `Robot.java` owns mode lifecycle callbacks and runs `CommandScheduler` from `robotPeriodic()`.
- `RobotContainer.java` wires subsystems, controllers, default commands, bindings, and autonomous selection; keep policy here rather than in `Robot`.
- `Constants.java` is the central home for robot-wide immutable configuration values. Group values by subsystem and include units in names where applicable.
- `commands/` and `subsystems/` have more specific local guides.

Every line of production code in this package must have a short comment explaining its purpose. Preserve comments during refactors and update them when behavior changes.
