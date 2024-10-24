package com.${ projectName?lower_case }.application.features.${string("camelcase",pluralEntityName)}.commands.create;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Created${entity.name?cap_first}Response {
      <#list entity.properties as propertyItem>
      <#if propertyItem.annotations?has_content && propertyItem.annotations?seq_contains("@ManyToOne")>
      private ${entity.idType} ${string("camelcase", propertyItem.name)}Id;
      <#else>
       private ${propertyItem.type} ${string("camelcase", propertyItem.name)};
       </#if>
      </#list>
}
