package com.codegenerator.core.application.commands;

public abstract class BaseStreamCommandResponse {

    private String currentStatusMessage;
    private String outputMessage;
    private String lastOperationMessage;

    protected BaseStreamCommandResponse() {
        this.currentStatusMessage = "";
    }

    public String getCurrentStatusMessage() {
        return currentStatusMessage;
    }

    public void setCurrentStatusMessage(String currentStatusMessage) {
        this.currentStatusMessage = currentStatusMessage;
    }

    public String getOutputMessage() {
        return outputMessage;
    }

    public void setOutputMessage(String outputMessage) {
        this.outputMessage = outputMessage;
    }

    public String getLastOperationMessage() {
        return lastOperationMessage;
    }

    public void setLastOperationMessage(String lastOperationMessage) {
        this.lastOperationMessage = lastOperationMessage;
    }
}