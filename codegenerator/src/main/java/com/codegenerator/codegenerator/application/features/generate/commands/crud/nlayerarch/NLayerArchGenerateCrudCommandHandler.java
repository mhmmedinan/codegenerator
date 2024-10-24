package com.codegenerator.codegenerator.application.features.generate.commands.crud.nlayerarch;

import com.codegenerator.codegenerator.application.features.common.generate.GenerateCrudCommand;
import com.codegenerator.codegenerator.application.features.common.generate.GenerateCrudCommandHandler;
import com.codegenerator.codegenerator.application.features.common.responses.BaseResponse;
import com.codegenerator.codegenerator.domain.contants.Templates;
import com.codegenerator.codegenerator.domain.valueobjects.CrudTemplateData;
import com.codegenerator.core.codegen.code.StringUtils;
import com.codegenerator.core.codegen.file.DirectoryHelper;
import com.codegenerator.core.codegen.helpers.PlatformHelper;
import com.codegenerator.core.codegen.templateengine.TemplateEngine;
import lombok.RequiredArgsConstructor;
import java.util.*;

@RequiredArgsConstructor
public class NLayerArchGenerateCrudCommandHandler extends GenerateCrudCommandHandler {

    private final TemplateEngine templateEngine;

    @Override
    public void handle(GenerateCrudCommand request) {
        try {
            BaseResponse response = new BaseResponse();
            List<String> newFilePaths = new ArrayList<>();
            List<String> updatedFilePaths = new ArrayList<>();

            newFilePaths.addAll(generateRepositoryCodes(request.getProjectPath(), request.getCrudTemplateData()));
            response.setLastOperationMessage("Repository layer codes have been generated.");
            response.setCurrentStatusMessage("Generating Service layer codes...");
            this.submit(response);

            newFilePaths.addAll(generateServiceCodes(request.getProjectPath(), request.getCrudTemplateData()));
            response.setLastOperationMessage("Service layer codes have been generated.");
            response.setCurrentStatusMessage("Generating Controller layer codes...");
            this.submit(response);

            newFilePaths.addAll(generateControllerCodes(request.getProjectPath(), request.getCrudTemplateData()));
            response.setLastOperationMessage("Controller layer codes have been generated.");
            this.submit(response);
            response.setCurrentStatusMessage("Completed.");
            response.setNewFilePathsResult(newFilePaths);
            response.setUpdatedFilePathsResult(updatedFilePaths);
            this.close();
        } catch (Exception e) {
            this.closeExceptionally(e);
        }
    }

        private Collection<String> generateRepositoryCodes(String projectPath, CrudTemplateData crudTemplateData)
        {
            String templateDir = PlatformHelper.securedPathJoin(
                    DirectoryHelper.getAssemblyDirectory(),
                    Templates.Paths.CRUD, "folders","nlayerarch", "repository"
            );
            return generateFolderCodes(templateDir, PlatformHelper.securedPathJoin(projectPath, "repository"), crudTemplateData);

        }

    private Collection<String> generateServiceCodes(String projectPath, CrudTemplateData crudTemplateData){
        String templateDir = PlatformHelper.securedPathJoin(
                DirectoryHelper.getAssemblyDirectory(),
                 Templates.Paths.CRUD, "folders","nlayerarch", "service"
        );
        return generateFolderCodes(templateDir,PlatformHelper.securedPathJoin(projectPath,"service"),crudTemplateData);

    }

    private Collection<String> generateControllerCodes(String projectPath, CrudTemplateData crudTemplateData){
        String templateDir = PlatformHelper.securedPathJoin(
                DirectoryHelper.getAssemblyDirectory(),
                 Templates.Paths.CRUD, "folders","nlayerarch", "controller"
        );
        return generateFolderCodes(templateDir,PlatformHelper.securedPathJoin(projectPath,"controller"),crudTemplateData);

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
