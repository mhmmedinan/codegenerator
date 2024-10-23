package com.codegenerator.codegenerator.cli.commands.New;

import com.codegenerator.codegenerator.application.features.common.New.NewProjectCommand;
import com.codegenerator.codegenerator.application.features.common.New.NewProjectCommandHandler;
import com.codegenerator.codegenerator.application.features.common.New.NewProjectSubscriber;
import com.codegenerator.codegenerator.application.features.common.responses.BaseResponse;
import com.codegenerator.codegenerator.cli.commands.factory.CommandHandlerFactory;
import com.codegenerator.codegenerator.domain.valueobjects.NewProjectData;
import picocli.CommandLine;

import java.util.concurrent.SubmissionPublisher;

@CommandLine.Command(name = "new", description = "Create a new project")
public class NewProjectCliCommand implements Runnable {


    @picocli.CommandLine.Parameters(paramLabel = "<projectName>", description = "Name of the project")
    private String projectName;

    @CommandLine.Option(names = {"-a", "--architecture"}, description = "Specify the architecture type (e.g., cleanarch, nlayer).", required = false)
    private String architecture = "cleanarch";

    @Override
    public void run() {
        try {
            CommandHandlerFactory factory = new CommandHandlerFactory();
            NewProjectCommandHandler handler = factory.createNewHandler(architecture);

            NewProjectCommand newProjectCommand = new NewProjectCommand(architecture,new NewProjectData(projectName));

            SubmissionPublisher<BaseResponse> publisher = new SubmissionPublisher<>();
            NewProjectSubscriber subscriber = new NewProjectSubscriber();

            publisher.subscribe(subscriber);
            handler.subscribe(subscriber);
            handler.handle(newProjectCommand);

            publisher.close();
        } catch (Exception e) {
            System.err.println("Failed to create project: " + e.getMessage());
        }
    }

}
