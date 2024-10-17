<#-- Template for a Delete Command Handler class -->
package com.${projectName?lower_case}.application.features.${pluralEntityName}.commands.delete;

import an.awesome.pipelinr.Command;
import com.${projectName?lower_case}.application.features.${pluralEntityName}.mappers.${entity.name?cap_first}Mapper;
import com.${projectName?lower_case}.domain.entities.${entity.name?cap_first};
import com.${projectName?lower_case}.persistence.repositories.${entity.name?cap_first}Repository;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class Delete${entity.name?cap_first}CommandHandler implements Command.Handler<Delete${entity.name?cap_first}Command, Deleted${entity.name?cap_first}Response> {
    private final ${entity.name?cap_first}Repository ${entity.name?lower_case}Repository;

    public Delete${entity.name?cap_first}CommandHandler(${entity.name?cap_first}Repository ${entity.name?lower_case}Repository) {
        this.${entity.name?lower_case}Repository = ${entity.name?lower_case}Repository;
    }

    @Override
    public Deleted${entity.name?cap_first}Response handle(Delete${entity.name?cap_first}Command delete${entity.name?cap_first}Command) {
        ${entity.name?cap_first} ${entity.name?lower_case} = ${entity.name?lower_case}Repository.findById(delete${entity.name?cap_first}Command.getId()).get();
        ${entity.name?lower_case}.setDeletedDate(LocalDateTime.now());
        ${entity.name?cap_first} deleted${entity.name?cap_first} = ${entity.name?lower_case}Repository.save(${entity.name?lower_case});
        Deleted${entity.name?cap_first}Response deleted${entity.name?cap_first}Response =
                ${entity.name?cap_first}Mapper.INSTANCE.deleted${entity.name?cap_first}ResponseFrom${entity.name?cap_first}(deleted${entity.name?cap_first});
        return deleted${entity.name?cap_first}Response;
    }
}
