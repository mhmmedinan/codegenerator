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

    <#list entity.properties as propertyItem>
    <#if propertyItem.annotations?has_content && propertyItem.annotations?seq_contains("@ManyToOne")>
    private ${entity.idType} ${string("camelcase", propertyItem.name)}Id;
    <#else>
    private ${propertyItem.type} ${string("camelcase", propertyItem.name)};
    </#if>
    </#list>
}
