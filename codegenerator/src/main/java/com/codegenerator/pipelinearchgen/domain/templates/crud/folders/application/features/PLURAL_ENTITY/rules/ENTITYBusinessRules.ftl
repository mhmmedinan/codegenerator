<#-- Template for Business Rules class -->
package com.${projectName?lower_case}.application.features.${string("camelcase", pluralEntityName)}.rules;

import lombok.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ${entity.name?cap_first}BusinessRules {
    private final ${entity.name?cap_first}Repository ${entity.name?camel_case}Repository;

    public void ${entity.name?cap_first}IdShouldExistWhenSelected(${entity.idType} id) {
        ${entity.name?cap_first} ${entity.name?camel_case} = ${entity.name?camel_case}Repository.findById(id).orElseThrow(() ->
            new BusinessException(Messages.BusinessErrors.${entity.name?cap_first}NotExists));
    }
}
