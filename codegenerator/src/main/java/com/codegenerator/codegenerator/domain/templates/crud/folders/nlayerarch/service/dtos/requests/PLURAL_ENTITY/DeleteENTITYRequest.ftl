package com.${ projectName?lower_case }.service.dtos.requests.${string("camelcase", pluralEntityName)};

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Delete${entity.name?cap_first}Request {
private ${entity.idType} id;
}