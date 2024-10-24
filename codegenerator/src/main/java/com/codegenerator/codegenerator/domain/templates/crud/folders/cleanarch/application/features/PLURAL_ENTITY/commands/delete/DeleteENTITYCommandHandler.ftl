<#-- Template for a Delete Command Handler class -->
package com.${projectName?lower_case}.application.features.${string("camelcase", pluralEntityName)}.commands.delete;

import an.awesome.pipelinr.Command;
import com.${projectName?lower_case}.application.features.${string("camelcase", pluralEntityName)}.mappers.${entity.name?cap_first}Mapper;
import com.${projectName?lower_case}.domain.entities.${entity.name?cap_first};
import com.${projectName?lower_case}.persistence.repositories.${entity.name?cap_first}Repository;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import io.github.mhmmedinan.core_crosscuttingconcerns.exceptions.types.BusinessException;
import lombok.*;

@Component
@RequiredArgsConstructor
public class Delete${entity.name?cap_first}CommandHandler implements Command.Handler<Delete${entity.name?cap_first}Command, Deleted${entity.name?cap_first}Response> {
    private final ${entity.name?cap_first}Repository ${string("camelcase", entity.name)}Repository;


    @Override
    public Deleted${entity.name?cap_first}Response handle(Delete${entity.name?cap_first}Command delete${entity.name?cap_first}Command) {
        ${entity.name?cap_first} existing${entity.name?cap_first} = ${string("camelcase", entity.name)}Repository.findById(delete${entity.name?cap_first}Command.getId()).orElseThrow(() -> new BusinessException("${entity.name?cap_first} not found"));
        existing${entity.name?cap_first}.setDeletedDate(LocalDateTime.now());
        ${entity.name?cap_first} deleted${entity.name?cap_first} = ${string("camelcase", entity.name)}Repository.save(existing${entity.name?cap_first});
        Deleted${entity.name?cap_first}Response deleted${entity.name?cap_first}Response =
                ${entity.name?cap_first}Mapper.INSTANCE.deleted${entity.name?cap_first}ResponseFrom${entity.name?cap_first}(deleted${entity.name?cap_first});
        return deleted${entity.name?cap_first}Response;
    }
}
