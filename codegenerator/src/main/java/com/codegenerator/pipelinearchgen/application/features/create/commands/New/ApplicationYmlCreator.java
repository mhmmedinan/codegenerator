package com.codegenerator.pipelinearchgen.application.features.create.commands.New;

import com.codegenerator.core.application.constants.DirectoryPath;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ApplicationYmlCreator {
    public static void createApplicationProperties(Path basePath,String projectName) {
        Path propertiesPath = basePath.resolve(DirectoryPath.Paths.RESOURCES_YML_PATH);
        String propertiesContent = """
                server:
                    port: 8080
                spring:
                    application:
                         name: %s
                    datasource:
                         password: 12345
                         url: jdbc:postgresql://localhost:5432/test
                         username: postgres
                    jpa:
                        hibernate:
                            ddl-auto: update
                        properties:
                            hibernate:
                                 dialect: org.hibernate.dialect.PostgreSQLDialect
                            javax:
                                persistence:
                                    validation:
                                         mode: none
                        show-sql: true
                """.formatted(projectName);

        try (BufferedWriter writer = Files.newBufferedWriter(propertiesPath)) {
            writer.write(propertiesContent);
            System.out.println("Created application.yml: " + propertiesPath);
        } catch (IOException e) {
            System.err.println("Failed to create application.yml: " + e.getMessage());
        }
    }
}