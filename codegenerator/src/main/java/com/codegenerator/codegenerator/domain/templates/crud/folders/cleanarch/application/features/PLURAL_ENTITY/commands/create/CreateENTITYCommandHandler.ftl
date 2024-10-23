<#-- Template for a Create Command Handler class -->
package com.${ projectName?lower_case }.application.features.${string("camelcase", pluralEntityName)}.commands.create;

import an.awesome.pipelinr.Command;
import com.${projectName?lower_case}.application.features.${string("camelcase", pluralEntityName)}.mappers.${entity.name?cap_first}Mapper;
import com.${projectName?lower_case}.application.features.${string("camelcase", pluralEntityName)}.rules.${entity.name?cap_first}BusinessRules;
import com.${projectName?lower_case}.domain.entities.${entity.name?cap_first};
import com.${projectName?lower_case}.persistence.repositories.${entity.name?cap_first}Repository;
import org.springframework.stereotype.Component;
import lombok.*;

@Component
@RequiredArgsConstructor
public class Create${entity.name?cap_first}CommandHandler implements Command.Handler<Create${entity.name?cap_first}Command, Created${entity.name?cap_first}Response> {
    private final ${entity.name?cap_first}Repository ${string("camelcase", entity.name)}Repository;
    private final ${entity.name?cap_first}BusinessRules ${string("camelcase", entity.name)}BusinessRules;



    @Override
    public Created${entity.name?cap_first}Response handle(Create${entity.name?cap_first}Command create${entity.name?cap_first}Command) {
        ${entity.name?cap_first} ${entity.name?lower_case} = ${entity.name?cap_first}Mapper.INSTANCE
                .${string("camelcase",entity.name)}FromCreate${entity.name?cap_first}Command(create${entity.name?cap_first}Command);
        ${entity.name?cap_first} created${entity.name?cap_first} = ${string("camelcase", entity.name)}Repository.save(${entity.name?lower_case});
        return ${entity.name?cap_first}Mapper.INSTANCE.created${entity.name?cap_first}ResponseFrom${entity.name?cap_first}(created${entity.name?cap_first});
    }
}
