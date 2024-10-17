package com.codegenerator.pipelinearchgen.domain.valueobjects;

import com.codegenerator.core.codegen.templateengine.TemplateData;

public class QueryTemplateData implements TemplateData {
    private String queryName;
    private String featureName;
    private boolean isSecuredOperationUsed;

    // Getter ve Setter metodları
    public String getQueryName() {
        return queryName;
    }

    public void setQueryName(String queryName) {
        this.queryName = queryName;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public boolean isSecuredOperationUsed() {
        return isSecuredOperationUsed;
    }

    public void setSecuredOperationUsed(boolean securedOperationUsed) {
        isSecuredOperationUsed = securedOperationUsed;
    }
}

