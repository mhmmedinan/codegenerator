package com.codegenerator.core.codegen.templateengine;

import com.codegenerator.codegenerator.domain.valueobjects.CrudTemplateData;
import com.codegenerator.codegenerator.domain.valueobjects.NewProjectData;

import java.util.concurrent.CompletableFuture;


public interface TemplateRenderer {

    String getTemplateExtension();

    CompletableFuture<String> renderAsync(String template, CrudTemplateData data);
    CompletableFuture<String> renderNewAsync(String template, NewProjectData data);

}
