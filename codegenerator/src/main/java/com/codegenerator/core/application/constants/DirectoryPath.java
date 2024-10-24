package com.codegenerator.core.application.constants;

public class DirectoryPath {
    public static class Paths{

        public static final String BASE_PATH = "src\\main\\java\\com\\";

        //Clean Architecture
        public static final String BASE_APPLICATION_PATH = "\\application";
        public static final String BASE_PERSISTENCE_PATH = "\\persistence";
        public static final String BASE_INFRASTRUCTURE_PATH = "\\infrastructure";
        public static final String BASE_DOMAIN_PATH = "\\domain";
        public static final String BASE_WEBAPI_PATH = "\\webapi";
        public static final String RESOURCES_PATH = "src\\main\\resources";

        //child path
        public static final String APPLICATION_FEATURES_PATH = BASE_APPLICATION_PATH + "\\features";
        public static final String APPLICATION_SERVICES_PATH = BASE_APPLICATION_PATH + "\\services";
        public static final String PERSISTENCE_REPOSITORIES_PATH = BASE_PERSISTENCE_PATH + "\\repositories";
        public static final String DOMAIN_ENTITIES_PATH = BASE_DOMAIN_PATH + "\\entities";
        public static final String INFRASTRUCTURE_ADAPTERS_PATH = BASE_INFRASTRUCTURE_PATH + "\\adapters";
        public static final String WEBAPI_CONTROLLER_PATH = BASE_WEBAPI_PATH + "\\controllers";

        //NLayer Architecture
        public static final String BASE_SERVICE_PATH = "\\service";
        public static final String BASE_REPOSITORY_PATH = "\\repository";
        public static final String BASE_ENTITY_PATH = "\\entity";
        public static final String BASE_CONTROLLER_PATH = "\\controller";



    }
}
