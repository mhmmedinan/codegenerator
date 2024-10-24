package com.codegenerator.codegenerator.application.features.generate.commands.crud.cleanarch;

import com.codegenerator.codegenerator.application.features.common.generate.GenerateCrudCommandHandler;
import com.codegenerator.codegenerator.application.features.common.responses.BaseResponse;
import com.codegenerator.codegenerator.application.features.common.generate.GenerateCrudCommand;
import com.codegenerator.core.codegen.code.StringUtils;
import com.codegenerator.core.codegen.file.DirectoryHelper;
import com.codegenerator.core.codegen.helpers.PlatformHelper;
import com.codegenerator.core.codegen.templateengine.TemplateEngine;
import com.codegenerator.codegenerator.domain.contants.Templates;
import com.codegenerator.codegenerator.domain.valueobjects.CrudTemplateData;
import lombok.RequiredArgsConstructor;
import java.util.*;
import java.util.concurrent.SubmissionPublisher;

@RequiredArgsConstructor
public class CleanArchGenerateCrudCommandHandler extends GenerateCrudCommandHandler {

    private final TemplateEngine templateEngine;

    @Override
    public void handle(GenerateCrudCommand request) {
        try {
            BaseResponse response = new BaseResponse();
            List<String> newFilePaths = new ArrayList<>();
            List<String> updatedFilePaths = new ArrayList<>();

            newFilePaths.addAll(generatePersistenceCodes(request.getProjectPath(), request.getCrudTemplateData()));
            response.setLastOperationMessage("Persistence layer codes have been generated.");
            response.setCurrentStatusMessage("Generating Application layer codes...");
            this.submit(response);

            newFilePaths.addAll(generateApplicationCodes(request.getProjectPath(), request.getCrudTemplateData()));
            response.setLastOperationMessage("Application layer codes have been generated.");
            response.setCurrentStatusMessage("Generating WebAPI layer codes...");
            this.submit(response);

            newFilePaths.addAll(generateWebAPICodes(request.getProjectPath(), request.getCrudTemplateData()));
            response.setLastOperationMessage("WebAPI layer codes have been generated.");
            this.submit(response);
            response.setCurrentStatusMessage("Completed.");
            response.setNewFilePathsResult(newFilePaths);
            response.setUpdatedFilePathsResult(updatedFilePaths);
            this.close();
        } catch (Exception e) {
            this.closeExceptionally(e);
        }
    }

        private Collection<String> generatePersistenceCodes(String projectPath, CrudTemplateData crudTemplateData)
        {
            String templateDir = PlatformHelper.securedPathJoin(
                    DirectoryHelper.getAssemblyDirectory(),
                    Templates.Paths.CRUD, "folders","cleanarch", "persistence"
            );
            return generateFolderCodes(templateDir, PlatformHelper.securedPathJoin(projectPath, "persistence"), crudTemplateData);

        }

    private Collection<String> generateApplicationCodes(String projectPath, CrudTemplateData crudTemplateData){
        String templateDir = PlatformHelper.securedPathJoin(
                DirectoryHelper.getAssemblyDirectory(),
                 Templates.Paths.CRUD, "folders","cleanarch", "application"
        );
        return generateFolderCodes(templateDir,PlatformHelper.securedPathJoin(projectPath,"application"),crudTemplateData);

    }

    private Collection<String> generateWebAPICodes(String projectPath, CrudTemplateData crudTemplateData){
        String templateDir = PlatformHelper.securedPathJoin(
                DirectoryHelper.getAssemblyDirectory(),
                 Templates.Paths.CRUD, "folders","cleanarch", "webapi"
        );
        return generateFolderCodes(templateDir,PlatformHelper.securedPathJoin(projectPath,"webapi"),crudTemplateData);

    }


    public Collection<String> generateFolderCodes(String templateDir, String outputDir, CrudTemplateData crudTemplateData) {
            List<String> templateFilePaths = DirectoryHelper.getFilesInDirectoryTree(templateDir, "*." + templateEngine.getTemplateExtension());

            Map<String, String> replacePathVariable = new HashMap<>();
            replacePathVariable.put("PLURAL_ENTITY", StringUtils.toCamelCase(crudTemplateData.getEntity().getName())+ "s");
            replacePathVariable.put("ENTITY",StringUtils.toPascalCase(crudTemplateData.getEntity().getName()));
            replacePathVariable.put("projectName", crudTemplateData.getProjectName());
            replacePathVariable.put("PLURAL_CONTROLLER",StringUtils.toPascalCase(crudTemplateData.getEntity().getName())+"s");


            try {
                return templateEngine.renderFilesAsync(templateFilePaths, templateDir, replacePathVariable, outputDir, crudTemplateData).get();
            } catch (Exception e) {
                throw new RuntimeException("Error while rendering files", e);
            }
    }

}
