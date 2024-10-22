package com.codegenerator.pipelinearchgen.application.features.create.commands.New;

import com.codegenerator.core.application.constants.DirectoryPath;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DirectoryCreator {

    public static Collection<String> handle(NewProjectCommand request) {
        List<String> createdDirectories = new ArrayList<>();
        try {
            String projectName = request.getNewProjectData().getProjectName();
            Path basePath = Paths.get(request.getNewProjectData().getProjectName());

            if (Files.exists(basePath)) {
                throw new RuntimeException("Warning: The directory '" + projectName + "' already exists. Please choose a different name or remove the existing directory.");
            }

            createdDirectories.add(createDirectory(basePath.resolve(DirectoryPath.Paths.BASE_PATH + projectName.toLowerCase())));
            createdDirectories.add(createDirectory(basePath.resolve(DirectoryPath.Paths.RESOURCES_PATH)));
            createdDirectories.add(createDirectory(basePath.resolve(DirectoryPath.Paths.BASE_PATH + projectName.toLowerCase())));

            createdDirectories.add(createDirectory(basePath.resolve(DirectoryPath.Paths.BASE_PATH + projectName.toLowerCase() + DirectoryPath.Paths.APPLICATION_FEATURES_PATH)));
            createdDirectories.add(createDirectory(basePath.resolve(DirectoryPath.Paths.BASE_PATH + projectName.toLowerCase() + DirectoryPath.Paths.APPLICATION_SERVICES_PATH)));

            createdDirectories.add(createDirectory(basePath.resolve(DirectoryPath.Paths.BASE_PATH + projectName.toLowerCase() + DirectoryPath.Paths.DOMAIN_ENTITIES_PATH)));

            createdDirectories.add(createDirectory(basePath.resolve(DirectoryPath.Paths.BASE_PATH + projectName.toLowerCase() + DirectoryPath.Paths.PERSISTENCE_REPOSITORIES_PATH)));

            createdDirectories.add(createDirectory(basePath.resolve(DirectoryPath.Paths.BASE_PATH + projectName.toLowerCase() + DirectoryPath.Paths.INFRASTRUCTURE_ADAPTERS_PATH)));

            createdDirectories.add(createDirectory(basePath.resolve(DirectoryPath.Paths.BASE_PATH + projectName.toLowerCase() + DirectoryPath.Paths.WEBAPI_CONTROLLER_PATH)));

            ApplicationClassCreator.createApplicationClass(basePath,projectName);
            ApplicationYmlCreator.createApplicationProperties(basePath,projectName);
            PomXmlCreator.createPomXml(basePath,projectName);
            MessagesPropertiesCreator.create(basePath);


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
