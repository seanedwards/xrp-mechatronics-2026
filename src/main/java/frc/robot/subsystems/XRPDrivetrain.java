// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.xrp.XRPMotor;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/** Owns the XRP drivetrain motors and their built-in wheel encoders. */
public class XRPDrivetrain extends SubsystemBase {

  /** Describes the XRP gearbox reduction from motor to wheel. */
  private static final double kGearRatio =
      (30.0 / 14.0) * (28.0 / 16.0) * (36.0 / 9.0) * (26.0 / 8.0);

  /** Counts encoder edges for one motor-shaft revolution. */
  private static final double kCountsPerMotorShaftRev = 12.0;

  /** Converts wheel revolutions into raw encoder counts. */
  private static final double kCountsPerRevolution = kCountsPerMotorShaftRev * kGearRatio;

  /** Records the XRP wheel diameter in inches. */
  private static final double kWheelDiameterInch = 2.3622;

  /** Drives the XRP left wheel on its fixed motor channel. */
  private final XRPMotor m_leftMotor = new XRPMotor(0);

  /** Drives the XRP right wheel on its fixed motor channel. */
  private final XRPMotor m_rightMotor = new XRPMotor(1);

  /** Measures left-wheel movement through the XRP's fixed encoder pins. */
  private final Encoder m_leftEncoder = new Encoder(4, 5);

  /** Measures right-wheel movement through the XRP's fixed encoder pins. */
  private final Encoder m_rightEncoder = new Encoder(6, 7);

  /** Sends paired wheel outputs to the two drivetrain motors. */
  private final DifferentialDrive m_diffDrive =
      new DifferentialDrive(m_leftMotor::set, m_rightMotor::set);

  /** Configures XRP encoder distances and motor directions. */
  public XRPDrivetrain() {

    // Converts left encoder pulses into inches for future lessons.
    m_leftEncoder.setDistancePerPulse((Math.PI * kWheelDiameterInch) / kCountsPerRevolution);

    // Converts right encoder pulses into inches for future lessons.
    m_rightEncoder.setDistancePerPulse((Math.PI * kWheelDiameterInch) / kCountsPerRevolution);

    // Starts distance measurements from a known zero point.
    resetEncoders();

    // Matches right-wheel output to the mechanically flipped right motor.
    m_rightMotor.setInverted(true);

    // Leaves the teleop command as the single place that applies stick deadband.
    m_diffDrive.setDeadband(0.0);
  }

  /** Applies direct, linear output requests to the left and right wheels. */
  public void tankDrive(double leftSpeed, double rightSpeed) {

    // Preserves the command's analog wheel outputs without squaring them.
    m_diffDrive.tankDrive(leftSpeed, rightSpeed, false);
  }

  /** Stops both drivetrain wheels through the same linear drive path. */
  public void stop() {

    // Commands zero output to each wheel.
    tankDrive(0.0, 0.0);
  }

  /** Resets both drivetrain distance measurements. */
  public void resetEncoders() {

    // Clears the left distance measurement.
    m_leftEncoder.reset();

    // Clears the right distance measurement.
    m_rightEncoder.reset();
  }

  /** Returns the measured left-wheel distance in inches. */
  public double getLeftDistanceInch() {

    // Provides the converted left encoder distance.
    return m_leftEncoder.getDistance();
  }

  /** Returns the measured right-wheel distance in inches. */
  public double getRightDistanceInch() {

    // Provides the converted right encoder distance.
    return m_rightEncoder.getDistance();
  }
}
