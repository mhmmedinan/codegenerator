package com.codegenerator.core.codegen.helpers;

public class PlatformHelper {

    public static String securedPathJoin(String... pathItems) {
        String path;
        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("nix") || os.contains("nux") || os.contains("mac")) {
            path = String.join("/", pathItems);
        } else {
            path = String.join("\\", pathItems);
        }

        return path;
    }

}

