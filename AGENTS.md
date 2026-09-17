# Project Guide

## Purpose
This repository is an educational WPILib Java project for the STEM Coliseum **Programming for Mechatronics** class. Students will take the project apart to learn how an XRP command-based robot is structured and controlled. Prefer clear, conventional, easy-to-trace designs over clever abstractions.

## Code and documentation standard
- Keep production code formatted with the project's Java formatter and use standard WPILib command-based patterns.
- **Every line of production code must include a brief, direct comment explaining its purpose.** Explain why the line exists or what role it serves; do not restate Java syntax.
- Keep comments accurate when changing code. A misleading comment is worse than no comment.
- Use descriptive names and small, single-purpose methods so students can follow execution from `Main` to `Robot`, `RobotContainer`, commands, and subsystems.
- Preserve the distinction between robot lifecycle code, wiring/configuration, commands, and hardware subsystems.
- Do not modify generated outputs or local caches (`build/`, `bin/`, `.gradle/`).

## Validation
Run `./gradlew test` after Java or Gradle changes. Add or update tests under `src/test` for behavior that can be exercised without robot hardware.

## Directory map
- `src/` — robot code and deployable assets; see its local guide.
- `vendordeps/` — pinned third-party WPILib dependency metadata; see its local guide.
- `gradle/` and Gradle root files — build wrapper and WPILib build configuration.
- `.vscode/` and `.wpilib/` — checked-in development environment preferences.
