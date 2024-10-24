<#-- Template for an Update Command Handler class -->
package com.${projectName?lower_case}.application.features.${string("camelcase", pluralEntityName)}.commands.update;

import an.awesome.pipelinr.Command;
import com.${projectName?lower_case}.application.features.${string("camelcase", pluralEntityName)}.mappers.${entity.name?cap_first}Mapper;
import com.${projectName?lower_case}.domain.entities.${entity.name?cap_first};
import com.${projectName?lower_case}.persistence.repositories.${entity.name?cap_first}Repository;
import org.springframework.stereotype.Component;
import io.github.mhmmedinan.core_crosscuttingconcerns.exceptions.types.BusinessException;
import lombok.*;

@Component
@RequiredArgsConstructor
public class Update${entity.name?cap_first}CommandHandler implements Command.Handler<Update${entity.name?cap_first}Command, Updated${entity.name?cap_first}Response> {
    private final ${entity.name?cap_first}Repository ${string("camelcase", entity.name)}Repository;

    @Override
    public Updated${entity.name?cap_first}Response handle(Update${entity.name?cap_first}Command update${entity.name?cap_first}Command) {
        ${entity.name?cap_first} existing${entity.name?cap_first} = ${string("camelcase", entity.name)}Repository.findById(update${entity.name?cap_first}Command.getId()).orElseThrow(() -> new BusinessException("${entity.name?cap_first} not found"));
        ${entity.name?cap_first} updated${entity.name?cap_first} = ${string("camelcase", entity.name)}Repository.save(existing${entity.name?cap_first});
        return ${entity.name?cap_first}Mapper.INSTANCE.updated${entity.name?cap_first}ResponseFrom${entity.name?cap_first}(updated${entity.name?cap_first});
    }
}
