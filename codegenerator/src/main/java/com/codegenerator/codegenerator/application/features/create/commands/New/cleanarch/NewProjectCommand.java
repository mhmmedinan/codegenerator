package com.codegenerator.codegenerator.application.features.create.commands.New.cleanarch;


import com.codegenerator.codegenerator.domain.valueobjects.NewProjectData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class NewProjectCommand {
    private NewProjectData newProjectData;
}