package com.codegenerator.codegenerator.cli.commands.factory;

import com.codegenerator.codegenerator.application.features.common.generate.GenerateCrudCommandHandler;
import com.codegenerator.codegenerator.application.features.create.commands.New.cleanarch.CleanArchNewProjectCommandHandler;
import com.codegenerator.codegenerator.application.features.common.New.NewProjectCommandHandler;
import com.codegenerator.codegenerator.application.features.generate.commands.crud.cleanarch.CleanArchGenerateCrudCommandHandler;
import com.codegenerator.core.codegen.templateengine.TemplateEngine;
import com.codegenerator.core.codegen.templateengine.TemplateEngineImpl;
import com.codegenerator.core.codegen.templateengine.freemarker.FreemarkerTemplateRenderer;

public class CommandHandlerFactory {
    private final TemplateEngine templateEngine;

    public CommandHandlerFactory() {
        this.templateEngine = new TemplateEngineImpl(new FreemarkerTemplateRenderer());
    }

    public NewProjectCommandHandler createNewHandler(String architecture) {
        switch (architecture.toLowerCase()) {
            case "cleanarch":
                return new CleanArchNewProjectCommandHandler(templateEngine);
           /* case "nlayer":
                return new NLayerNewProjectCommandHandler(templateEngine);*/
            // Başka mimariler eklenebilir
            default:
                System.err.println("Invalid architecture specified. Using default 'cleanarch'.");
                return new CleanArchNewProjectCommandHandler(templateEngine);
        }
    }

    public GenerateCrudCommandHandler createGenerateHandler(String architecture) {
        switch (architecture.toLowerCase()) {
            case "cleanarch":
                return new CleanArchGenerateCrudCommandHandler(templateEngine);
           /* case "nlayer":
                return new NLayerNewProjectCommandHandler(templateEngine);*/
            // Başka mimariler eklenebilir
            default:
                throw new IllegalArgumentException("Unsupported architecture: " + architecture);
        }
    }
}