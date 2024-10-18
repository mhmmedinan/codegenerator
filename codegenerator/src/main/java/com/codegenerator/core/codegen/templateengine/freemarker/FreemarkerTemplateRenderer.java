package com.codegenerator.core.codegen.templateengine.freemarker;

import com.codegenerator.core.codegen.code.StringUtils;
import com.codegenerator.core.codegen.templateengine.TemplateRenderer;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;
import com.codegenerator.pipelinearchgen.domain.valueobjects.CrudTemplateData;
import freemarker.template.*;
import org.springframework.stereotype.Service;
import java.io.StringWriter;
import java.util.concurrent.CompletionException;
import java.util.Map;


@Service
public class FreemarkerTemplateRenderer implements TemplateRenderer {

    @Override
    public String getTemplateExtension() {
        return "ftl";
    }

    @Override
    public CompletableFuture<String> renderAsync(String template, CrudTemplateData data) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Template freemarkerTemplate = new Template("template", new StringReader(template), new Configuration(Configuration.VERSION_2_3_31));

                Map<String, Object> templateData = new HashMap<>();
                templateData.put("projectName", data.getProjectName());
                templateData.put("pluralEntityName", StringUtils.toCamelCase(data.getEntity().getName()) + "s");
                templateData.put("entity",data.getEntity());

                StringWriter writer = new StringWriter();
                freemarkerTemplate.process(templateData,writer);
                return writer.toString();
            } catch (IOException | TemplateException e) {
                throw new CompletionException(e);
            }
        });
    }
}

