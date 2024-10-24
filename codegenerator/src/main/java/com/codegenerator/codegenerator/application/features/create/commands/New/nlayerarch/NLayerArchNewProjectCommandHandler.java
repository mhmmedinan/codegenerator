package com.codegenerator.codegenerator.application.features.create.commands.New.nlayerarch;

import com.codegenerator.codegenerator.application.features.common.New.NewProjectCommand;
import com.codegenerator.codegenerator.application.features.common.New.NewProjectCommandHandler;
import com.codegenerator.codegenerator.application.features.common.responses.BaseResponse;
import com.codegenerator.codegenerator.domain.contants.Templates;
import com.codegenerator.codegenerator.domain.valueobjects.NewProjectData;
import com.codegenerator.core.codegen.code.StringUtils;
import com.codegenerator.core.codegen.file.DirectoryHelper;
import com.codegenerator.core.codegen.helpers.MetadataHelper;
import com.codegenerator.core.codegen.helpers.PlatformHelper;
import com.codegenerator.core.codegen.templateengine.TemplateEngine;
import lombok.RequiredArgsConstructor;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@RequiredArgsConstructor
public class NLayerArchNewProjectCommandHandler extends NewProjectCommandHandler {

    private final TemplateEngine templateEngine;

    @Override
    public void handle(NewProjectCommand request) {
        try {
            String projectName = request.getNewProjectData().getProjectName();
            Path basePath = Paths.get(projectName);
            BaseResponse response = new BaseResponse();
            List<String> newFilePaths = new ArrayList<>();
            List<String> updatedFilePaths = new ArrayList<>();

            File projectDirectory = basePath.toFile();
            if (projectDirectory.exists() && projectDirectory.isDirectory()) {
                System.out.println("The directory '" + projectName + "' already exists. Please choose a different name or remove the existing directory.");
                this.close();
                return;
            }

            newFilePaths.addAll(NLayerArchDirectoryCreator.handle(basePath, projectName));
            response.setLastOperationMessage("Project directories have been created.");
            this.submit(response);

            MetadataHelper.createMetadataFile(basePath, request.getArchitecture(), projectName);


            newFilePaths.addAll(createConfigurationCodes(basePath, request.getNewProjectData()));
            response.setLastOperationMessage("Configuration files (application.yml, pom.xml) have been created.");
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
                Templates.Paths.CRUD, "folders","common", "configuration"
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
