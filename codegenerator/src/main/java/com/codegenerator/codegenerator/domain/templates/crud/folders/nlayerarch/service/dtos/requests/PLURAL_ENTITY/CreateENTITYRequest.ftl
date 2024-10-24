package com.${ projectName?lower_case }.service.dtos.requests.${string("camelcase", pluralEntityName)};

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Create${entity.name?cap_first}Request {
<#list entity.properties as propertyItem>
<#if propertyItem.name != "id">
private ${propertyItem.type} ${string("camelcase",propertyItem.name)};
</#if>
</#list>
}