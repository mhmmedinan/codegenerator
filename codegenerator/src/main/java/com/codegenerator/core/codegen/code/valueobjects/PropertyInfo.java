package com.codegenerator.core.codegen.code.valueobjects;

/**
 * A class representing property information, including its name, type, access modifier, and namespace.
 */
public class PropertyInfo {
    private String name;
    private String type;
    private String accessModifier;
    private String nameSpace; // Optional field, can be null

    // Default constructor
    public PropertyInfo() {
    }

    // Parameterized constructor
    public PropertyInfo(String name, String type, String accessModifier, String nameSpace) {
        this.name = name;
        this.type = type;
        this.accessModifier = accessModifier;
        this.nameSpace = nameSpace;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAccessModifier() {
        return accessModifier;
    }

    public void setAccessModifier(String accessModifier) {
        this.accessModifier = accessModifier;
    }

    public String getNameSpace() {
        return nameSpace;
    }

    public void setNameSpace(String nameSpace) {
        this.nameSpace = nameSpace;
    }
}

