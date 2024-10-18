package com.codegenerator.pipelinearchgen.application.features.create.commands.New;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CreateNewProjectCommand {
    public static void createProjectStructure(String projectName) {
        try {
            Path basePath = Paths.get(projectName);

            if (Files.exists(basePath)) {
                System.err.println("Warning: The directory '" + projectName + "' already exists. Please choose a different name or remove the existing directory.");
                return;
            }

            // Create standard Spring Boot package structure
            DirectoryCreator.createDirectory(basePath.resolve("src/main/java/com/" + projectName.toLowerCase()));
            DirectoryCreator.createDirectory(basePath.resolve("src/main/resources"));
            DirectoryCreator.createDirectory(basePath.resolve("src/test/java/com/" + projectName.toLowerCase()));

            // Create application layer directories
            DirectoryCreator.createDirectory(basePath.resolve("src/main/java/com/" + projectName.toLowerCase() + "/application/features"));

            // Create domain layer directories
            DirectoryCreator.createDirectory(basePath.resolve("src/main/java/com/" + projectName.toLowerCase() + "/domain/entities"));

            // Create persistence layer directories
            DirectoryCreator.createDirectory(basePath.resolve("src/main/java/com/" + projectName.toLowerCase() + "/persistence/repositories"));

            // Create infrastructure layer directories
            DirectoryCreator.createDirectory(basePath.resolve("src/main/java/com/" + projectName.toLowerCase() + "/infrastructure/adapters"));

            // Create webapi layer directories
            DirectoryCreator.createDirectory(basePath.resolve("src/main/java/com/" + projectName.toLowerCase() + "/webapi/controllers"));

            // Create example application files
            ApplicationClassCreator.createApplicationClass(basePath, projectName);
            ApplicationPropertiesCreator.createApplicationProperties(basePath);

            // Create pom.xml
            PomXmlCreator.createPomXml(basePath, projectName);

            System.out.println("Project structure created successfully!");

        } catch (Exception e) {
            System.err.println("Failed to create project structure: " + e.getMessage());
        }
    }
}
