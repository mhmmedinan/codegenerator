package com.codegenerator.pipelinearchgen.application.features.create.commands.New;

import com.codegenerator.core.application.constants.DirectoryPath;
import com.codegenerator.core.codegen.code.StringUtils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ApplicationClassCreator {
    public static void createApplicationClass(Path basePath, String projectName) {
        Path applicationPath = basePath.resolve(DirectoryPath.Paths.BASE_PATH + projectName.toLowerCase() + "/" + StringUtils.toPascalCase(projectName) + "Application.java");
        String applicationContent = """
                package com.%s;

                import org.springframework.boot.SpringApplication;
                import org.springframework.boot.autoconfigure.SpringBootApplication;

                @SpringBootApplication(scanBasePackages = {"com.%s",
                		"io.github.mhmmedinan.core_localization","io.github.mhmmedinan.core_persistence","io.github.mhmmedinan.core_crosscuttingconcerns"})
                public class %sApplication {

                    public static void main(String[] args) {
                        SpringApplication.run(%sApplication.class, args);
                    }
                }
                """.formatted(projectName.toLowerCase(), StringUtils.toPascalCase(projectName), StringUtils.toPascalCase(projectName));

        try (BufferedWriter writer = Files.newBufferedWriter(applicationPath)) {
            writer.write(applicationContent);
            System.out.println("Created application class: " + applicationPath);
        } catch (IOException e) {
            System.err.println("Failed to create application class: " + e.getMessage());
        }
    }
}