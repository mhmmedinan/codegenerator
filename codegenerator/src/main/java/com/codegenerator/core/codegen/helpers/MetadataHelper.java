package com.codegenerator.core.codegen.helpers;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class MetadataHelper {
    public static void createMetadataFile(Path projectPath, String architectureType,String projectName) {
        Path metadataPath = projectPath.resolve("metadata.json");
        String content = "{ \"architecture\": \"" + architectureType + "\", \"projectName\": \"" + projectName + "\" }";

        try {
            Files.createDirectories(projectPath);
            Files.writeString(metadataPath, content);
            System.out.println("Metadata file created at: " + metadataPath);
        } catch (IOException e) {
            System.err.println("Failed to create metadata file: " + e.getMessage());
        }
    }

    public static String getArchitectureFromMetadata(String projectRootPath) {
        Path metadataPath = Paths.get(projectRootPath, "metadata.json");
        if (!Files.exists(metadataPath)) {
            throw new RuntimeException("Metadata file not found. Please ensure the project was created correctly.");
        }

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> jsonMap = objectMapper.readValue(metadataPath.toFile(), Map.class);
            return (String) jsonMap.get("architecture");
        } catch (Exception e) {
            throw new RuntimeException("Failed to read architecture from metadata.json: " + e.getMessage(), e);
        }
    }
}
