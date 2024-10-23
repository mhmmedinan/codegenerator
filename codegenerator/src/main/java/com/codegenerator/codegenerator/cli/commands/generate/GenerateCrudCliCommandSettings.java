package com.codegenerator.codegenerator.cli.commands.generate;

import lombok.Getter;
import lombok.Setter;
import picocli.CommandLine.Option;
import java.io.File;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;


@Getter
@Setter
public class GenerateCrudCliCommandSettings {

    @Option(names = { "-p", "--project" }, description = "Name of the project")
    private String projectName;

    @Option(names = { "-e", "--entity" }, description = "Name of the entity")
    private String entityName;


    public String getProjectPath() {
        return projectName != null
                ? Paths.get(System.getProperty("user.dir"), "src", "main", "java", "com", projectName.toLowerCase()).toString()
                : System.getProperty("user.dir");

    }


    public void checkProjectName() {
        if (projectName != null) {
            if (!new File(getProjectPath()).exists()) {
                System.err.println("Project not found in \"" + getProjectPath() + "\".");
            }
            return;
        }

        String[] layerFolders = { "application", "domain", "persistence", "webapi" };
        if (Stream.of(layerFolders).allMatch(folder -> new File(System.getProperty("user.dir") + "/" + folder).exists())) {
            return;
        }

        File[] projects = new File(System.getProperty("user.dir") + "/src/main/java/com").listFiles(File::isDirectory);
        if (projects == null || projects.length == 0) {
            System.err.println("No projects found");
        }
        if (projects.length == 1) {
            projectName = projects[0].getName();
            return;
        }

        projectName = selectProjectFromPrompt(projects);
    }


    public void checkEntityArgument() {
        if (entityName != null) {
            System.out.println("Selected entity is " + entityName);
            return;
        }

        File[] entities = new File(Paths.get(getProjectPath(), "domain", "entities").toString()).listFiles();
        if (entities == null || entities.length == 0) {
            System.err.println("No entities found in \"" + Paths.get(getProjectPath(), "domain", "entities").toString() + "\"");
        }

        System.out.println("Available entities:");
        for (int i = 0; i < entities.length; i++) {
            System.out.println((i + 1) + ": " + entities[i].getName().replace(".java", ""));
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Select an entity by number: ");
        int selectedNumber = scanner.nextInt();
        if (selectedNumber < 1 || selectedNumber > entities.length) {
            System.err.println("Invalid selection");
        }
        entityName = entities[selectedNumber - 1].getName().replace(".java", "");
        System.out.println("Selected entity is " + entityName);
    }


    private String selectProjectFromPrompt(File[] projects) {
        return projects[0].getName();
    }
}

