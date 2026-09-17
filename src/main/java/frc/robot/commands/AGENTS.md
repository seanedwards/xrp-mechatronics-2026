# Commands Guide

Commands describe robot behaviors and coordinate subsystem use through WPILib's scheduler.

- Declare every hardware subsystem used by a command with `addRequirements` or an equivalent command composition API.
- Keep `initialize`, `execute`, `end`, and `isFinished` behavior explicit and safe. Commands that can move hardware must stop it when ending or being interrupted unless a replacement command deliberately owns it.
- Ensure autonomous commands have a clear completion condition or timeout.
- Prefer composable WPILib commands when they make the behavior easier for students to read.
- Every production-code line must include a concise comment that states its purpose rather than narrating syntax.
