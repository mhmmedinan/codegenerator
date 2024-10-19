<#-- Template for a Controller class -->
package com.${projectName?lower_case}.webapi.controllers;

import com.${projectName?lower_case}.application.features.${string("camelcase", pluralEntityName)}.commands.create.Create${entity.name?cap_first}Command;
import com.${projectName?lower_case}.application.features.${string("camelcase", pluralEntityName)}.commands.delete.Delete${entity.name?cap_first}Command;
import com.${projectName?lower_case}.application.features.${string("camelcase", pluralEntityName)}.commands.update.Update${entity.name?cap_first}Command;
import com.${projectName?lower_case}.application.features.${string("camelcase", pluralEntityName)}.queries.getlist.GetList${entity.name?cap_first}Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import an.awesome.pipelinr.Pipeline;
import java.util.List;

@RestController
@RequestMapping("/api/${string("camelcase", entity.name)}s")
public class ${entity.name?cap_first}sController {
    private final Pipeline pipeline;

    public ${entity.name?cap_first}sController(Pipeline pipeline) {
        this.pipeline = pipeline;
    }

    @PostMapping
    public ResponseEntity<Created${entity.name?cap_first}Response> add(@RequestBody Create${entity.name?cap_first}Command command) {
        Created${entity.name?cap_first}Response response = command.execute(pipeline);
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<Updated${entity.name?cap_first}Response> update(@RequestBody Update${entity.name?cap_first}Command command) {
        Updated${entity.name?cap_first}Response response = command.execute(pipeline);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Delete${entity.name?cap_first}Command command = new Delete${entity.name?cap_first}Command(id);
        command.execute(pipeline);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<${entity.name?cap_first}Response>> getAll() {
        GetList${entity.name?cap_first}Query query = new GetList${entity.name?cap_first}Query();
        List<${entity.name?cap_first}Response> responses = query.execute(pipeline);
        return ResponseEntity.ok(responses);
    }
}
