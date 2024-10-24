package com.codegenerator.codegenerator.cli.commands.generate;

import com.codegenerator.codegenerator.application.features.common.generate.GenerateCrudCommandHandler;
import com.codegenerator.codegenerator.application.features.common.responses.BaseResponse;
import com.codegenerator.codegenerator.cli.commands.common.CrudCliCommandSettings;
import com.codegenerator.codegenerator.cli.commands.factory.CliCommandSettingsFactory;
import com.codegenerator.codegenerator.cli.commands.factory.CommandHandlerFactory;
import com.codegenerator.core.codegen.code.JavaCodeReader;
import com.codegenerator.core.codegen.code.valueobjects.PropertyInfo;
import com.codegenerator.core.codegen.helpers.MetadataHelper;
import com.codegenerator.codegenerator.application.features.common.generate.GenerateCrudCommand;
import com.codegenerator.codegenerator.application.features.common.generate.GenerateCrudSubscriber;
import com.codegenerator.codegenerator.domain.valueobjects.CrudTemplateData;
import com.codegenerator.codegenerator.domain.valueobjects.Entity;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.SubmissionPublisher;

@Command(name = "generate-crud", description = "Generate CRUD operations")
public class GenerateCrudCliCommand implements Runnable {

    private final CommandHandlerFactory commandHandlerFactory;

    public GenerateCrudCliCommand() {
        this.commandHandlerFactory = new CommandHandlerFactory();
    }


    @Override
    public void run() {
        try {
            String architecture = MetadataHelper.getArchitectureFromMetadata(System.getProperty("user.dir"));
            CrudCliCommandSettings settings = CliCommandSettingsFactory.createSettings(architecture);
            GenerateCrudCommandHandler handler = commandHandlerFactory.createGenerateHandler(architecture);

            settings.checkProjectName();
            settings.checkEntityArgument();

            Path entityPath = settings.getEntityPath();
            List<PropertyInfo> entityProperties = JavaCodeReader.readClassPropertiesAsync(entityPath.toString(), settings.getProjectPath()).get();
            String entityIdType = settings.getEntityIdType(entityProperties);

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
