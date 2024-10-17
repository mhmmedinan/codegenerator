package com.codegenerator.pipelinearchgen.cli.commands.New;

import com.codegenerator.pipelinearchgen.application.features.create.commands.New.CreateNewProjectCommand;
import picocli.CommandLine;

@CommandLine.Command(name = "new", description = "Create a new project")
public class NewProjectCommand implements Runnable {

    @picocli.CommandLine.Parameters(paramLabel = "<projectName>", description = "Name of the project")
    private String projectName;

    @Override
    public void run() {
        try {
            CreateNewProjectCommand.createProjectStructure(projectName);
            System.out.println("Project " + projectName + " created successfully!");
        } catch (Exception e) {
            System.err.println("Failed to create project: " + e.getMessage());
        }
    }
}
