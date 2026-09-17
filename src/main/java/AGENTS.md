# Java Source Guide

This is the production Java source root. The code follows WPILib's command-based architecture.

## Navigation order for students
1. `frc.robot.Main` starts the robot.
2. `frc.robot.Robot` owns lifecycle callbacks and runs the command scheduler.
3. `frc.robot.RobotContainer` creates subsystems, input bindings, default commands, and autonomous selection.
4. `frc.robot.commands` defines units of robot behavior.
5. `frc.robot.subsystems` owns and controls hardware.

## Rules
- Keep the package hierarchy aligned with this architecture.
- Use WPILib APIs and command requirements correctly; commands that use hardware must declare the affected subsystem.
- Every production-code line must carry a concise comment explaining its purpose, not Java syntax.
- Favor readable control flow and explicit units in names, especially for speeds, distances, timings, and hardware channels.
