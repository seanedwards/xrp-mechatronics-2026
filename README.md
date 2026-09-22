# Programming for Mechatronics: XRP Command-Based Robot

Welcome! This repository is the starting point for learning how a command-based WPILib robot is organized, controlled, and extended.

You do not need to arrive knowing how to program. You do need curiosity, patience, and a willingness to ask questions. During the first half of the class, we will take a functioning robot apart so we can understand it. During the second half, we will put it back together with our own code.

> **This is a sequence of topics, not a week-by-week schedule.** We will spend the time each topic needs and adjust as we learn.

## What you will learn

By the end of this sequence, you should be able to:

- Navigate a WPILib Java project and use the IDE and GitHub workflow.
- Explain the layers of a command-based robot program.
- Read, start, stop, interrupt, and compose commands.
- Connect triggers and controller buttons to existing commands.
- Explain what subsystems, constants, sensors, and commands are responsible for.
- Use core Java ideas—including classes, inheritance, interfaces, abstraction, and encapsulation—to understand robot code.
- Build and deploy code to the XRP and the larger robot.
- Create an idea, break it into robot behaviors, and work out how to implement it.
- Communicate effectively with the electrical and mechanical teams about hardware constraints and opportunities.

## Our class expectations

- **It is okay if you hate programming.** You are still welcome here, and you can learn something useful from the process.
- **If you choose to do it, ask questions.** Questions are how we discover what the robot and the code are actually doing.
- **Confusion is part of the process.** Being lost, seeing errors, and finding that nothing works are normal parts of engineering. You have to be more stubborn than the machine.
- **Make a serious effort to get computer access.** A computer will make this work much easier. If you only have a tablet, try using GitHub Codespaces.
- **Work safely and communicate.** Keep the robot on a stable, clear surface during bench testing, disable it before making hardware changes, and report anything that binds, collides, or behaves unexpectedly.

## The learning sequence

### 1. Start with a standup and get oriented

We will begin with a short standup: what are we trying to learn, what is working, and where are we stuck?

Then we will:

1. Drive the robot before changing its code.
2. Learn the IDE and the basic project layout.
3. Sign up for GitHub and learn the repository workflow.
4. Check out this repository and read the code.
5. Build and deploy the starter project without making code changes.
6. Set expectations for how we will work together and ask for help.

The first goal is not to write code. It is to establish a working baseline and learn how to inspect, build, deploy, and observe a robot program.

### 2. Understand the robot architecture

We will compare the XRP architecture with the RoboRIO-based architecture used by the larger robot. We will follow the path from the robot lifecycle to `RobotContainer`, commands, subsystems, and hardware.

We will also study triggers and button bindings. At first, we will not change them—we will read them and understand what they do. Once the wiring makes sense, we will bind an existing command to a new trigger.

As the class develops, we can use small existing movement commands—or add deliberately unbound ones—to practice connecting behavior to controls without writing every command from scratch. The important step is to understand the trigger-to-command connection before creating new behavior.

### 3. Learn what is inside a command

We will trace a command through its lifecycle:

- How a command starts and performs its initial setup.
- What happens repeatedly while it is scheduled.
- How it decides that it is finished.
- What happens when it ends normally.
- What happens when it is interrupted or canceled.
- Why a command must safely stop or release the hardware it controls.

Then we will compose commands into larger behaviors:

- **Sequences:** run actions one after another.
- **Either/or choices:** select one behavior based on a condition.
- **Parallel commands:** run multiple behaviors at the same time.
- **Deadline groups:** run several behaviors together while one command determines when the group ends.

### 4. Learn what subsystems do

Subsystems own hardware and expose meaningful operations for commands. We will use them to understand the boundary between robot behavior and robot hardware.

We will examine:

- The constants file and why configuration belongs in one visible place.
- Classes, methods, fields, and other Java constructs used throughout the project.
- Inheritance and interfaces as ways to describe shared behavior.
- Abstraction and encapsulation as ways to keep hardware details in the right layer.
- The drive subsystem and how commands request movement.
- The XRP drivetrain's built-in wheel encoders, which are owned by `XRPDrivetrain`, and how their measurements become useful to commands.

The goal is not to memorize vocabulary. The goal is to be able to open a file and explain what responsibility belongs there and why.

### 5. Capstone and open play

In the second half of the class, we will start putting the robot back together with our own ideas and code. The larger-robot programming, autonomous implementation, and cross-team integration described below are future class activities outside this current XRP starter repository.

We will:

- Program the larger robot when that project is available.
- Study autonomous modes and, when the larger robot project provides them, try autonomous behavior to compare it with teleoperated behavior. The current XRP starter is intentionally teleoperated only, so autonomous is a future or conceptual exercise here.
- Propose an idea, then work out together how to make it real.
- Break an idea into commands, subsystems, inputs, and measurable outcomes.
- Integrate our work with the other mechatronics classes.
- Identify questions and requests for the electrical and mechanical teams.
- Look for problems or opportunities that we can predict from the hardware design.

There is no single correct capstone idea. The important part is learning how to move from an idea to a testable robot behavior while communicating with the people who build and support the hardware.

### Self-study ideas

When you have time to explore, choose a small question and investigate it:

- Trace another command from its trigger to the hardware it controls.
- Tune a constant and observe how the robot's behavior changes.
- Graph or log encoder measurements while the robot moves.
- Sketch or prototype an autonomous routine for the larger robot.
- Read the WPILib documentation for a command group and explain when you would use it.
- Bring an idea from another class or hobby and ask how we could turn it into a safe, testable robot behavior.

## The starter XRP

The current starter program is a small teleoperated example:

- Connect an Xbox-compatible controller to Driver Station USB port **0**.
- Use the **left stick Y** axis to drive forward and reverse.
- Use the **left stick X** axis to turn left and right.
- Press the **left bumper** to request the Servo1 arm position `0.0`.
- Press the **right bumper** to request the Servo1 arm position `1.0`.
- The drivetrain uses direct arcade mixing: `left = clamp(forward + turn)` and `right = clamp(forward - turn)`.

The arm code assumes the XRP header labeled **Servo1** is WPILib PWM channel **4**. Servo positions are normalized from `0.0` to `1.0`, and the code clamps requested positions to that range.

If the arm binds or collides, stop and disable the robot safely. Do not silently change the required endpoints; report the mechanical incompatibility so we can investigate it together.

## A useful way to read this repository

Start in `RobotContainer`. It is the wiring diagram for the software: it creates the controller and subsystems, assigns default commands, and connects triggers to commands.

Then follow these paths:

1. `TeleopArcadeDriveCommand` — turns controller input into a drive request.
2. `XRPDrivetrain` — owns the drivetrain hardware and applies that request.
3. `ArmSetpointCommand` — represents an arm behavior requested by a button.
4. `XRPArm` — owns the servo and applies a safe normalized position.
5. `Constants` — collects ports, input settings, and other configuration.

As you read, ask: **What layer is this file part of? What does it know? What should it not need to know?**

## First bench check

Before experimenting with code or hardware:

1. Verify the controller appears at Driver Station port 0.
2. Place the XRP on a stable bench with its wheels elevated and clear of people and objects.
3. Enable teleop and gently test forward, reverse, left spin, right spin, and a forward-right combined motion.
4. Test each arm bumper while keeping clear of the arm's full travel.
5. Release the controls and disable the robot; verify the wheel output stops safely.

The best result is not simply a robot that moves. It is a robot that you can explain, test, improve, and safely hand to the next person.
