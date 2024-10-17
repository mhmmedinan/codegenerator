package com.codegenerator.core.codegen.file;

import com.codegenerator.core.codegen.helpers.PlatformHelper;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DirectoryHelper {

    public static String getAssemblyDirectory() {
       /* try {
            String codeBase = PlatformHelper.getDirectoryHeader() +
                    DirectoryHelper.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            String codeBaseLocation =  DirectoryHelper.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            System.out.println("Location" + codeBaseLocation);
            Path path = Paths.get(codeBase);
            Path parentPath = path.getParent();

            if (parentPath == null) {
                throw new IllegalArgumentException("Parent directory not found for path: " + codeBase);
            }

            return parentPath.toString();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }*/

        try {
            Path path = Paths.get(DirectoryHelper.class.getProtectionDomain().getCodeSource().getLocation().toURI());
            File file = new File(path.toString());

            if (file.exists() && file.isFile()) {
                return file.getParentFile().getAbsolutePath();
            } else if (file.isDirectory()) {
                return file.getAbsolutePath();
            } else {
                throw new IllegalArgumentException("Assembly directory is invalid or not found.");
            }
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("URI Syntax Exception: " + e.getMessage());
        } catch (Exception e) {
            throw new IllegalArgumentException("Parent directory not found: " + e.getMessage());
        }
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
                    .sorted((path1, path2) -> path2.compareTo(path1)) // Reverse order to delete files before directories
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

