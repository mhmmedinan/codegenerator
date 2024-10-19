package com.codegenerator.pipelinearchgen.domain.valueobjects;

import com.codegenerator.core.codegen.templateengine.TemplateData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class NewProjectData implements TemplateData {
    private String projectName;
}
