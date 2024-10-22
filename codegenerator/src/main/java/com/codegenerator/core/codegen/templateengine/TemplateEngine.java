package com.codegenerator.core.codegen.templateengine;

import com.codegenerator.pipelinearchgen.domain.valueobjects.CrudTemplateData;
import com.codegenerator.pipelinearchgen.domain.valueobjects.NewProjectData;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface TemplateEngine {


    String getTemplateExtension();

    CompletableFuture<String> renderFileAsync(
            String templateFilePath,
            String templateDir,
            Map<String, String> replacePathVariable,
            String outputDir,
            CrudTemplateData templateData
    );


    CompletableFuture<List<String>> renderFilesAsync(
            List<String> templateFilePaths,
            String templateDir,
            Map<String, String> replacePathVariable,
            String outputDir,
            CrudTemplateData templateData
    );

}


