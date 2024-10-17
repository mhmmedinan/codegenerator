package com.codegenerator.core.codegen.helpers;

import java.util.StringJoiner;

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

    public static String getDirectoryHeader() {
        String file;
        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("nix") || os.contains("nux") || os.contains("mac")) {
            file = "file://";
        } else {
            file = "";
        }

        return file;
    }
}

