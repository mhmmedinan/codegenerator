<#-- Template for a Get List Query Response class -->
package com.${projectName?lower_case}.application.features.${string("camelcase", pluralEntityName)}.queries.getlist;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetList${entity.name?cap_first}Response {

    <#list entity.properties as propertyItem>
    private ${propertyItem.type} ${string("camelcase", propertyItem.name)};
    </#list>
}
