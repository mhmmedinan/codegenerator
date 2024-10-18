<#-- Template for a Deleted Response class -->
package com.${projectName?lower_case}.application.features.${string("camelcase", pluralEntityName)}.commands.delete;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Deleted${entity.name?cap_first}Response {
    private ${entity.idType} id;
}
