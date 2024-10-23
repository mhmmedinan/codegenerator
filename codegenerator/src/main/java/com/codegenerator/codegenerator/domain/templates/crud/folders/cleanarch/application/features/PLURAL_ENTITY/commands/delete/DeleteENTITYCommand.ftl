<#-- Template for a Delete Command class -->
package com.${projectName?lower_case}.application.features.${string("camelcase", pluralEntityName)}.commands.delete;

import an.awesome.pipelinr.Command;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Delete${entity.name?cap_first}Command implements Command<Deleted${entity.name?cap_first}Response> {
    private ${entity.idType} id;
}
