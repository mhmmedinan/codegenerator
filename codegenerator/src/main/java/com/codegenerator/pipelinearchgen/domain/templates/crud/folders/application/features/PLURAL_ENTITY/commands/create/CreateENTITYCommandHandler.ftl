<#-- Template for a Create Command Handler class -->
package com.${ projectName?lower_case }.application.features.${string("camelcase", pluralEntityName)}.commands.create;

import an.awesome.pipelinr.Command;
import com.${projectName?lower_case}.application.features.${string("camelcase", pluralEntityName)}.mappers.${entity.name?cap_first}Mapper;
import com.${projectName?lower_case}.application.features.${string("camelcase", pluralEntityName)}.rules.${entity.name?cap_first}BusinessRules;
import com.${projectName?lower_case}.domain.entities.${entity.name?cap_first};
import com.${projectName?lower_case}.persistence.repositories.${entity.name?cap_first}Repository;
import org.springframework.stereotype.Component;

@Component
public class Create${entity.name?cap_first}CommandHandler implements Command.Handler<Create${entity.name?cap_first}Command, Created${entity.name?cap_first}Response> {
    private final ${entity.name?cap_first}Repository ${entity.name?lower_case}Repository;
    private final ${entity.name?cap_first}BusinessRules ${entity.name?lower_case}BusinessRules;

    public Create${entity.name?cap_first}CommandHandler(${entity.name?cap_first}Repository ${entity.name?lower_case}Repository,
                                                      ${entity.name?cap_first}BusinessRules ${entity.name?lower_case}BusinessRules) {
        this.${entity.name?lower_case}Repository = ${entity.name?lower_case}Repository;
        this.${entity.name?lower_case}BusinessRules = ${entity.name?lower_case}BusinessRules;
    }

    @Override
    public Created${entity.name?cap_first}Response handle(Create${entity.name?cap_first}Command create${entity.name?cap_first}Command) {
        ${entity.name?cap_first} ${entity.name?lower_case} = ${entity.name?cap_first}Mapper.INSTANCE
                .${entity.name?camel_case}FromCreate${entity.name?cap_first}Command(create${entity.name?cap_first}Command);
        ${entity.name?cap_first} created${entity.name?cap_first} = ${entity.name?lower_case}Repository.save(${entity.name?lower_case});
        return ${entity.name?cap_first}Mapper.INSTANCE.created${entity.name?cap_first}ResponseFrom${entity.name?cap_first}(created${entity.name?lower_case});
    }
}
