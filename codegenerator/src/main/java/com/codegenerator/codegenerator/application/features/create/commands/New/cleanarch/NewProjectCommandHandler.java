package com.codegenerator.codegenerator.application.features.create.commands.New.cleanarch;

import com.codegenerator.codegenerator.domain.contants.Templates;
import com.codegenerator.codegenerator.domain.valueobjects.NewProjectData;
import com.codegenerator.core.application.constants.DirectoryPath;
import com.codegenerator.core.codegen.code.StringUtils;
import com.codegenerator.core.codegen.file.DirectoryHelper;
import com.codegenerator.core.codegen.helpers.PlatformHelper;
import com.codegenerator.core.codegen.templateengine.TemplateEngine;
import lombok.RequiredArgsConstructor;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.SubmissionPublisher;


@RequiredArgsConstructor
public class NewProjectCommandHandler extends SubmissionPublisher<NewProjectResponse> {

    private final TemplateEngine templateEngine;

    public void handle(NewProjectCommand request){

        try {
            String projectName = request.getNewProjectData().getProjectName();
            Path basePath = Paths.get(projectName);
            NewProjectResponse response = new NewProjectResponse();
            List<String> newFilePaths = new ArrayList<>();
            List<String> updatedFilePaths = new ArrayList<>();

            newFilePaths.addAll(DirectoryCreator.handle(basePath,projectName));
            response.setLastOperationMessage("Files have been created.");
            this.submit(response);
            newFilePaths.addAll(createConfigurationCodes(basePath, request.getNewProjectData()));
            this.submit(response);
            response.setNewFilePathsResult(newFilePaths);
            response.setUpdatedFilePathsResult(updatedFilePaths);
            this.close();
        }
        catch (Exception e){
            this.closeExceptionally(e);
        }
    }

    private Collection<String> createConfigurationCodes(Path projectPath, NewProjectData newProjectData){
        String templateDir = PlatformHelper.securedPathJoin(
                DirectoryHelper.getAssemblyDirectory(),
                Templates.Paths.CRUD, "folders","cleanarch", "configuration"
        );
        return createFolderCodes(templateDir,PlatformHelper.securedPathJoin(String.valueOf(projectPath)),newProjectData);

    }

    public Collection<String> createFolderCodes(String templateDir, String outputDir, NewProjectData newProjectData) {
        List<String> templateFilePaths = DirectoryHelper.getFilesInDirectoryTree(templateDir, "*." + templateEngine.getTemplateExtension());

        Map<String, String> replacePathVariable = new HashMap<>();
        replacePathVariable.put("APPLICATION_NAME", StringUtils.toPascalCase(newProjectData.getProjectName()));
        replacePathVariable.put("projectName", newProjectData.getProjectName());


        try {
            return renderNewFilesAsync(templateFilePaths, templateDir, replacePathVariable, outputDir, newProjectData).get();
        } catch (Exception e) {
            throw new RuntimeException("Error while rendering files", e);
        }
    }

    private CompletableFuture<List<String>> renderNewFilesAsync(List<String> templateFilePaths, String templateDir, Map<String, String> replacePathVariable, String outputDir, NewProjectData templateData) {
        List<CompletableFuture<String>> futures = new ArrayList<>();
        for (String templateFilePath : templateFilePaths) {
            String filename = Paths.get(templateFilePath).getFileName().toString();
            String specificOutputDir = determineOutputDir(outputDir, filename,templateData.getProjectName());
            futures.add(templateEngine.renderNewFileAsync(templateFilePath, templateDir, replacePathVariable, specificOutputDir, templateData));
        }
        return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                .thenApply(v -> {
                    List<String> renderedFilePaths = new ArrayList<>();
                    futures.forEach(future -> renderedFilePaths.add(future.join()));
                    return renderedFilePaths;
                });
    }


    private String determineOutputDir(String defaultOutputDir, String filename,String projectName) {
        switch (filename) {
            case "application.yml.ftl","message.properties.ftl","message_en.properties.ftl","message_tr.properties.ftl":
                return PlatformHelper.securedPathJoin(defaultOutputDir, DirectoryPath.Paths.RESOURCES_PATH);
            case "APPLICATION_NAMEApplication.ftl":
                return PlatformHelper.securedPathJoin(defaultOutputDir, DirectoryPath.Paths.BASE_PATH + projectName.toLowerCase());
            default:
                return defaultOutputDir;
        }

    }


}
