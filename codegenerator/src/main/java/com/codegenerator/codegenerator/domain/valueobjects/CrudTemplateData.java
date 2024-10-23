package com.codegenerator.codegenerator.domain.valueobjects;

import com.codegenerator.core.codegen.templateengine.TemplateData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class CrudTemplateData implements TemplateData {
    private Entity entity;
    private String projectName;

}

