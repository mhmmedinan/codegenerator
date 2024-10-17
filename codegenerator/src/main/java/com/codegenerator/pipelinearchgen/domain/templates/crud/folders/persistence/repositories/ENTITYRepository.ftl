package com.${projectName?lower_case}.persistence.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ${entity.name}Repository extends JpaRepository<${entity.name}, ${entity.idType}>{

}