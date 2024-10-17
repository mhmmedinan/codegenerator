package com.codegenerator.core.codegen.templateengine;

import com.codegenerator.core.codegen.file.FileHelper;
import com.codegenerator.pipelinearchgen.domain.valueobjects.CrudTemplateData;
import org.springframework.stereotype.Service;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.CompletableFuture;

/**
 * Implementation of the TemplateEngine interface that utilizes a template renderer to render templates.
 */
@Service
public class TemplateEngineImpl implements TemplateEngine {

    private final TemplateRenderer templateRenderer;

    public TemplateEngineImpl(TemplateRenderer templateRenderer) {
        this.templateRenderer = templateRenderer;
    }

    @Override
    public String getTemplateExtension() {
        return templateRenderer.getTemplateExtension();
    }

    @Override
    public CompletableFuture<String> renderAsync(String template, CrudTemplateData templateData) {
        return templateRenderer.renderAsync(template, templateData);
    }

    @Override
    public CompletableFuture<String> renderFileAsync(
            String templateFilePath,
            String templateDir,
            Map<String, String> replacePathVariable,
            String outputDir,
            CrudTemplateData templateData
    ) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                String templateFileText = Files.readString(Paths.get(templateFilePath));
                String newRenderedFileText = templateRenderer.renderAsync(templateFileText, templateData).get();
                String newRenderedFilePath = templateRenderer.renderAsync(
                        getOutputFilePath(templateFilePath, templateDir, replacePathVariable, outputDir),
                        templateData
                ).get();

                FileHelper.createFileAsync(newRenderedFilePath, newRenderedFileText);
                return newRenderedFilePath;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public CompletableFuture<List<String>> renderFilesAsync(
            List<String> templateFilePaths,
            String templateDir,
            Map<String, String> replacePathVariable,
            String outputDir,
            CrudTemplateData templateData
    ) {
        List<CompletableFuture<String>> futures = new ArrayList<>();
        for (String templateFilePath : templateFilePaths) {
            futures.add(renderFileAsync(templateFilePath, templateDir, replacePathVariable, outputDir, templateData));
        }
        return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                .thenApply(v -> {
                    List<String> renderedFilePaths = new ArrayList<>();
                    futures.forEach(future -> renderedFilePaths.add(future.join()));
                    return renderedFilePaths;
                });
    }

    private String getOutputFilePath(
            String templateFilePath,
            String templateDir,
            Map<String, String> replacePathVariable,
            String outputDir
    ) {
        templateDir = templateDir.replace("\\\\", "\\");
        templateFilePath = templateFilePath.replace("\\\\", "\\");
        String outputFilePath = templateFilePath;
        for (Map.Entry<String, String> entry : replacePathVariable.entrySet()) {
            outputFilePath = outputFilePath.replace(entry.getKey(), entry.getValue());
        }
        outputFilePath = outputFilePath
                .replace(templateDir, outputDir)
                .replace("." + templateRenderer.getTemplateExtension(), ".java");

        return outputFilePath;
    }
}
