package com.${projectName?lower_case}.application.services.${string("camelcase",pluralEntityName)};

import com.${projectName?lower_case}.domain.entities.${entity.name?cap_first};
import com.${projectName?lower_case}.persistence.repositories.${entity.name?cap_first}Repository;
import org.springframework.stereotype.Service;
import io.github.minan65.core_crosscuttingconcerns.exceptions.types.BusinessException;
import java.util.List;
import lombok.*;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ${entity.name?cap_first}ServiceImpl implements ${entity.name?cap_first}Service {

private final ${entity.name?cap_first}Repository ${string("camelcase", entity.name)}Repository;


    @Override
    public ${entity.name?cap_first} add(${entity.name?cap_first} ${string("camelcase", entity.name)}) {
      return ${string("camelcase", entity.name)}Repository.save(${string("camelcase", entity.name)});
    }

    @Override
    public ${entity.name?cap_first} update(${entity.name?cap_first} ${string("camelcase", entity.name)}) {
        ${entity.name?cap_first} existing${entity.name?cap_first} = ${entity.name?lower_case}Repository.findById(${string("camelcase", entity.name)}.getId()).orElseThrow(() -> new BusinessException("${entity.name?cap_first} not found"));
        ${entity.name?cap_first} updated${entity.name?cap_first} = ${entity.name?lower_case}Repository.save(existing${entity.name?cap_first});
        return updated${entity.name?cap_first};
    }

    @Override
    public ${entity.name?cap_first} delete(${entity.idType} id) {
        ${entity.name?cap_first} existing${entity.name?cap_first} = ${entity.name?lower_case}Repository.findById(id).orElseThrow(() -> new BusinessException("${entity.name?cap_first} not found"));
        existing${entity.name?cap_first}.setDeletedDate(LocalDateTime.now());
        ${entity.name?cap_first} deleted${entity.name?cap_first} = ${entity.name?lower_case}Repository.save(existing${entity.name?cap_first});
        return deleted${entity.name?cap_first};
    }

    @Override
    public List<${entity.name?cap_first}> getList() {
      return ${string("camelcase", entity.name)}Repository.findAll();
    }

    @Override
    public ${entity.name?cap_first} getById(${entity.idType} id) {
        return ${string("camelcase", entity.name)}Repository.findById(id)
        .orElseThrow(() -> new BusinessException("${entity.name?cap_first} not found"));
    }
}
