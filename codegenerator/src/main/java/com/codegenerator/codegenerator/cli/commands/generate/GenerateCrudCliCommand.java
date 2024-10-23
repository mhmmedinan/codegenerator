package com.codegenerator.codegenerator.cli.commands.generate;

import com.codegenerator.codegenerator.application.features.common.generate.GenerateCrudCommandHandler;
import com.codegenerator.codegenerator.application.features.common.responses.BaseResponse;
import com.codegenerator.codegenerator.cli.commands.factory.CommandHandlerFactory;
import com.codegenerator.core.codegen.code.JavaCodeReader;
import com.codegenerator.core.codegen.code.valueobjects.PropertyInfo;
import com.codegenerator.core.codegen.helpers.MetadataHelper;
import com.codegenerator.core.codegen.templateengine.TemplateEngineImpl;
import com.codegenerator.core.codegen.templateengine.freemarker.FreemarkerTemplateRenderer;
import com.codegenerator.codegenerator.application.features.generate.commands.crud.cleanarch.CleanArchGenerateCrudCommandHandler;
import com.codegenerator.codegenerator.application.features.common.generate.GenerateCrudCommand;
import com.codegenerator.codegenerator.application.features.common.generate.GenerateCrudSubscriber;
import com.codegenerator.codegenerator.domain.valueobjects.CrudTemplateData;
import com.codegenerator.codegenerator.domain.valueobjects.Entity;
import picocli.CommandLine.Command;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.SubmissionPublisher;

@Command(name = "generate-crud", description = "Generate CRUD operations")
public class GenerateCrudCliCommand implements Runnable {

    private GenerateCrudCliCommandSettings settings;

    public GenerateCrudCliCommand() {
        this.settings = new GenerateCrudCliCommandSettings();
    }

    @Override
    public void run() {
        try {

            String architecture = MetadataHelper.getArchitectureFromMetadata(settings.getProjectPath());
            CommandHandlerFactory factory = new CommandHandlerFactory();
            GenerateCrudCommandHandler handler = factory.createGenerateHandler(architecture);

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

            SubmissionPublisher<BaseResponse> publisher = new SubmissionPublisher<>();
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
