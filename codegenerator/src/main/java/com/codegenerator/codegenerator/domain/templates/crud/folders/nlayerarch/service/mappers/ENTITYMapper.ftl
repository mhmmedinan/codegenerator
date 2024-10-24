<#-- Template for a Mapper interface -->
package com.${projectName?lower_case}.service.mappers;

import com.${projectName?lower_case}.service.dtos.requests.${string("camelcase", pluralEntityName)}.*;
import com.${projectName?lower_case}.service.dtos.responses.${string("camelcase", pluralEntityName)}.*;
import com.${projectName?lower_case}.entity.${entity.name?cap_first};
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface ${entity.name?cap_first}Mapper {
    ${entity.name?cap_first}Mapper INSTANCE = Mappers.getMapper(${entity.name?cap_first}Mapper.class);

    ${entity.name?cap_first} ${string("camelcase", entity.name)}FromCreate${entity.name?cap_first}Request(Create${entity.name?cap_first}Request create${entity.name?cap_first}Request);
    Created${entity.name?cap_first}Response created${entity.name?cap_first}ResponseFrom${entity.name?cap_first}(${entity.name?cap_first} ${string("camelcase", entity.name)});
    Deleted${entity.name?cap_first}Response deleted${entity.name?cap_first}ResponseFrom${entity.name?cap_first}(${entity.name?cap_first} ${string("camelcase", entity.name)});
    Updated${entity.name?cap_first}Response updated${entity.name?cap_first}ResponseFrom${entity.name?cap_first}(${entity.name?cap_first} ${string("camelcase", entity.name)});
    GetList${entity.name?cap_first}Response ${string("camelcase", entity.name)}ToGetList${entity.name?cap_first}Response(${entity.name?cap_first} ${string("camelcase", entity.name)});
    Get${entity.name?cap_first}Response ${string("camelcase", entity.name)}ToGet${entity.name?cap_first}Response(${entity.name?cap_first} ${string("camelcase", entity.name)});

    default List<GetList${entity.name?cap_first}Response> getList${entity.name?cap_first}ResponseFrom${entity.name?cap_first}s(List<${entity.name?cap_first}> ${string("camelcase", entity.name)}s) {
        return ${string("camelcase", entity.name)}s.stream()
                .map(this::${string("camelcase", entity.name)}ToGetList${entity.name?cap_first}Response)
                .collect(Collectors.toList());
    }
}
