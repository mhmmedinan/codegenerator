package com.codegenerator.codegenerator.application.features.generate.commands.crud.cleanarch;

import com.codegenerator.codegenerator.domain.valueobjects.CrudTemplateData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GenerateCrudCommand {
    private String projectPath;
    private CrudTemplateData crudTemplateData;
}
