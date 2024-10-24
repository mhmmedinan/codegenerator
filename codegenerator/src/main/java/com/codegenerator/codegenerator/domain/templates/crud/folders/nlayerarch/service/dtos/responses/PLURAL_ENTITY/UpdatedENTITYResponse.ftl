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
<#if propertyItem.annotations?has_content && propertyItem.annotations?seq_contains("@ManyToOne")>
private ${entity.idType} ${string("camelcase", propertyItem.name)}Id;
<#else>
private ${propertyItem.type} ${string("camelcase", propertyItem.name)};
</#if>
</#list>
}