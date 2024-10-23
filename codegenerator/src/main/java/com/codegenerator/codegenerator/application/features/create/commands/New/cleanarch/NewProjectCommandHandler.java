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
            return templateEngine.renderNewFilesAsync(templateFilePaths, templateDir, replacePathVariable, outputDir, newProjectData).get();
        } catch (Exception e) {
            throw new RuntimeException("Error while rendering files", e);
        }
    }




}
