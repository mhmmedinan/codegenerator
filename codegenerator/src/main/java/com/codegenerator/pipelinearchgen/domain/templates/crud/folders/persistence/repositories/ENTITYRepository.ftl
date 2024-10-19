package com.${projectName?lower_case}.persistence.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import com.${projectName?lower_case}.domain.entities.${entity.name}

@Repository
public interface ${entity.name}Repository extends JpaRepository<${entity.name}, ${string("toWrapperType", entity.idType)}>{

}