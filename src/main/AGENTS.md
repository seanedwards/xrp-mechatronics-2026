# Main Source Set Guide

`src/main` is the production robot source set used by GradleRIO.

- `java/` holds the Java application that runs on the robot or simulation.
- `deploy/` holds non-Java files copied for robot deployment.
- Keep runtime behavior in Java rather than hiding it in generated or binary assets.
- Any production-code line added below `java/` must have a brief purpose-focused comment.
