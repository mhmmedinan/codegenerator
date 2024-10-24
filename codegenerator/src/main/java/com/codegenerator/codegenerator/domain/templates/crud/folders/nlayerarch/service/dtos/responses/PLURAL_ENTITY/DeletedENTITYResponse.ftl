package com.${ projectName?lower_case }.service.dtos.responses.${string("camelcase", pluralEntityName)};

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Deleted${entity.name?cap_first}Response {
private ${entity.idType} id;
}