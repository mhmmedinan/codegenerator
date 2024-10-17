package com.codegenerator.pipelinearchgen.domain.valueobjects;

import com.codegenerator.core.codegen.code.valueobjects.PropertyInfo;
import lombok.AllArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class Entity {
    private String name;
    private String idType;
    private List<PropertyInfo> properties;

    public Entity() {
        properties = new ArrayList<>();
    }

    // Getter ve Setter metotları
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public List<PropertyInfo> getProperties() {
        return properties;
    }

    public void setProperties(List<PropertyInfo> properties) {
        this.properties = properties;
    }
}
