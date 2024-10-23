server:
    port: 8080
spring:
    application:
         name: ${projectName?lower_case}
    datasource:
         password: 12345
         url: jdbc:postgresql://localhost:5432/test
         username: postgres
    jpa:
        hibernate:
            ddl-auto: update
        properties:
            hibernate:
                 dialect: org.hibernate.dialect.PostgreSQLDialect
            javax:
                persistence:
                    validation:
                          mode: none
            show-sql: true