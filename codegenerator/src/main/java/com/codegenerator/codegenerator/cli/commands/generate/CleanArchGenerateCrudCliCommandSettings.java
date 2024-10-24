package com.codegenerator.codegenerator.cli.commands.generate;

import com.codegenerator.codegenerator.cli.commands.common.CrudCliCommandSettings;
import com.codegenerator.core.codegen.code.valueobjects.PropertyInfo;
import lombok.Getter;
import lombok.Setter;
import picocli.CommandLine.Option;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Stream;


@Getter
@Setter
public class CleanArchGenerateCrudCliCommandSettings extends CrudCliCommandSettings {



    public String getProjectPath() {
        return getProjectName() != null
                ? Paths.get(System.getProperty("user.dir"), "src", "main", "java", "com", getProjectName() .toLowerCase()).toString()
                : System.getProperty("user.dir");

    }

    @Override
    public Path getEntityPath() {
        return Paths.get(getProjectPath(), "domain", "entities", getEntityName() + ".java");
    }

    @Override
    public String getEntityIdType(List<PropertyInfo> entityProperties) {
        Optional<PropertyInfo> idProperty = entityProperties.stream()
                .filter(property -> property.getName().equals("id"))
                .findFirst();
        return idProperty.map(PropertyInfo::getType).orElse("Long");
    }


    public void checkProjectName() {
        if (getProjectName()  != null) {
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
            setProjectName(projects[0].getName());
            return;
        }

        setProjectName(selectProjectFromPrompt(projects));
    }


    public void checkEntityArgument() {
        if (getEntityName() != null) {
            System.out.println("Selected entity is " + getEntityName());
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
        setEntityName(entities[selectedNumber - 1].getName().replace(".java", ""));
        System.out.println("Selected entity is " + getEntityName());
    }


    private String selectProjectFromPrompt(File[] projects) {
        return projects[0].getName();
    }
}

