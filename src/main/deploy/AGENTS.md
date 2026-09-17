# Deployment Assets Guide

Files here are copied to the robot deployment directory by GradleRIO. Keep only assets that robot code intentionally loads at runtime.

- Use descriptive filenames and document how code consumes each asset.
- Prefer small, human-readable formats so students can inspect and understand the asset.
- Do not place generated output, credentials, or machine-specific files here.
- When an asset changes runtime behavior, add or update a test where practical and document the relationship in the consuming code.
