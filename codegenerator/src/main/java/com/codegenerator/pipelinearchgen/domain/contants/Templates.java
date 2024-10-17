package com.codegenerator.pipelinearchgen.domain.contants;

import com.codegenerator.core.codegen.helpers.PlatformHelper;

public class Templates {
    public static class Paths{
        public static final String ROOT = "templates";
        public static final String CRUD = PlatformHelper.securedPathJoin(ROOT, "crud");
        public static final String COMMAND = PlatformHelper.securedPathJoin(ROOT, "Command");
        public static final String QUERY = PlatformHelper.securedPathJoin(ROOT, "Query");
    }
}
