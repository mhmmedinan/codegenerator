package com.codegenerator.pipelinearchgen.domain.valueobjects;

import com.codegenerator.core.codegen.templateengine.TemplateData;

public class NewProjectData implements TemplateData {
    private String projectName;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
