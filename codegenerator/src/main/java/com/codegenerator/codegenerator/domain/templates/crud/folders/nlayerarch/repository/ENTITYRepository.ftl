package com.${projectName?lower_case}.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.${projectName?lower_case}.entity.${entity.name};
import org.springframework.stereotype.Repository;

@Repository
public interface ${entity.name}Repository extends JpaRepository<${entity.name}, ${string("toWrapperType", entity.idType)}>{

}