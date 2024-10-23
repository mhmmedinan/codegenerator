package com.codegenerator.codegenerator.cli.commands.New;

import com.codegenerator.codegenerator.application.features.create.commands.New.NewProjectCommand;
import com.codegenerator.codegenerator.application.features.create.commands.New.NewProjectCommandHandler;
import com.codegenerator.codegenerator.application.features.create.commands.New.NewProjectResponse;
import com.codegenerator.codegenerator.application.features.create.commands.New.NewProjectSubscriber;
import com.codegenerator.codegenerator.domain.valueobjects.NewProjectData;
import picocli.CommandLine;
import java.nio.file.Paths;
import java.util.concurrent.SubmissionPublisher;

@CommandLine.Command(name = "new", description = "Create a new project")
public class NewProjectCliCommand implements Runnable {

    private final NewProjectCommandHandler newProjectCommandHandler;

    public NewProjectCliCommand(){
        this.newProjectCommandHandler = new NewProjectCommandHandler();
    }

    @picocli.CommandLine.Parameters(paramLabel = "<projectName>", description = "Name of the project")
    private String projectName;

    @Override
    public void run() {
        try {
            NewProjectCommand newProjectCommand = new NewProjectCommand(getProjectPath(),new NewProjectData(projectName));
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

    public String getProjectPath() {
        return projectName != null
                ? Paths.get(System.getProperty("user.dir"), "src", "main", "java", "com", projectName.toLowerCase()).toString()
                : System.getProperty("user.dir");

    }


}
