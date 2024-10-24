package com.codegenerator.codegenerator.cli.commands.common;

import com.codegenerator.core.codegen.code.valueobjects.PropertyInfo;
import lombok.Getter;
import lombok.Setter;
import picocli.CommandLine;

import java.nio.file.Path;
import java.util.List;

@Getter
@Setter
public abstract class CrudCliCommandSettings {
    @CommandLine.Option(names = { "-a", "--architecture" }, description = "Architecture type", required = false)
    private String architecture;

    @CommandLine.Option(names = { "-p", "--project" }, description = "Name of the project", required = false)
    private String projectName;

    @CommandLine.Option(names = { "-e", "--entity" }, description = "Name of the entity", required = false)
    private String entityName;

    public abstract String getProjectPath();
    public abstract Path getEntityPath();
    public abstract String getEntityIdType(List<PropertyInfo> entityProperties);

    public abstract void checkProjectName();

    public abstract void checkEntityArgument();
}
