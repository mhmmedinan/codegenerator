<#-- Template for a Controller class -->
package com.${projectName?lower_case}.controller;

import com.${projectName?lower_case}.service.dtos.requests.${string("camelcase", pluralEntityName)}.*;
import com.${projectName?lower_case}.service.dtos.responses.${string("camelcase", pluralEntityName)}.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.${projectName?lower_case}.service.abstracts.${entity.name?cap_first}Service;
import java.util.List;
import lombok.*;

@RestController
@RequestMapping("/api/${string("camelcase", entity.name)}s")
@RequiredArgsConstructor
public class ${entity.name?cap_first}sController {
    private final ${entity.name?cap_first}Service ${string("camelcase", entity.name)}Service;

    @PostMapping
    public ResponseEntity<Created${entity.name?cap_first}Response> add(@RequestBody Create${entity.name?cap_first}Request request) {
        Created${entity.name?cap_first}Response response = ${string("camelcase", entity.name)}Service.add(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<Updated${entity.name?cap_first}Response> update(@RequestBody Update${entity.name?cap_first}Request request) {
        Updated${entity.name?cap_first}Response response = ${string("camelcase", entity.name)}Service.update(request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Deleted${entity.name?cap_first}Response> delete(@PathVariable ${entity.idType} id) {
        Delete${entity.name?cap_first}Request request = new Delete${entity.name?cap_first}Request(id);
        Deleted${entity.name?cap_first}Response response = ${string("camelcase", entity.name)}Service.delete(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<GetList${entity.name?cap_first}Response>> getAll() {
        List<GetList${entity.name?cap_first}Response> responses = ${string("camelcase", entity.name)}Service.getList();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Get${entity.name?cap_first}Response> getById(@PathVariable ${entity.idType} id) {
       Get${entity.name?cap_first}Response response = ${string("camelcase", entity.name)}Service.getById(id);
       return ResponseEntity.ok(response);
    }
}
