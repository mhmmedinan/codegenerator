package com.codegenerator.pipelinearchgen.application.features.create.commands.New;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ApplicationClassCreator {
    public static void createApplicationClass(Path basePath, String projectName) {
        Path applicationPath = basePath.resolve("src/main/java/com/" + projectName.toLowerCase() + "/" + projectName + "Application.java");
        String applicationContent = """
                package com.%s;

                import org.springframework.boot.SpringApplication;
                import org.springframework.boot.autoconfigure.SpringBootApplication;

                @SpringBootApplication
                public class %sApplication {

                    public static void main(String[] args) {
                        SpringApplication.run(%sApplication.class, args);
                    }
                }
                """.formatted(projectName.toLowerCase(), projectName, projectName);

        try (BufferedWriter writer = Files.newBufferedWriter(applicationPath)) {
            writer.write(applicationContent);
            System.out.println("Created application class: " + applicationPath);
        } catch (IOException e) {
            System.err.println("Failed to create application class: " + e.getMessage());
        }
    }
}

