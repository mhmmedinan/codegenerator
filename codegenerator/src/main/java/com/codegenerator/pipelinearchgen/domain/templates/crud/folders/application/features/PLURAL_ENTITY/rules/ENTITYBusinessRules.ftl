<#-- Template for Business Rules class -->
package com.${projectName?lower_case}.application.features.${string("camelcase", pluralEntityName)}.rules;

import lombok.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ${entity.name?cap_first}BusinessRules {
    private final ${entity.name?cap_first}Repository ${string("camelcase", entity.name)}Repository;

    public void ${entity.name?cap_first}IdShouldExistWhenSelected(${entity.idType} id) {
        ${entity.name?cap_first} ${string("camelcase", entity.name)} = ${string("camelcase", entity.name)}Repository.findById(id).orElseThrow(() ->
            new BusinessException(Messages.BusinessErrors.${entity.name?cap_first}NotExists));
    }
}
