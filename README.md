# TaskForge

TaskForge is a Java-based task management application designed to help users efficiently track, organize, and prioritize their tasks. Developed as part of an academic project, it emphasizes object-oriented programming principles, clean architecture, and practical utility.

---

## Implemented Features

- Add new tasks
- Edit existing tasks
- Delete Selected tasks
- GUI using JavaFX and fxml
- Export/import tasks using `.dat`,`.json` and `.txt` files
- Exit confirmation prompt for unsaved work

---

## Currently Working On

- Sorting and filtering
- Improving UI

---

## Planned Features

- Task categories (e.g. School, Work, Personal)
- Deadlines and due dates
- Task completion tracking
- Sorting and filtering
- Cross-device or cloud syncing

---

## Technologies Used

- Java (Object-Oriented Programming)
- Eclipse IDE (or IntelliJ)
- Git and GitHub for version control
- `.dat` file storage using Java built-in serialization (`ObjectOutputStream`, `ObjectInputStream`)
- `.txt` custom file storage.
- UI structure created with FXML for declarative layout.

---

## Requirements

- **JDK 20** or newer (tested with **JavaFX 24**)
- **JavaFX SDK** – [Download from Gluon](https://gluonhq.com/products/javafx/)
- **Gson** – [Google's JSON library](https://github.com/google/gson) (used for saving/loading `.json` task files)

---

## Installation

1. **Clone the repository:**
    ```bash
    git clone https://github.com/nimyhub/TaskForge.git
    cd TaskForge
    ```

2. **Install JavaFX:**

    - Download the JavaFX SDK from [gluonhq.com](https://gluonhq.com/products/javafx/)
    - Unzip it and remember the path (e.g., `/path/to/javafx-sdk-24.0.2`)

3. **Set up dependencies:**

    - In your IDE (Eclipse, IntelliJ, etc.):
        - Add JavaFX to your **module path**
        - Add the `gson-<version>.jar` file to your **build path**  
          (Download from [Maven Central](https://repo1.maven.org/maven2/com/google/code/gson/gson/))

    - When running the app, add this to your VM options:
        ```
        --module-path /path/to/javafx-sdk-24.0.2/lib --add-modules javafx.controls,javafx.fxml
        ```

4. **Open the project** in your IDE and let it build.

5. **Run the application** by launching the `RunApp` class.

---

## Author

Niko  
Information Engineering Student  
GitHub: [https://github.com/nimyhub](https://github.com/nimyhub)
Gson – Google's JSON library (used for saving/loading .json task files)
---

## License

This project is licensed under the [MIT License](LICENSE).


