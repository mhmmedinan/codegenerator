package com.${ projectName?lower_case }.service.abstracts;

import com.${projectName?lower_case}.service.dtos.requests.${string("camelcase", pluralEntityName)}.*;
import com.${projectName?lower_case}.service.dtos.responses.${string("camelcase", pluralEntityName)}.*;
import java.util.List;

public interface ${entity.name?cap_first}Service
{
     Created${entity.name?cap_first}Response add(Create${entity.name?cap_first}Request create${entity.name?cap_first}Request);
     Updated${entity.name?cap_first}Response update(Update${entity.name?cap_first}Request update${entity.name?cap_first}Request);
     Deleted${entity.name?cap_first}Response delete(Delete${entity.name?cap_first}Request delete${entity.name?cap_first}Request);
     List<GetList${entity.name?cap_first}Response> getList();
     Get${entity.name?cap_first}Response getById(${entity.idType} id);

}