<#-- Template for Business Rules class -->
package com.${projectName?lower_case}.application.features.${string("camelcase", pluralEntityName)}.rules;

import lombok.*;
import org.springframework.stereotype.Service;
import com.${projectName?lower_case}.application.features.${string("camelcase", pluralEntityName)}.constants.Messages;
import com.${projectName?lower_case}.domain.entities.${entity.name?cap_first};
import com.${projectName?lower_case}.persistence.repositories.${entity.name?cap_first}Repository;
import io.github.minan65.core_crosscuttingconcerns.exceptions.types.BusinessException;
import io.github.minan65.core_localization.abstraction.TranslationService;

@Service
@RequiredArgsConstructor
public class ${entity.name?cap_first}BusinessRules {
    private final ${entity.name?cap_first}Repository ${string("camelcase", entity.name)}Repository;
    private final TranslationService translationService;

    public void ${entity.name?lower_case}IdShouldExistWhenSelected(${entity.idType} id) {
         ${string("camelcase", entity.name)}Repository.findById(id).orElseThrow(() ->
            new BusinessException(translationService.getMessage(Messages.BusinessErrors.${entity.name?cap_first}NotExists)));
    }
}
