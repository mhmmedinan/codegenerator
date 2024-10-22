package com.codegenerator.pipelinearchgen.application.features.create.commands.New;

import java.nio.file.Files;
import java.nio.file.Path;

public class PomXmlCreator {
    public static void createPomXml(Path basePath, String projectName) {
        String pomXmlContent = """
            <project xmlns="http://maven.apache.org/POM/4.0.0"
                     xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                     xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
                <modelVersion>4.0.0</modelVersion>
                <parent>
                   <groupId>org.springframework.boot</groupId>
                   <artifactId>spring-boot-starter-parent</artifactId>
                   <version>3.2.6</version>
                   <relativePath/> <!-- lookup parent from repository -->
                </parent>
                <groupId>com.%s</groupId>
                <artifactId>%s</artifactId>
                <version>0.0.1-SNAPSHOT</version>
                <packaging>jar</packaging>

                <properties>
                     <spring-cloud.version>2023.0.2</spring-cloud.version>
                     <org.mapstruct.version>1.6.0.Beta1</org.mapstruct.version>
                     <lombok-mapstruct-binding.version>0.2.0</lombok-mapstruct-binding.version>
                     <org.projectlombok.version>1.18.30</org.projectlombok.version>
                     <java.version>19</java.version> <!-- Java 19 ayarlandı -->
                </properties>

                <dependencies>
                    <dependency>
                        <groupId>io.github.mhmmedinan-core</groupId>
                        <artifactId>core-persistence</artifactId>
                        <version>1.0.3</version>
                    </dependency>
                    <dependency>
                        <groupId>io.github.mhmmedinan-core</groupId>
                        <artifactId>core-crosscuttingconcerns</artifactId>
                        <version>1.0.4</version>
                    </dependency>
                    <dependency>
                        <groupId>io.github.mhmmedinan-core</groupId>
                        <artifactId>core-localization</artifactId>
                        <version>1.0.5</version>                
                    </dependency>
                    <dependency>
                       <groupId>org.projectlombok</groupId>
                       <artifactId>lombok</artifactId>
                       <optional>true</optional>
                    </dependency>
                    <dependency>
                       <groupId>org.postgresql</groupId>
                       <artifactId>postgresql</artifactId>
                       <scope>runtime</scope>
                    </dependency>
                    <dependency>
                        <groupId>org.springdoc</groupId>
                        <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
                        <version>2.3.0</version>
                    </dependency>
                    <dependency>
                       <groupId>net.sizovs</groupId>
                       <artifactId>pipelinr</artifactId>
                       <version>0.8</version>
                    </dependency>
                    <dependency>
                       <groupId>org.mapstruct</groupId>
                       <artifactId>mapstruct</artifactId>
                       <version>${org.mapstruct.version}</version>
                    </dependency>
                    <dependency>
                        <groupId>org.springframework.boot</groupId>
                        <artifactId>spring-boot-starter</artifactId>
                    </dependency>
                    <dependency>
                        <groupId>org.springframework.boot</groupId>
                        <artifactId>spring-boot-starter-data-jpa</artifactId>
                    </dependency>
                    <dependency>
                        <groupId>org.springframework.boot</groupId>
                        <artifactId>spring-boot-starter-validation</artifactId>
                    </dependency>
                    <dependency>
                        <groupId>org.springframework.boot</groupId>
                        <artifactId>spring-boot-starter-web</artifactId>
                    </dependency>
                    <dependency>
                        <groupId>org.springframework.boot</groupId>
                        <artifactId>spring-boot-devtools</artifactId>
                        <scope>runtime</scope>
                        <optional>true</optional>
                    </dependency>
                    <dependency>
                        <groupId>org.springframework.boot</groupId>
                        <artifactId>spring-boot-starter-test</artifactId>
                        <scope>test</scope>
                    </dependency>
                </dependencies>
                <dependencyManagement>
                       <dependencies>
                    	   <dependency>
                    		  <groupId>org.springframework.cloud</groupId>
                    		  <artifactId>spring-cloud-dependencies</artifactId>
                    		  <version>${spring-cloud.version}</version>
                    		  <type>pom</type>
                    		  <scope>import</scope>
                    	   </dependency>
                       </dependencies>
                </dependencyManagement>

               <build>
                   		<pluginManagement>
                   			<plugins>
                   				<plugin>
                   					<groupId>org.apache.maven.plugins</groupId>
                   					<artifactId>maven-compiler-plugin</artifactId>
                   					<version>3.8.1</version>
                   					<configuration>
                   						<source>${java.version}</source>
                   						<target>${java.version}</target>
                   						<annotationProcessorPaths>
                   							<path>
                   								<groupId>org.mapstruct</groupId>
                   								<artifactId>mapstruct-processor</artifactId>
                   								<version>${org.mapstruct.version}</version>
                   							</path>
                   							<path>
                   								<groupId>org.projectlombok</groupId>
                   								<artifactId>lombok</artifactId>
                   								<version>${org.projectlombok.version}</version>
                   							</path>
                   							<path>
                   								<groupId>org.projectlombok</groupId>
                   								<artifactId>lombok-mapstruct-binding</artifactId>
                   								<version>${lombok-mapstruct-binding.version}</version>
                   							</path>
                   						</annotationProcessorPaths>
                   					</configuration>
                   				</plugin>
                   			</plugins>
                   		</pluginManagement>
                   	</build>
            </project>
            """.formatted(basePath.getFileName().toString(), projectName);

        try {
            Path pomXmlPath = basePath.resolve("pom.xml");
            Files.writeString(pomXmlPath, pomXmlContent);
            System.out.println("Created pom.xml at: " + pomXmlPath);
        } catch (Exception e) {
            System.err.println("Failed to create pom.xml: " + e.getMessage());
        }
    }

}