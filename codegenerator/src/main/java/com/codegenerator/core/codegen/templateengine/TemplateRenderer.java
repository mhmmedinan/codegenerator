package com.codegenerator.core.codegen.templateengine;

import com.codegenerator.codegenerator.domain.valueobjects.CrudTemplateData;
import java.util.concurrent.CompletableFuture;


public interface TemplateRenderer {

    String getTemplateExtension();

    CompletableFuture<String> renderAsync(String template, CrudTemplateData data);
}
