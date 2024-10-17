<#-- Template for a Get List Query class -->
package com.${projectName?lower_case}.application.features.${pluralEntityName}.queries.getlist;

import an.awesome.pipelinr.Command;
import java.util.List;

public class GetList${entity.name?cap_first}Query implements Command<List<GetList${entity.name?cap_first}Response>> {}
