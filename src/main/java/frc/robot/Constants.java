// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/** Stores the fixed configuration values students may need to tune. */
public final class Constants {

  /** Prevents accidental construction of this constants-only class. */
  private Constants() {}

  /** Groups operator-controller settings for the teleop lesson. */
  public static final class OperatorConstants {

    /** Selects the first Driver Station controller port. */
    public static final int kDriverControllerPort = 0;

    /** Ignores tiny stick movements near center. */
    public static final double kAxisDeadband = 0.08;

    /** Converts the Xbox forward stick direction into positive forward robot motion. */
    public static final double kForwardAxisSign = -1.0;

    /** Prevents construction of this settings-only class. */
    private OperatorConstants() {}
  }

  /** Groups Servo1 settings for the fixed-position arm lesson. */
  public static final class ArmConstants {

    /** Selects the XRP PWM header labeled Servo1. */
    public static final int kServo1PwmChannel = 4;

    /** Moves the arm to its required left-bumper endpoint. */
    public static final double kLeftBumperPosition = 0.0;

    /** Moves the arm to its required right-bumper endpoint. */
    public static final double kRightBumperPosition = 1.0;

    /** Prevents construction of this settings-only class. */
    private ArmConstants() {}
  }
}
