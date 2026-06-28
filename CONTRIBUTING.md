# Contributing to TaskFlow

Thank you for considering contributing to **TaskFlow**! We welcome contributions from the community. Below are some guidelines to help you get started.

## How to Contribute

1. **Fork the repository** and clone your fork locally.
2. **Create a new branch** for your feature or bug‑fix:
   ```bash
   git checkout -b my-feature
   ```
3. **Make your changes** following the existing code style (Java: Google Java Style; Python: PEP8).
4. **Run the tests / sanity checks**:
   - Java: `./gradlew test` (there are no unit tests yet, but the build must succeed).
   - Python: `python -m py_compile taskflow.py` (ensures syntax is valid).
5. **Commit your changes** with a clear commit message.
6. **Push to your fork** and open a Pull Request against the `main` branch.

## Pull Request Checklist

- [ ] Code builds without errors.
- [ ] Code follows the project’s coding conventions.
- [ ] Documentation (README, Javadoc, docstrings) is updated if necessary.
- [ ] No new warnings or lint errors introduced.
- [ ] The PR description clearly explains the purpose of the change.

## Code Style

- **Java** – Use `google-java-format` style. Avoid long lines; wrap at 100 characters.
- **Python** – Follow PEP8. Use 4‑space indentation, no trailing whitespace.

## Reporting Issues

If you encounter a bug or have a suggestion, please open an issue using the templates provided.

---

*Happy coding!*
