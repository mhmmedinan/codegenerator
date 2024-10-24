package com.${ projectName?lower_case }.service.dtos.responses.${string("camelcase", pluralEntityName)};

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Updated${entity.name?cap_first}Response {
<#list entity.properties as propertyItem>
private ${propertyItem.type} ${string("camelcase",propertyItem.name)};
</#list>
}