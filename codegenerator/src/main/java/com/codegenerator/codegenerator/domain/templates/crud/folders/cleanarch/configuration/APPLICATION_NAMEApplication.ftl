package com.${projectName?lower_case};

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.${projectName?lower_case}",
"io.github.mhmmedinan.core_localization","io.github.mhmmedinan.core_persistence","io.github.mhmmedinan.core_crosscuttingconcerns"})
public class ${string("pascalcase",projectName)}Application {

public static void main(String[] args) {
    SpringApplication.run(${string("pascalcase",projectName)}Application.class, args);
   }
}
