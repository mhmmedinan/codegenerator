<#-- Template for a Constants class -->
package com.${projectName?lower_case}.application.features.${pluralEntityName}.constants;

public class Messages {
    public static class BusinessErrors {
        public static final String ${entity.name?cap_first}NotExists = "${entity.name?lower_case}NotExists";
    }
}
