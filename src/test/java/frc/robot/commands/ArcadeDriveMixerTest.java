package frc.robot.commands;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

/** Illustrates hardware-free tests for the direct arcade-drive wheel math. */
class ArcadeDriveMixerTest {
  /** Verifies full forward and reverse preserve equal wheel outputs. */
  @Test
  void mixesFullForwardAndReverse() {
    assertArrayEquals(new double[] {1.0, 1.0}, ArcadeDriveMixer.mix(1.0, 0.0));
    assertArrayEquals(new double[] {-1.0, -1.0}, ArcadeDriveMixer.mix(-1.0, 0.0));
  }

  /** Verifies full turn produces opposite wheel directions. */
  @Test
  void mixesFullLeftAndRightSpins() {
    assertArrayEquals(new double[] {1.0, -1.0}, ArcadeDriveMixer.mix(0.0, 1.0));
    assertArrayEquals(new double[] {-1.0, 1.0}, ArcadeDriveMixer.mix(0.0, -1.0));
  }

  /** Verifies a combined request keeps its direct analog wheel value. */
  @Test
  void mixesForwardRightInput() {
    assertArrayEquals(new double[] {1.0, 0.5}, ArcadeDriveMixer.mix(1.0, 0.5));
  }

  /** Verifies wheel requests are clamped independently to the valid range. */
  @Test
  void clampsWheelOutputs() {
    assertArrayEquals(new double[] {1.0, 0.0}, ArcadeDriveMixer.mix(1.0, 1.0));
  }

  /** Verifies centered input leaves both wheels stopped. */
  @Test
  void mixesNeutralInput() {
    assertArrayEquals(new double[] {0.0, 0.0}, ArcadeDriveMixer.mix(0.0, 0.0));
  }
}
