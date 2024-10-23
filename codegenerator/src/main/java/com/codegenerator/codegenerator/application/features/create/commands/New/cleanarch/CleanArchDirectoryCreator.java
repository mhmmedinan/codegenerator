package com.codegenerator.codegenerator.application.features.create.commands.New.cleanarch;

import com.codegenerator.core.application.constants.DirectoryPath;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CleanArchDirectoryCreator {

    public static Collection<String> handle(Path basePath,String projectName) {
        List<String> createdDirectories = new ArrayList<>();
        try {

            createdDirectories.add(createDirectory(basePath.resolve(DirectoryPath.Paths.BASE_PATH + projectName.toLowerCase())));
            createdDirectories.add(createDirectory(basePath.resolve(DirectoryPath.Paths.RESOURCES_PATH)));
            createdDirectories.add(createDirectory(basePath.resolve(DirectoryPath.Paths.BASE_PATH + projectName.toLowerCase())));

            createdDirectories.add(createDirectory(basePath.resolve(DirectoryPath.Paths.BASE_PATH + projectName.toLowerCase() + DirectoryPath.Paths.APPLICATION_FEATURES_PATH)));
            createdDirectories.add(createDirectory(basePath.resolve(DirectoryPath.Paths.BASE_PATH + projectName.toLowerCase() + DirectoryPath.Paths.APPLICATION_SERVICES_PATH)));

            createdDirectories.add(createDirectory(basePath.resolve(DirectoryPath.Paths.BASE_PATH + projectName.toLowerCase() + DirectoryPath.Paths.DOMAIN_ENTITIES_PATH)));

            createdDirectories.add(createDirectory(basePath.resolve(DirectoryPath.Paths.BASE_PATH + projectName.toLowerCase() + DirectoryPath.Paths.PERSISTENCE_REPOSITORIES_PATH)));

            createdDirectories.add(createDirectory(basePath.resolve(DirectoryPath.Paths.BASE_PATH + projectName.toLowerCase() + DirectoryPath.Paths.INFRASTRUCTURE_ADAPTERS_PATH)));

            createdDirectories.add(createDirectory(basePath.resolve(DirectoryPath.Paths.BASE_PATH + projectName.toLowerCase() + DirectoryPath.Paths.WEBAPI_CONTROLLER_PATH)));


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
