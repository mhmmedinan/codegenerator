<#-- Template for a Create Command class -->
package com.${ projectName?lower_case }.application.features.${string("camelcase", pluralEntityName)}.commands.create;

import an.awesome.pipelinr.Command;
import jakarta.persistence.Column;
import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Create${entity.name?cap_first}Command implements Command<Created${entity.name?cap_first}Response> {

    <#list entity.properties as propertyItem>
    private ${propertyItem.type} ${propertyItem.name?camel_case};
    </#list>
}
