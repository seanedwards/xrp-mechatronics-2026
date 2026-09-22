// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.XRPArm;

/** Selects one fixed Servo1 position when scheduled by a bumper press. */
public class ArmSetpointCommand extends Command {

  /** Owns the Servo1 hardware targeted by this command. */
  private final XRPArm m_arm;

  /** Stores the normalized position selected by the bumper. */
  private final double m_position;

  /** Creates a one-time arm target selection. */
  public ArmSetpointCommand(XRPArm arm, double position) {

    // Stores the sole Servo1 hardware owner.
    m_arm = arm;

    // Stores the endpoint that this command selects.
    m_position = position;

    // Prevents two arm commands from writing Servo1 together.
    addRequirements(arm);
  }

  /** Sends the selected target when the bumper schedules this command. */
  @Override
  public void initialize() {

    // Leaves Servo1 enabled at the requested target after the command finishes.
    m_arm.setPosition(m_position);
  }

  /** Finishes after selecting the target once. */
  @Override
  public boolean isFinished() {

    // Makes bumper presses set a target rather than requiring the bumper to stay held.
    return true;
  }
}
