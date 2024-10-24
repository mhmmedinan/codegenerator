package com.${projectName?lower_case}.service.concretes;

import com.${projectName?lower_case}.service.dtos.requests.${string("camelcase", pluralEntityName)}.*;
import com.${projectName?lower_case}.service.dtos.responses.${string("camelcase", pluralEntityName)}.*;
import com.${projectName?lower_case}.entity.*;
import com.${projectName?lower_case}.service.abstracts.${entity.name?cap_first}Service;
import com.${projectName?lower_case}.service.mappers.${entity.name?cap_first}Mapper;
import com.${projectName?lower_case}.repository.${entity.name?cap_first}Repository;
import org.springframework.stereotype.Service;
import io.github.mhmmedinan.core_crosscuttingconcerns.exceptions.types.BusinessException;
import java.util.List;
import lombok.*;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ${entity.name?cap_first}ServiceImpl implements ${entity.name?cap_first}Service {

    private final ${entity.name?cap_first}Repository ${string("camelcase", entity.name)}Repository;


    @Override
    public Created${entity.name?cap_first}Response add(Create${entity.name?cap_first}Request create${entity.name?cap_first}Request) {
        ${entity.name?cap_first} ${entity.name?lower_case} = ${entity.name?cap_first}Mapper.INSTANCE
        .${string("camelcase",entity.name)}FromCreate${entity.name?cap_first}Request(create${entity.name?cap_first}Request);
        ${entity.name?cap_first} created${entity.name?cap_first} = ${string("camelcase", entity.name)}Repository.save(${entity.name?lower_case});
        return ${entity.name?cap_first}Mapper.INSTANCE.created${entity.name?cap_first}ResponseFrom${entity.name?cap_first}(created${entity.name?cap_first});
    }

    @Override
    public Updated${entity.name?cap_first}Response update(Update${entity.name?cap_first}Request update${entity.name?cap_first}Request) {
        ${entity.name?cap_first} existing${entity.name?cap_first} = ${string("camelcase", entity.name)}Repository.findById(update${entity.name?cap_first}Request.getId()).orElseThrow(() -> new BusinessException("${entity.name?cap_first} not found"));
        ${entity.name?cap_first} updated${entity.name?cap_first} = ${string("camelcase", entity.name)}Repository.save(existing${entity.name?cap_first});
        return ${entity.name?cap_first}Mapper.INSTANCE.updated${entity.name?cap_first}ResponseFrom${entity.name?cap_first}(updated${entity.name?cap_first});
    }

    @Override
    public Deleted${entity.name?cap_first}Response delete(Delete${entity.name?cap_first}Request delete${entity.name?cap_first}Request) {
        ${entity.name?cap_first} existing${entity.name?cap_first} = ${string("camelcase", entity.name)}Repository.findById(delete${entity.name?cap_first}Request.getId()).orElseThrow(() -> new BusinessException("${entity.name?cap_first} not found"));
        existing${entity.name?cap_first}.setDeletedDate(LocalDateTime.now());
        ${entity.name?cap_first} deleted${entity.name?cap_first} = ${string("camelcase", entity.name)}Repository.save(existing${entity.name?cap_first});
        Deleted${entity.name?cap_first}Response deleted${entity.name?cap_first}Response =
                ${entity.name?cap_first}Mapper.INSTANCE.deleted${entity.name?cap_first}ResponseFrom${entity.name?cap_first}(deleted${entity.name?cap_first});
        return deleted${entity.name?cap_first}Response;
    }

    @Override
    public List<GetList${entity.name?cap_first}Response> getList() {
        List<${entity.name?cap_first}> ${string("camelcase", entity.name)}List = ${string("camelcase", entity.name)}Repository.findAll();
        List<GetList${entity.name?cap_first}Response> responses = ${entity.name?cap_first}Mapper.INSTANCE.getList${entity.name?cap_first}ResponseFrom${entity.name?cap_first}s(${string("camelcase", entity.name)}List);
        return responses;
    }

    @Override
    public Get${entity.name?cap_first}Response getById(${entity.idType} id) {
        ${entity.name?cap_first} ${string("camelcase", entity.name)} =${string("camelcase", entity.name)}Repository.findById(id)
        .orElseThrow(() -> new BusinessException("${entity.name?cap_first} not found"));
        Get${entity.name?cap_first}Response response = ${entity.name?cap_first}Mapper.INSTANCE.${string("camelcase", entity.name)}ToGet${entity.name?cap_first}Response(${string("camelcase", entity.name)});
        return response;
    }
}
