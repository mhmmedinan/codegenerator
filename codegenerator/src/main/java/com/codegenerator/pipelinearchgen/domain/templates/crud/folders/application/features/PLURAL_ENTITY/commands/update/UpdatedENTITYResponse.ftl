<#-- Template for an Updated Response class -->
package com.${projectName?lower_case}.application.features.${string("camelcase", pluralEntityName)}.commands.update;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Updated${entity.name?cap_first}Response {
    private ${entity.idType} id;

    <#list entity.properties as propertyItem>
    private ${propertyItem.type} ${propertyItem.name?camel_case};
    </#list>
}
