package com.codegenerator.pipelinearchgen.domain.valueobjects;

import com.codegenerator.core.codegen.templateengine.TemplateData;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CrudTemplateData implements TemplateData {
    private Entity entity;
    private String projectName;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }
}

