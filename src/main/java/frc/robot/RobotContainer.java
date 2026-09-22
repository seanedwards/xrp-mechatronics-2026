// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.commands.ArmSetpointCommand;
import frc.robot.commands.TeleopArcadeDriveCommand;
import frc.robot.subsystems.XRPArm;
import frc.robot.subsystems.XRPDrivetrain;

/** Wires the controller, teleop commands, and XRP hardware subsystems together. */
public class RobotContainer {

  /** Reads the Xbox-compatible controller connected at Driver Station port zero. */
  private final CommandXboxController m_driverController =
      new CommandXboxController(Constants.OperatorConstants.kDriverControllerPort);

  /** Owns the XRP drivetrain hardware. */
  private final XRPDrivetrain m_drivetrain = new XRPDrivetrain();

  /** Owns the Servo1 arm hardware. */
  private final XRPArm m_arm = new XRPArm();

  /** Configures the beginner teleop controller layout. */
  public RobotContainer() {

    // Keeps the left stick driving whenever no other drivetrain command is scheduled.
    m_drivetrain.setDefaultCommand(
        new TeleopArcadeDriveCommand(
            m_drivetrain,
            () -> Constants.OperatorConstants.kForwardAxisSign * m_driverController.getLeftY(),
            m_driverController::getLeftX));

    // Connects bumper presses to their one-time arm endpoint commands.
    configureButtonBindings();
  }

  /** Maps Xbox bumpers to the two fixed Servo1 targets. */
  private void configureButtonBindings() {

    // Selects the zero endpoint when the left bumper is pressed.
    m_driverController
        .leftBumper()
        .onTrue(new ArmSetpointCommand(m_arm, Constants.ArmConstants.kLeftBumperPosition));

    // Selects the one endpoint after the left binding when the right bumper is pressed too.
    m_driverController
        .rightBumper()
        .onTrue(new ArmSetpointCommand(m_arm, Constants.ArmConstants.kRightBumperPosition));
  }

  /** Provides no autonomous command because this project teaches teleop only. */
  public Command getAutonomousCommand() {

    // Autonomous is intentionally not part of this teleop lesson.
    return null;
  }
}
