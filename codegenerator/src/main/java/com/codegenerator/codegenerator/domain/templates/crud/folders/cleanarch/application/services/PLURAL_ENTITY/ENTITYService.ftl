package com.${ projectName?lower_case }.application.services.${string("camelcase",pluralEntityName)};

import com.${projectName?lower_case}.domain.entities.${entity.name?cap_first};
import java.util.List;

public interface ${entity.name?cap_first}Service
{
     ${entity.name?cap_first} add(${entity.name?cap_first} ${string("camelcase",entity.name)});
     ${entity.name?cap_first} update(${entity.name?cap_first} ${string("camelcase",entity.name)});
     ${entity.name?cap_first} delete(${entity.idType} id);
     List<${entity.name?cap_first}> getList();
     ${entity.name?cap_first} getById(${entity.idType} id);

}