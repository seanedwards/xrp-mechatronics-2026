// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

/** Owns the Servo1 arm hardware and accepts safe normalized targets. */
public class XRPArm extends SubsystemBase {

  /** Sends position targets to the XRP header labeled Servo1. */
  private final Servo m_servo = new Servo(Constants.ArmConstants.kServo1PwmChannel);

  /** Sets and holds a normalized arm target between zero and one. */
  public void setPosition(double position) {

    // Protects Servo1 from invalid software position requests.
    double safePosition = MathUtil.clamp(position, 0.0, 1.0);

    // Keeps the servo enabled so it holds the selected target.
    m_servo.set(safePosition);
  }
}
