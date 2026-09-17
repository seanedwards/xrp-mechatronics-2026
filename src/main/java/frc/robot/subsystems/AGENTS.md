# Subsystems Guide

Subsystems own robot hardware and provide focused operations for commands.

- Create and configure motors, sensors, encoders, and other hardware here; do not construct the same hardware in commands.
- Use named constants for ports, calibration values, and conversions. Include physical units in names and comments.
- Expose meaningful operations such as drive, reset, or measurement access instead of leaking hardware objects.
- Make safe behavior explicit, including how moving outputs are stopped when a command ends.
- Keep `periodic` and `simulationPeriodic` focused on recurring subsystem work.
- Every production-code line must include a concise comment explaining its purpose, not its Java syntax.
