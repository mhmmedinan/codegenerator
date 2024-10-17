package com.codegenerator.core.codegen.templateengine;

import com.codegenerator.pipelinearchgen.domain.valueobjects.CrudTemplateData;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;



/**
 * This interface defines the structure for a template engine that can render templates asynchronously.
 * It includes methods for rendering templates from strings and files, as well as handling multiple files.
 */
public interface TemplateEngine {

    /**
     * Returns the file extension used by the template engine (e.g., ".html", ".ftl", ".tpl").
     *
     * @return the file extension for templates.
     */
    String getTemplateExtension();

    /**
     * Renders a template asynchronously using the given template data.
     *
     * @param template the template content as a string.
     * @param templateData the data to be injected into the template during rendering.
     * @return a CompletableFuture that resolves to the rendered template as a string.
     */
    CompletableFuture<String> renderAsync(String template, CrudTemplateData templateData);

    /**
     * Renders a template file asynchronously and saves the output to a specified directory.
     *
     * @param templateFilePath the path to the template file.
     * @param templateDir the directory where the template is located.
     * @param replacePathVariable a map of path variables to be replaced within the template.
     * @param outputDir the directory where the rendered output will be saved.
     * @param templateData the data to be injected into the template during rendering.
     * @return a CompletableFuture that resolves to the rendered template as a string.
     */
    CompletableFuture<String> renderFileAsync(
            String templateFilePath,
            String templateDir,
            Map<String, String> replacePathVariable,
            String outputDir,
            CrudTemplateData templateData
    );

    /**
     * Renders multiple template files asynchronously and saves the outputs to a specified directory.
     *
     * @param templateFilePaths the list of template file paths.
     * @param templateDir the directory where the templates are located.
     * @param replacePathVariable a map of path variables to be replaced within each template.
     * @param outputDir the directory where the rendered outputs will be saved.
     * @param templateData the data to be injected into each template during rendering.
     * @return a CompletableFuture that resolves to a list of rendered template contents as strings.
     */
    CompletableFuture<List<String>> renderFilesAsync(
            List<String> templateFilePaths,
            String templateDir,
            Map<String, String> replacePathVariable,
            String outputDir,
            CrudTemplateData templateData
    );
}


