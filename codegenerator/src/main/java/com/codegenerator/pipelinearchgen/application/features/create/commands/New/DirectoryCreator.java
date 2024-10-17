package com.codegenerator.pipelinearchgen.application.features.create.commands.New;

import java.nio.file.Files;
import java.nio.file.Path;

public class DirectoryCreator {
    public static void createDirectory(Path path) {
        try {
            Files.createDirectories(path);
            System.out.println("Created directory: " + path);
        } catch (Exception e) {
            System.err.println("Failed to create directory: " + path + " - " + e.getMessage());
        }
    }
}
