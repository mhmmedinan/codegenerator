<#-- Template for a Create Command class -->
package com.${ projectName?lower_case }.application.features.${string("camelcase", pluralEntityName)}.commands.create;

import an.awesome.pipelinr.Command;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Create${entity.name?cap_first}Command implements Command<Created${entity.name?cap_first}Response> {
    <#list entity.properties as propertyItem>
      <#if propertyItem.name != "id">
    private ${propertyItem.type} ${string("camelcase",propertyItem.name)};
      </#if>
    </#list>
}
