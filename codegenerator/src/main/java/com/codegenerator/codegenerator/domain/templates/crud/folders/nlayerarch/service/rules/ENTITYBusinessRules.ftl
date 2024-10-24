<#-- Template for Business Rules class -->
package com.${projectName?lower_case}.service.rules;

import lombok.*;
import org.springframework.stereotype.Service;
import com.${projectName?lower_case}.service.constants.Messages;
import com.${projectName?lower_case}.repository.${entity.name?cap_first}Repository;
import io.github.mhmmedinan.core_crosscuttingconcerns.exceptions.types.BusinessException;
import io.github.mhmmedinan.core_localization.abstraction.TranslationService;

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
