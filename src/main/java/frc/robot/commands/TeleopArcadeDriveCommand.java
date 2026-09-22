// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.XRPDrivetrain;
import java.util.function.DoubleSupplier;

/** Drives the XRP continuously from one controller stick using arcade mixing. */
public class TeleopArcadeDriveCommand extends Command {

  /** Owns the wheel hardware used by this command. */
  private final XRPDrivetrain m_drivetrain;

  /** Reads the requested forward or reverse stick value. */
  private final DoubleSupplier m_forwardSupplier;

  /** Reads the requested turning stick value. */
  private final DoubleSupplier m_turnSupplier;

  /** Creates a continuous arcade-drive behavior for the supplied controls. */
  public TeleopArcadeDriveCommand(
      XRPDrivetrain drivetrain, DoubleSupplier forwardSupplier, DoubleSupplier turnSupplier) {

    // Stores the drivetrain that receives mixed wheel outputs.
    m_drivetrain = drivetrain;

    // Stores the live forward input reader.
    m_forwardSupplier = forwardSupplier;

    // Stores the live turning input reader.
    m_turnSupplier = turnSupplier;

    // Prevents another drivetrain command from running at the same time.
    addRequirements(drivetrain);
  }

  /** Reads the stick and applies one direct arcade-drive update. */
  @Override
  public void execute() {

    // Removes small unintended forward-stick movements once at the command boundary.
    double forward =
        MathUtil.applyDeadband(
            m_forwardSupplier.getAsDouble(), Constants.OperatorConstants.kAxisDeadband);

    // Removes small unintended turn-stick movements once at the command boundary.
    double turn =
        MathUtil.applyDeadband(m_turnSupplier.getAsDouble(), Constants.OperatorConstants.kAxisDeadband);

    // Converts the two stick directions into linear left and right wheel requests.
    double[] wheelSpeeds = ArcadeDriveMixer.mix(forward, turn);

    // Sends the mixed requests to the drivetrain.
    m_drivetrain.tankDrive(wheelSpeeds[0], wheelSpeeds[1]);
  }

  /** Stops the drivetrain whenever this default command is interrupted. */
  @Override
  public void end(boolean interrupted) {

    // Leaves the XRP safe when another drivetrain command takes control.
    m_drivetrain.stop();
  }

  /** Keeps this command active as the drivetrain's default behavior. */
  @Override
  public boolean isFinished() {

    // Lets the scheduler run this command until it is interrupted.
    return false;
  }
}

/** Contains the hardware-free arcade mixing math used by teleop drive. */
final class ArcadeDriveMixer {

  /** Prevents construction because mixing is a static calculation. */
  private ArcadeDriveMixer() {}

  /** Returns clamped left and right wheel outputs for forward and turn inputs. */
  static double[] mix(double forward, double turn) {

    // Adds turn to forward motion for the left wheel.
    double leftSpeed = MathUtil.clamp(forward + turn, -1.0, 1.0);

    // Subtracts turn from forward motion for the right wheel.
    double rightSpeed = MathUtil.clamp(forward - turn, -1.0, 1.0);

    // Returns wheel outputs in left-then-right order.
    return new double[] {leftSpeed, rightSpeed};
  }
}
