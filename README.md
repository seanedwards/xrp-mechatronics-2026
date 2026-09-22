# XRP introductory teleop

This project is a small command-based WPILib lesson for driving an XRP with an Xbox-compatible controller. It intentionally teaches teleop only; `getAutonomousCommand()` returns `null`.

## Controls

- Connect the controller at **Driver Station USB port 0**.
- **Left stick Y:** forward and reverse. Pushing forward drives the XRP forward.
- **Left stick X:** turn left and right.
- **Left bumper:** set the Servo1 arm target to normalized position `0.0`.
- **Right bumper:** set the Servo1 arm target to normalized position `1.0`.
- If both bumpers create commands together, bindings are evaluated in their listed order, so the right-bumper `1.0` endpoint is selected last.

The stick has a small deadband configured in `Constants.OperatorConstants.kAxisDeadband`. The direct arcade math is `left = clamp(forward + turn)` and `right = clamp(forward - turn)`; it does not apply a fixed turn ratio or rescale combined input.

## Servo1 assumption and tuning

The arm code assumes the XRP header labeled **Servo1** is WPILib PWM channel **4**. It uses WPILib `Servo.set(position)` with normalized targets from `0.0` to `1.0` and leaves the servo enabled so it can hold the selected target. Software clamps any requested arm position to that range.

For this lesson, the required bumper endpoints remain exactly `0.0` and `1.0`. If the arm binds mechanically, stop and disable the robot safely, then report the incompatibility rather than silently changing the endpoint constants. Controller port, deadband, axis sign, Servo1 channel, and endpoints are grouped in `Constants` for easy classroom inspection.

## Manual validation

### ControllerAndDrivetrainBenchValidation

1. Verify the controller appears at Driver Station port 0.
2. Place the XRP on a stable bench with the drivetrain elevated so wheels cannot contact people or objects.
3. Enable teleop and gently test forward, reverse, left spin, right spin, and a forward-right combined motion with the left stick.
4. Release the stick and disable the robot; verify wheel output stops safely.

### Servo1BumperBenchValidation

1. Keep the drivetrain elevated and connect the arm servo specifically to the XRP header labeled **Servo1**.
2. Enable teleop while keeping clear of the arm's full travel.
3. Press the left bumper once and confirm the servo moves to and holds `0.0` after release.
4. Press the right bumper once and confirm the servo moves to and holds `1.0` after release.
5. If either endpoint binds or collides, disable or stop safely and report the mechanical incompatibility; do not change the required endpoints without a follow-up decision.

### StudentCodeWalkthrough

1. Start in `RobotContainer` to see the controller, two subsystems, default drive command, and bumper bindings.
2. Trace `TeleopArcadeDriveCommand` to the direct arcade mixer and `XRPDrivetrain.tankDrive`.
3. Trace `ArmSetpointCommand` to `XRPArm.setPosition`, where Servo1 owns and clamps the requested normalized target.
4. Confirm `getAutonomousCommand()` deliberately returns `null`, keeping autonomous outside this teleop lesson.
