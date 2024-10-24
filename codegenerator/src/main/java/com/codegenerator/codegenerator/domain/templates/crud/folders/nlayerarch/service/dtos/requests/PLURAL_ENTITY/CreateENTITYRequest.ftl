package com.${ projectName?lower_case }.service.dtos.requests.${string("camelcase", pluralEntityName)};

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Create${entity.name?cap_first}Request {
<#list entity.properties as propertyItem>
<#if propertyItem.name != "id">
<#if propertyItem.annotations?has_content && propertyItem.annotations?seq_contains("@ManyToOne")>
private ${entity.idType} ${string("camelcase", propertyItem.name)}Id;
<#else>
private ${propertyItem.type} ${string("camelcase", propertyItem.name)};
</#if>
</#if>
</#list>
}