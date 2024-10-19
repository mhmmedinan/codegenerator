package com.codegenerator.pipelinearchgen.cli.commands.generate;

import com.codegenerator.core.codegen.code.JavaCodeReader;
import com.codegenerator.core.codegen.code.valueobjects.PropertyInfo;
import com.codegenerator.core.codegen.templateengine.TemplateEngineImpl;
import com.codegenerator.core.codegen.templateengine.freemarker.FreemarkerTemplateRenderer;
import com.codegenerator.pipelinearchgen.application.features.generate.commands.crud.GenerateCommandHandler;
import com.codegenerator.pipelinearchgen.application.features.generate.commands.crud.GenerateCrudCommand;
import com.codegenerator.pipelinearchgen.application.features.generate.commands.crud.GenerateCrudSubscriber;
import com.codegenerator.pipelinearchgen.application.features.generate.commands.crud.GeneratedCrudResponse;
import com.codegenerator.pipelinearchgen.domain.valueobjects.CrudTemplateData;
import com.codegenerator.pipelinearchgen.domain.valueobjects.Entity;
import picocli.CommandLine.Command;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.SubmissionPublisher;

@Command(name = "generate-crud", description = "Generate CRUD operations")
public class GenerateCrudCliCommand implements Runnable {

    private GenerateCrudCliCommandSettings settings;

    private final GenerateCommandHandler handler;

    public GenerateCrudCliCommand() {
        this.handler = new GenerateCommandHandler(new TemplateEngineImpl(new FreemarkerTemplateRenderer()));
        this.settings = new GenerateCrudCliCommandSettings();
    }

    @Override
    public void run() {
        try {
            settings.checkProjectName();
            settings.checkEntityArgument();



            Path entityPath = Paths.get(settings.getProjectPath(), "domain", "entities", settings.getEntityName() + ".java");
            List<PropertyInfo> entityProperties = JavaCodeReader.readClassPropertiesAsync(entityPath.toString(), settings.getProjectPath()).get();
            Optional<PropertyInfo> idProperty = entityProperties.stream()
                    .filter(property -> property.getName().equals("id"))
                    .findFirst();
            String entityIdType = idProperty.map(PropertyInfo::getType).orElse("Long");

            GenerateCrudCommand generateCrudCommand = new GenerateCrudCommand(
                    settings.getProjectPath(),
                    new CrudTemplateData(
                            new Entity(settings.getEntityName(), entityIdType, entityProperties), settings.getProjectName()
            ));

            SubmissionPublisher<GeneratedCrudResponse> publisher = new SubmissionPublisher<>();
            GenerateCrudSubscriber subscriber = new GenerateCrudSubscriber();

            publisher.subscribe(subscriber);
            handler.subscribe(subscriber);
            handler.handle(generateCrudCommand);

            publisher.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
