package com.codegenerator.core.codegen.code.valueobjects;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PropertyInfo {
    private String name;
    private String type;
    private String accessModifier;
    private String nameSpace;
    private List<String> annotations;
}

