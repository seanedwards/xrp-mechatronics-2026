# Vendor Dependencies Guide

This directory contains WPILib vendor-dependency JSON definitions that declare externally maintained robot libraries.

- Change these files only when intentionally adding, upgrading, or removing a vendor library.
- Preserve vendor-provided URLs, checksums, and version metadata exactly unless the vendor's installation instructions require an update.
- Document the reason for version changes in the commit message or accompanying project documentation.
- Do not hand-edit generated library artifacts or copy vendor binaries into the repository.
- Run `./gradlew test` after dependency changes to verify Gradle resolves and compiles the project.
