package com.codegenerator.pipelinearchgen.application.features.generate.commands.crud;

import com.codegenerator.pipelinearchgen.domain.valueobjects.CrudTemplateData;
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
