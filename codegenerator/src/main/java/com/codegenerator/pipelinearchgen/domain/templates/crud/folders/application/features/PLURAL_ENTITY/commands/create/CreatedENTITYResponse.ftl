package com.${ projectName?lower_case }.application.features.${string("camelcase",pluralEntityName)}.commands.create;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Created${entity.name}Response {

      private ${entity.idType} id;

      <#list entity.properties as propertyItem>
      private ${propertyItem.type} ${propertyItem.name?camel_case};
      </#list>
}
