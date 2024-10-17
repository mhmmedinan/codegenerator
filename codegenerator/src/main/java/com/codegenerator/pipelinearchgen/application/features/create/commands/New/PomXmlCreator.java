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
                     <maven.compiler.source>19</maven.compiler.source>
                     <maven.compiler.target>19</maven.compiler.target>
                     <java.version>19</java.version> <!-- Java 19 ayarlandı -->
                </properties>

                <dependencies>
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

                 <build>
                    <plugins>
                       <plugin>
                         <groupId>org.apache.maven.plugins</groupId>
                         <artifactId>maven-compiler-plugin</artifactId>
                         <version>3.8.1</version>
                         <configuration>
                           <source>19</source> <!-- Java kaynak sürümü -->
                           <target>19</target> <!-- Java hedef sürümü -->
                         </configuration>
                       </plugin>
                    </plugins>
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
