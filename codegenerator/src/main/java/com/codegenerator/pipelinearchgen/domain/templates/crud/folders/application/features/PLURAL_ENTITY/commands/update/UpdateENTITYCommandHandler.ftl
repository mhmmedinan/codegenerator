<#-- Template for an Update Command Handler class -->
package com.${projectName?lower_case}.application.features.${pluralEntityName}.commands.update;

import an.awesome.pipelinr.Command;
import com.${projectName?lower_case}.application.features.${pluralEntityName}.mappers.${entity.name?cap_first}Mapper;
import com.${projectName?lower_case}.domain.entities.${entity.name?cap_first};
import com.${projectName?lower_case}.persistence.repositories.${entity.name?cap_first}Repository;
import org.springframework.stereotype.Component;

@Component
public class Update${entity.name?cap_first}CommandHandler implements Command.Handler<Update${entity.name?cap_first}Command, Updated${entity.name?cap_first}Response> {
    private final ${entity.name?cap_first}Repository ${entity.name?lower_case}Repository;

    public Update${entity.name?cap_first}CommandHandler(${entity.name?cap_first}Repository ${entity.name?lower_case}Repository) {
        this.${entity.name?lower_case}Repository = ${entity.name?lower_case}Repository;
    }

    @Override
    public Updated${entity.name?cap_first}Response handle(Update${entity.name?cap_first}Command update${entity.name?cap_first}Command) {
        ${entity.name?cap_first} existingEntity = ${entity.name?lower_case}Repository.findById(update${entity.name?cap_first}Command.getId()).orElseThrow(() -> new RuntimeException("Entity not found"));
        ${entity.name?cap_first} updatedEntity = ${entity.name?lower_case}Repository.save(existingEntity);
        return ${entity.name?cap_first}Mapper.INSTANCE.updated${entity.name?cap_first}ResponseFrom${entity.name?cap_first}(updatedEntity);
    }
}
