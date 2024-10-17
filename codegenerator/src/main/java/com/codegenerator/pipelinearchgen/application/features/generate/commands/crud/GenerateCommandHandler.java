package com.codegenerator.pipelinearchgen.application.features.generate.commands.crud;

import com.codegenerator.core.codegen.code.StringUtils;
import com.codegenerator.core.codegen.file.DirectoryHelper;
import com.codegenerator.core.codegen.helpers.PlatformHelper;
import com.codegenerator.core.codegen.templateengine.TemplateEngine;
import com.codegenerator.pipelinearchgen.domain.contants.Templates;
import com.codegenerator.pipelinearchgen.domain.valueobjects.CrudTemplateData;
import lombok.RequiredArgsConstructor;
import java.util.*;
import java.util.concurrent.SubmissionPublisher;

@RequiredArgsConstructor
public class GenerateCommandHandler extends SubmissionPublisher<GeneratedCrudResponse> {

    private final TemplateEngine templateEngine;

    public void handle(GenerateCrudCommand request) {
        try {
            GeneratedCrudResponse response = new GeneratedCrudResponse();
            List<String> newFilePaths = new ArrayList<>();
            List<String> updatedFilePaths = new ArrayList<>();

            newFilePaths.addAll(generatePersistenceCodes(request.getProjectPath(), request.getCrudTemplateData()));
            response.setLastOperationMessage("Persistence layer codes have been generated.");
            response.setCurrentStatusMessage("Generating Application layer codes...");
            this.submit(response);

            newFilePaths.addAll(generateApplicationCodes(request.getProjectPath(), request.getCrudTemplateData()));
            response.setLastOperationMessage("Application layer codes have been generated.");
            this.submit(response);

            newFilePaths.addAll(generateWebAPICodes(request.getProjectPath(), request.getCrudTemplateData()));
            response.setLastOperationMessage("WebAPI layer codes have been generated.");
            response.setCurrentStatusMessage("Completed.");
            response.setNewFilePathsResult(newFilePaths);
            response.setUpdatedFilePathsResult(updatedFilePaths);
            this.submit(response);
            this.close();
        } catch (Exception e) {
            this.closeExceptionally(e);
        }
    }

        private Collection<String> generatePersistenceCodes(String projectPath, CrudTemplateData crudTemplateData){
        String templateDir = PlatformHelper.securedPathJoin("D:\\Projects\\codegenerator\\codegenerator\\src\\main\\java\\com\\codegenerator\\pipelinearchgen\\domain\\", Templates.Paths.CRUD,"folders","persistence");
        return generateFolderCodes(templateDir,PlatformHelper.securedPathJoin(projectPath,"persistence"),crudTemplateData);

    }

    private Collection<String> generateApplicationCodes(String projectPath, CrudTemplateData crudTemplateData){
        String templateDir = PlatformHelper.securedPathJoin("D:\\Projects\\codegenerator\\codegenerator\\src\\main\\java\\com\\codegenerator\\pipelinearchgen\\domain\\", Templates.Paths.CRUD,"folders","application");
        return generateFolderCodes(templateDir,PlatformHelper.securedPathJoin(projectPath,"application"),crudTemplateData);

    }

    private Collection<String> generateWebAPICodes(String projectPath, CrudTemplateData crudTemplateData){
        String templateDir = PlatformHelper.securedPathJoin("D:\\Projects\\codegenerator\\codegenerator\\src\\main\\java\\com\\codegenerator\\pipelinearchgen\\domain\\", Templates.Paths.CRUD,"folders","webapi");
        return generateFolderCodes(templateDir,PlatformHelper.securedPathJoin(projectPath,"webapi"),crudTemplateData);

    }



    public Collection<String> generateFolderCodes(String templateDir, String outputDir, CrudTemplateData crudTemplateData) {
            List<String> templateFilePaths = DirectoryHelper.getFilesInDirectoryTree(templateDir, "*." + templateEngine.getTemplateExtension());



            Map<String, String> replacePathVariable = new HashMap<>();
            replacePathVariable.put("PLURAL_ENTITY", StringUtils.toCamelCase(crudTemplateData.getEntity().getName())+ "s");
            replacePathVariable.put("ENTITY",StringUtils.toPascalCase(crudTemplateData.getEntity().getName()));
            replacePathVariable.put("projectName", crudTemplateData.getProjectName());


            try {
                return templateEngine.renderFilesAsync(templateFilePaths, templateDir, replacePathVariable, outputDir, crudTemplateData).get();
            } catch (Exception e) {
                throw new RuntimeException("Error while rendering files", e);
            }
    }

}
