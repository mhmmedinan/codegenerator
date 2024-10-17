package com.codegenerator.core.codegen.templateengine;

import com.codegenerator.pipelinearchgen.domain.valueobjects.CrudTemplateData;

import java.util.concurrent.CompletableFuture;

/**
 * Interface for template rendering operations.
 * Defines methods for rendering templates asynchronously and getting template extensions.
 */
public interface TemplateRenderer {

    /**
     * Retrieves the extension for templates that this renderer supports (e.g., ".html", ".txt").
     *
     * @return the template file extension.
     */
    String getTemplateExtension();

    /**
     * Renders the given template asynchronously with the provided data.
     *
     * @param template The template string to render.
     * @param data     The template data to be injected during rendering.
     * @return A CompletableFuture representing the rendered result.
     */
    CompletableFuture<String> renderAsync(String template, CrudTemplateData data);
}
