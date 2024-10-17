package com.codegenerator.pipelinearchgen.domain.valueobjects;

import com.codegenerator.core.codegen.templateengine.TemplateData;

public class CommandTemplateData implements TemplateData {
    private final String commandName;
    private final String featureName;
    private boolean isSecuredOperationUsed;
    private final String endPointMethod;

    public CommandTemplateData(String commandName, String featureName, String endPointMethod) {
        this.commandName = commandName;
        this.featureName = featureName;
        this.endPointMethod = endPointMethod;
    }

    public String getCommandName() {
        return commandName;
    }

    public String getFeatureName() {
        return featureName;
    }



    public boolean isSecuredOperationUsed() {
        return isSecuredOperationUsed;
    }

    public void setSecuredOperationUsed(boolean securedOperationUsed) {
        isSecuredOperationUsed = securedOperationUsed;
    }

    public String getEndPointMethod() {
        return endPointMethod;
    }
}

