package com.codegenerator.codegenerator.application.features.create.commands.New.nlayerarch;

import com.codegenerator.core.application.constants.DirectoryPath;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class NLayerArchDirectoryCreator {
    public static Collection<String> handle(Path basePath, String projectName) {
        List<String> createdDirectories = new ArrayList<>();
        try {

            createdDirectories.add(createDirectory(basePath.resolve(DirectoryPath.Paths.BASE_PATH + projectName.toLowerCase())));
            createdDirectories.add(createDirectory(basePath.resolve(DirectoryPath.Paths.RESOURCES_PATH)));

            createdDirectories.add(createDirectory(basePath.resolve(DirectoryPath.Paths.BASE_PATH + projectName.toLowerCase() + DirectoryPath.Paths.BASE_SERVICE_PATH)));

            createdDirectories.add(createDirectory(basePath.resolve(DirectoryPath.Paths.BASE_PATH + projectName.toLowerCase() + DirectoryPath.Paths.BASE_ENTITY_PATH)));

            createdDirectories.add(createDirectory(basePath.resolve(DirectoryPath.Paths.BASE_PATH + projectName.toLowerCase() + DirectoryPath.Paths.BASE_REPOSITORY_PATH)));

            createdDirectories.add(createDirectory(basePath.resolve(DirectoryPath.Paths.BASE_PATH + projectName.toLowerCase() + DirectoryPath.Paths.BASE_CONTROLLER_PATH)));


        } catch (Exception e) {
            throw new RuntimeException("Failed to create project structure: " + e.getMessage());
        }
        return createdDirectories;
    }

    public static String createDirectory(Path path) {
        try {
            Files.createDirectories(path);
            return path.toString();
        } catch (Exception e) {
            throw new RuntimeException("Failed to create directory: " + path + " - " + e.getMessage());
        }
    }
}
