package com.codegenerator.core.codegen.templateengine;

import com.codegenerator.codegenerator.domain.valueobjects.NewProjectData;
import com.codegenerator.core.application.constants.DirectoryPath;
import com.codegenerator.core.codegen.file.FileHelper;
import com.codegenerator.codegenerator.domain.valueobjects.CrudTemplateData;
import com.codegenerator.core.codegen.helpers.PlatformHelper;
import org.springframework.stereotype.Service;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.CompletableFuture;

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
    public CompletableFuture<String> renderNewFileAsync(String templateFilePath, String templateDir, Map<String, String> replacePathVariable, String outputDir, NewProjectData templateData) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                String templateFileText = Files.readString(Paths.get(templateFilePath));
                String newRenderedFileText = templateRenderer.renderNewAsync(templateFileText, templateData).get();
                String newRenderedFilePath = templateRenderer.renderNewAsync(
                        getOutputNewFilePath(templateFilePath, templateDir, replacePathVariable, outputDir),
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

    @Override
    public CompletableFuture<List<String>> renderNewFilesAsync(List<String> templateFilePaths, String templateDir, Map<String, String> replacePathVariable, String outputDir, NewProjectData templateData) {
        List<CompletableFuture<String>> futures = new ArrayList<>();
        for (String templateFilePath : templateFilePaths) {
            String filename = Paths.get(templateFilePath).getFileName().toString();
            String specificOutputDir = determineOutputDir(outputDir, filename,templateData.getProjectName());
            futures.add(renderNewFileAsync(templateFilePath, templateDir, replacePathVariable, specificOutputDir, templateData));
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

    private String getOutputNewFilePath(
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
        String templateExtension = templateRenderer.getTemplateExtension();
        if (templateFilePath.endsWith("pom.xml." + templateExtension)) {
            outputFilePath = outputFilePath.replace(".xml." + templateExtension, ".xml");
        } else if (templateFilePath.endsWith("application.yml." + templateExtension)) {
            outputFilePath = outputFilePath.replace(".yml." + templateExtension, ".yml");
        }
        else if (templateFilePath.endsWith(".properties." + templateExtension )) {
            outputFilePath = outputFilePath.replace(".properties." + templateExtension, ".properties");
        }
        else {
            outputFilePath = outputFilePath.replace("." + templateExtension, ".java");
        }
        outputFilePath = outputFilePath.replace(templateDir, outputDir);

        return outputFilePath;
    }
}
