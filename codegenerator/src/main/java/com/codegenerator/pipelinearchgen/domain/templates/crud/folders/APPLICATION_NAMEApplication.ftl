package com.${projectName?lower_case};

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ${projectName?cap_first}Application {

public static void main(String[] args) {
SpringApplication.run(${projectName?cap_first}Application.class, args);
}
}
