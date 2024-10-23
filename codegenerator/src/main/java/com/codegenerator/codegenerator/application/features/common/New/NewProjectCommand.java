package com.codegenerator.codegenerator.application.features.common.New;


import com.codegenerator.codegenerator.domain.valueobjects.NewProjectData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class NewProjectCommand {
    private String architecture;
    private NewProjectData newProjectData;
}
