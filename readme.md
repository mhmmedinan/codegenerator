
# CodeGenerator CLI

CodeGenerator CLI is a command-line tool for generating Spring Boot projects with different architectural patterns, such as Clean Architecture and N-Layer Architecture. The tool can also generate CRUD operations for entities within these projects.

## Features
- **Project Creation:** Supports generating projects with multiple architectural patterns (Clean Architecture, N-Layer Architecture).
- **CRUD Generation:** Automatically generates CRUD operations for entities in the project.
- **Architecture Detection:** Determines the architecture type of an existing project based on a `metadata.json` file.
- **Interactive Selection:** Lists available projects and entities if they are not specified in the command.

## Installation
1. **Clone the repository:**
   ```bash
   git clone https://github.com/mhmmedinan/codegenerator.git
   ```
2. **Navigate to the project directory:**
   ```bash
   cd codegenerator
   ```
3. **Build the project:**
   ```bash
   mvn clean install
   ```

4. **Add the JAR file to the system PATH**
   - Move the `codegenerator.jar` file to a directory of your choice (e.g., `C:\codegenerator`).
   - Add this directory to your system PATH to access the `codegenerator` command globally:
     - **Windows**:
       1. Go to `System Properties` > `Advanced` > `Environment Variables`.
       2. Under `System variables`, find `Path`, and click `Edit`.
       3. Add the directory is located (e.g., `C:\codegenerator`).
       4. Click `OK` to save the changes.
     - **Linux/macOS**:
       - Open the terminal and add the following line to your `~/.bashrc`, `~/.bash_profile`, or `~/.zshrc` file:
         ```bash
         export PATH=$PATH:/path/to/codegenerator
         ```
       - Run `source ~/.bashrc` or `source ~/.bash_profile` to apply the changes.

5. **Create a `codegen.bat` or `codegen.sh` file for convenience**
   - **Windows (codegen.bat)**:
     ```batch
     @echo off
     java -jar "C:\codegenerator\target\codegenerator-0.0.1-SNAPSHOT.jar"  %*
     ```
   - **Linux/macOS (codegen.sh)**:
     ```bash
     #!/bin/bash
     java -jar /path/to/codegenerator/codegenerator.jar "$@"
     ```
   - Make the `codegen.sh` script executable:
     ```bash
     chmod +x /path/to/codegenerator/codegen.sh
     ```

## Usage

### Creating a New Project

To create a new project with the `CodeGenerator` CLI, use the following command:

```bash
codegen new <projectName> -a <architecture>
```

- `projectName`: The name of the project.
- `-a` or `--architecture`: The architecture type. Possible values are:
  - `cleanarch` for Clean Architecture
  - `nlayerarch` for N-Layer Architecture

If the architecture option is not provided, the default is `cleanarch`.

Example:

```bash
codegen new MyProject -a cleanarch
```

This command will generate a new project named `MyProject` using Clean Architecture.

### Generating CRUD Operations

To generate CRUD operations for an existing project, navigate to the root directory of the project and use the following command:

This command will list all available entities in the project, and you can select the entity for which you want to generate CRUD operations.

If you want to specify the entity directly, use:

```bash
codegen generate-crud 
```

1. **Project Selection:** If the project is not specified, the CLI will prompt you to select from the list of available projects.
2. **Entity Selection:** If the entity is not specified, the CLI will list all entities in the selected project, and you can choose one.
3. **Architecture Detection:** The tool detects the architecture based on the `metadata.json` file located in the project's root directory.

### Available Commands

- **`codegen new <projectName>`**: Creates a new project.
- **`codegen generate-crud`**: Generates CRUD operations for an existing project.
- **`codegen help`**: Displays help information.

## Architecture Overview

### Clean Architecture

The Clean Architecture style organizes code into layers that separate concerns, including:

1. **Application Layer**: Defines use cases and application services.
2. **Persistence Layer**: Handles database operations and data access.
3. **Infrastructure Layer**: Manages external services and cross-cutting concerns, such as logging or messaging.
4. **Domain Layer**: Contains the business logic and domain entities.
5. **WebAPI Layer**: Exposes RESTful endpoints for interaction with the application.

### N-Layer Architecture

The N-Layer Architecture separates the application into multiple layers:

1. **Service Layer**: Contains the core business logic and services.
2. **Repository Layer**: Manages data access and database communication.
3. **Entity Layer**: Defines the data models and business entities.
4. **Controller Layer**: Handles user requests and manages the presentation of the application's endpoints.

## Configuration

### Metadata File

When creating a project, a `metadata.json` file is generated in the root directory to store the architecture type. This file is used to determine the architecture when generating additional code.

Example `metadata.json`:
```json
{
  "projectName": "MyProject",
  "architecture": "cleanarch"
}
```

## Example Workflow

1. **Create a new project**:
   ```bash
   codegen new MySpringApp -a cleanarch
   ```

2. **Navigate to the project directory**:
   ```bash
   cd MySpringApp
   ```

3. **Generate CRUD for an entity**:
   ```bash
   codegen generate-crud
   ```

4. **Review the generated code in the appropriate layers**:
   - **Clean Architecture**: Check the `application`, `domain`, `persistence`,`infrastructure` and `webapi` packages.
   - **N-Layer Architecture**: Look in the `controller`, `service`,`repository` and `entity` packages.

## Contributing

1. Fork the repository.
2. Create a new feature branch (`git checkout -b feature/your-feature`).
3. Commit your changes (`git commit -m 'Add some feature'`).
4. Push to the branch (`git push origin feature/your-feature`).
5. Create a pull request.

## License

This project is licensed under the MIT License.

## Troubleshooting

- **`command not found: codegen`**: Ensure that the JAR file and executable scripts are in the system PATH.
- **Errors during generation**: Check if the `metadata.json` file is present in the project root. Make sure the project structure matches the expected architecture.

## Contact

For any questions, please contact the project maintainers.