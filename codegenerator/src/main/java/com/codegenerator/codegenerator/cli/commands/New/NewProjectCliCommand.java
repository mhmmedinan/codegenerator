package com.codegenerator.codegenerator.cli.commands.New;

import com.codegenerator.codegenerator.application.features.create.commands.New.cleanarch.NewProjectCommand;
import com.codegenerator.codegenerator.application.features.create.commands.New.cleanarch.NewProjectCommandHandler;
import com.codegenerator.codegenerator.application.features.create.commands.New.cleanarch.NewProjectResponse;
import com.codegenerator.codegenerator.application.features.create.commands.New.cleanarch.NewProjectSubscriber;
import com.codegenerator.codegenerator.domain.valueobjects.NewProjectData;
import com.codegenerator.core.codegen.templateengine.TemplateEngineImpl;
import com.codegenerator.core.codegen.templateengine.freemarker.FreemarkerTemplateRenderer;
import picocli.CommandLine;

import java.util.concurrent.SubmissionPublisher;

@CommandLine.Command(name = "new", description = "Create a new project")
public class NewProjectCliCommand implements Runnable {

    private final NewProjectCommandHandler newProjectCommandHandler;

    @picocli.CommandLine.Parameters(paramLabel = "<projectName>", description = "Name of the project")
    private String projectName;

    public NewProjectCliCommand(){
        this.newProjectCommandHandler = new NewProjectCommandHandler(new TemplateEngineImpl(new FreemarkerTemplateRenderer()));
    }



    @Override
    public void run() {
        try {
            NewProjectCommand newProjectCommand = new NewProjectCommand(new NewProjectData(projectName));
            SubmissionPublisher<NewProjectResponse> publisher = new SubmissionPublisher<>();
            NewProjectSubscriber subscriber = new NewProjectSubscriber();

            publisher.subscribe(subscriber);
            newProjectCommandHandler.subscribe(subscriber);
            newProjectCommandHandler.handle(newProjectCommand);
            publisher.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Failed to create project: " + e.getMessage());
        }
    }
}
