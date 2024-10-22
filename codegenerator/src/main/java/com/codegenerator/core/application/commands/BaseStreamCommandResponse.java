package com.codegenerator.core.application.commands;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseStreamCommandResponse {

    private String currentStatusMessage;
    private String outputMessage;
    private String lastOperationMessage;

    protected BaseStreamCommandResponse() {
        this.currentStatusMessage = "";
    }
}