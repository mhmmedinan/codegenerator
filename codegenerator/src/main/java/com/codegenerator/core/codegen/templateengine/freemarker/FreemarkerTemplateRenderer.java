package com.codegenerator.core.codegen.templateengine.freemarker;

import com.codegenerator.codegenerator.domain.valueobjects.NewProjectData;
import com.codegenerator.core.codegen.templateengine.TemplateRenderer;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;
import com.codegenerator.codegenerator.domain.valueobjects.CrudTemplateData;
import freemarker.template.*;
import org.springframework.stereotype.Service;
import java.io.StringWriter;
import java.util.concurrent.CompletionException;
import java.util.Map;


@Service
public class FreemarkerTemplateRenderer implements TemplateRenderer {

    private final Configuration configuration;

    public FreemarkerTemplateRenderer() {
        this.configuration = new Configuration(Configuration.VERSION_2_3_31);
        this.configuration.setSharedVariable("string", new FreemarkerBuiltinFunctionsExtensions.StringFunctionsModel());
    }

    @Override
    public String getTemplateExtension() {
        return "ftl";
    }


    @Override
    public CompletableFuture<String> renderAsync(String template, CrudTemplateData data) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Template freemarkerTemplate = new Template("template", new StringReader(template), configuration);

                Map<String, Object> templateData = new HashMap<>();
                templateData.put("projectName", data.getProjectName());
                templateData.put("pluralEntityName", data.getEntity().getName() + "s");
                templateData.put("entity",data.getEntity());

                StringWriter writer = new StringWriter();
                freemarkerTemplate.process(templateData,writer);
                return writer.toString();
            } catch (IOException | TemplateException e) {
                throw new CompletionException(e);
            }
        });
    }

    @Override
    public CompletableFuture<String> renderNewAsync(String template, NewProjectData data) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Template freemarkerTemplate = new Template("template", new StringReader(template), configuration);

                Map<String, Object> templateData = new HashMap<>();
                templateData.put("projectName", data.getProjectName());

                StringWriter writer = new StringWriter();
                freemarkerTemplate.process(templateData,writer);
                return writer.toString();
            } catch (IOException | TemplateException e) {
                throw new CompletionException(e);
            }
        });
    }
}

