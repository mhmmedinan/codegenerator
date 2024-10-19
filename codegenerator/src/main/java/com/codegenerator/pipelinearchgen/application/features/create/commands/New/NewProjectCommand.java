package com.codegenerator.pipelinearchgen.application.features.create.commands.New;


import com.codegenerator.pipelinearchgen.domain.valueobjects.NewProjectData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class NewProjectCommand {
    private String basePath;
    private NewProjectData newProjectData;
}
