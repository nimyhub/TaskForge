# TaskForge

TaskForge is a Java-based task management application designed to help users efficiently track, organize, and prioritize their tasks. Developed as part of an academic project, it emphasizes object-oriented programming principles, clean architecture, and practical utility.

---

## Implemented Features

- Add new tasks
- Edit existing tasks
- Delete Selected tasks
- GUI using JavaFX and fxml
- Export/import tasks using `.dat` and `.txt` files
- Exit confirmation prompt for unsaved work

---

## Currently Working On

- Export/import task data in readable formats (`.json`)

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

- JDK 20 or newer (JavaFX 24 tested)
- JavaFX SDK (https://gluonhq.com/products/javafx/)

---

## Installation

1. Clone the repository:
    ```bash
    git clone https://github.com/nimyhub/TaskForge.git
    cd TaskForge
    ```

2. Download and install the JavaFX SDK and set it up in your IDE or build tool.

3. Open the project in your Java IDE (Eclipse, IntelliJ, etc.)

4. Make sure to add JavaFX libraries to your module path or VM options. For example, add to VM arguments:

    ```
    --module-path /path/to/javafx-sdk-24.0.2/lib --add-modules javafx.controls,javafx.fxml
    ```

5. Compile and run the `RunApp` class.

---

## Author

Niko  
Information Engineering Student  
GitHub: [https://github.com/nimyhub](https://github.com/nimyhub)

---

## License

This project is for educational use. License information coming soon.

