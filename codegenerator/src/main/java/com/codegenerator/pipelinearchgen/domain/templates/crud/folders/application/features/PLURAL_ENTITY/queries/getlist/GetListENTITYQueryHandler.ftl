<#-- Template for a Get List Query Handler class -->
package com.${projectName?lower_case}.application.features.${string("camelcase", pluralEntityName)}.queries.getlist;

import an.awesome.pipelinr.Command;
import com.${projectName?lower_case}.application.features.${string("camelcase", pluralEntityName)}.mappers.${entity.name?cap_first}Mapper;
import com.${projectName?lower_case}.domain.entities.${entity.name?cap_first};
import com.${projectName?lower_case}.persistence.repositories.${entity.name?cap_first}Repository;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class GetList${entity.name?cap_first}QueryHandler implements Command.Handler<GetList${entity.name?cap_first}Query, List<GetList${entity.name?cap_first}Response>> {
    private final ${entity.name?cap_first}Repository ${entity.name?camel_case}Repository;

    public GetList${entity.name?cap_first}QueryHandler(${entity.name?cap_first}Repository ${entity.name?camel_case}Repository) {
        this.${entity.name?camel_case}Repository = ${entity.name?camel_case}Repository;
    }

    @Override
    public List<GetList${entity.name?cap_first}Response> handle(GetList${entity.name?cap_first}Query getList${entity.name?cap_first}Query) {
        List<${entity.name?cap_first}> ${entity.name?camel_case}List = ${entity.name?camel_case}Repository.findAll();
        List<GetList${entity.name?cap_first}Response> responses = ${entity.name?cap_first}Mapper.INSTANCE.getList${entity.name?cap_first}ResponseFrom${entity.name?cap_first}s(${entity.name?camel_case}List);
        return responses;
    }
}
