package com.codegenerator.pipelinearchgen.application.features.create.commands.New;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ApplicationPropertiesCreator {
    public static void createApplicationProperties(Path basePath) {
        Path propertiesPath = basePath.resolve("src/main/resources/application.properties");
        String propertiesContent = """
                # Spring Boot application properties

                spring.datasource.url=jdbc:h2:mem:testdb
                spring.datasource.driverClassName=org.h2.Driver
                spring.datasource.username=sa
                spring.datasource.password=
                spring.h2.console.enabled=true

                spring.jpa.hibernate.ddl-auto=update
                spring.jpa.show-sql=true
                """;

        try (BufferedWriter writer = Files.newBufferedWriter(propertiesPath)) {
            writer.write(propertiesContent);
            System.out.println("Created application.properties: " + propertiesPath);
        } catch (IOException e) {
            System.err.println("Failed to create application.properties: " + e.getMessage());
        }
    }
}
