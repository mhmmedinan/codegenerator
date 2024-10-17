<#-- Template for a Mapper interface -->
package com.${projectName?lower_case}.application.features.${pluralEntityName}.mappers;

import com.${projectName?lower_case}.application.features.${pluralEntityName}.commands.create.Create${entity.name?cap_first}Command;
import com.${projectName?lower_case}.application.features.${pluralEntityName}.commands.create.Created${entity.name?cap_first}Response;
import com.${projectName?lower_case}.application.features.${pluralEntityName}.commands.delete.Deleted${entity.name?cap_first}Response;
import com.${projectName?lower_case}.application.features.${pluralEntityName}.queries.getList.GetList${entity.name?cap_first}Response;
import com.${projectName?lower_case}.domain.entities.${entity.name?cap_first};
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface ${entity.name?cap_first}Mapper {
    ${entity.name?cap_first}Mapper INSTANCE = Mappers.getMapper(${entity.name?cap_first}Mapper.class);

    ${entity.name?cap_first} ${entity.name?camel_case}FromCreate${entity.name?cap_first}Command(Create${entity.name?cap_first}Command create${entity.name?cap_first}Command);
    Created${entity.name?cap_first}Response created${entity.name?cap_first}ResponseFrom${entity.name?cap_first}(${entity.name?cap_first} ${entity.name?camel_case});
    Deleted${entity.name?cap_first}Response deleted${entity.name?cap_first}ResponseFrom${entity.name?cap_first}(${entity.name?cap_first} ${entity.name?camel_case});
    GetList${entity.name?cap_first}Response ${entity.name?camel_case}ToGetList${entity.name?cap_first}Response(${entity.name?cap_first} ${entity.name?camel_case});

    default List<GetList${entity.name?cap_first}Response> getList${entity.name?cap_first}ResponseFrom${entity.name?cap_first}s(List<${entity.name?cap_first}> ${entity.name?camel_case}s) {
        return ${entity.name?camel_case}s.stream()
                .map(this::${entity.name?camel_case}ToGetList${entity.name?cap_first}Response)
                .collect(Collectors.toList());
    }
}
