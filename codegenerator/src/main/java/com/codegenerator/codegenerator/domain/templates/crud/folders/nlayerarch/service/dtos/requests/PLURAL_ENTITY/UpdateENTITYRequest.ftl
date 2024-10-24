package com.${ projectName?lower_case }.service.dtos.requests.${string("camelcase", pluralEntityName)};

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Update${entity.name?cap_first}Request {
<#list entity.properties as propertyItem>
private ${propertyItem.type} ${string("camelcase", propertyItem.name)};
</#list>
}