package com.codegenerator.core.codegen.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DirectoryHelper {

    public static String getAssemblyDirectory() {
        String classpath = System.getProperty("java.class.path");
        String[] paths = classpath.split(File.pathSeparator);

        if (paths.length > 0) {
            Path firstPath = Paths.get(paths[0]);
            Path projectRoot = firstPath.getParent().getParent();
            Path templatePath = projectRoot.resolve(Paths.get("src", "main", "java", "com", "codegenerator", "pipelinearchgen", "domain"));
            return templatePath.toString();
        }

        throw new RuntimeException("Could not determine project root directory");

    }


    public static List<String> getFilesInDirectoryTree(String root, String searchPattern) {
        List<String> files = new ArrayList<>();
        Stack<String> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            String dir = stack.pop();
            try {
                Files.newDirectoryStream(Paths.get(dir), searchPattern).forEach(path -> files.add(path.toString()));
                Files.newDirectoryStream(Paths.get(dir), Files::isDirectory).forEach(subDir -> stack.push(subDir.toString()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return files;
    }

    public static List<String> getDirsInDirectoryTree(String root, String searchPattern) {
        List<String> dirs = new ArrayList<>();
        Stack<String> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            String dir = stack.pop();
            try {
                Files.newDirectoryStream(Paths.get(dir), searchPattern).forEach(path -> dirs.add(path.toString()));
                Files.newDirectoryStream(Paths.get(dir), Files::isDirectory).forEach(subDir -> stack.push(subDir.toString()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return dirs;
    }

    public static void deleteDirectory(String path) {
        try {
            Files.walk(Paths.get(path))
                    .sorted((path1, path2) -> path2.compareTo(path1))
                    .forEach(p -> {
                        try {
                            Files.delete(p);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

