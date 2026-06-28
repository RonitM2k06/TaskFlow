# TaskFlow

[![Build Status](https://github.com/RonitM2k06/TaskFlow/actions/workflows/ci.yml/badge.svg)](https://github.com/RonitM2k06/TaskFlow/actions/workflows/ci.yml)
[![License: Apache-2.0](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

**TaskFlow** is a lightweight cross‑language CLI task manager written in **Java 21** (Gradle) and **Python 3**. It stores tasks in a local `tasks.json` file, letting you add, list, complete, delete, and filter tasks by tags.

---

## ✨ Features

- Simple, menu‑driven CLI
- JSON‑based persistence (no external DB)
- Tags & due‑date support
- Two implementations (Java & Python) – great for learning both ecosystems
- Zero external runtime dependencies (Gson for Java, standard library for Python)

---

## 🏗 Architecture (Java version)

```
[CLI] → TaskController → JsonFileManager → tasks.json
```

- **CLI** – `Main.java` handles user interaction.
- **TaskController** – `TaskService` provides CRUD operations.
- **JsonFileManager** – reads/writes the JSON file using Gson.

---

## 🛠 Technology Stack

- **Java 21** (Gradle wrapper) – `com.google.code.gson:gson:2.10.1`
- **Python 3.9+** – pure‑standard‑library implementation
- **GitHub Actions** – CI for both Java build and Python lint check

---

## 📦 Installation

### Java
```powershell
# Clone the repo
git clone https://github.com/RonitM2k06/TaskFlow.git
cd TaskFlow
# Build with the Gradle wrapper
./gradlew.bat build   # Windows
# Run the application
./gradlew.bat run
```

### Python
```powershell
git clone https://github.com/RonitM2k06/TaskFlow.git
cd TaskFlow
python -m venv .venv
.\.venv\Scripts\activate   # Windows PowerShell
python taskflow.py
```

---

## ▶️ Usage (sample session – Java version)
```
Welcome to TaskFlow – your lightweight CLI task manager
--- Menu ---
1) List all tasks
2) Add a new task
3) Mark task as complete
4) Delete a task
5) Filter tasks by tag
0) Exit
Select an option: 2
Title: Write report
Description (optional): Quarterly financial report
Tags (comma separated, optional): work,finance
Due date (YYYY-MM-DD, optional): 2026-07-15
Task added with ID: 7a9c3e1b‑... 
```

---

## 📂 Project Structure
```
TaskFlow/
├─ .gitignore
├─ build.gradle
├─ src/main/java/com/taskflow/…   # Java source
├─ taskflow.py                    # Python implementation
├─ tasks.json (generated at runtime)
└─ docs/
   └─ project_info.txt            # Detailed project description
```

---

## 📜 License

Licensed under the **Apache License 2.0** – see [LICENSE](LICENSE).

---

## 🙌 Contributing

Please see [CONTRIBUTING.md](CONTRIBUTING.md) for guidelines.

---

## 🐞 Reporting Issues

Open an issue using the provided templates or see [SECURITY.md](SECURITY.md) for security disclosures.
