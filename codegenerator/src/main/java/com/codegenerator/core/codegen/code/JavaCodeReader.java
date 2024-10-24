package com.codegenerator.core.codegen.code;

import com.codegenerator.core.codegen.code.valueobjects.PropertyInfo;
import com.codegenerator.core.codegen.file.DirectoryHelper;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.regex.*;
import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JavaCodeReader {

    public static CompletableFuture<String> readClassNameAsync(String filePath) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
                String pattern = "class\\s+(\\w+)";
                Matcher match = Pattern.compile(pattern).matcher(fileContent);
                if (!match.find()) return "";
                return match.group(1);
            } catch (IOException e) {
                e.printStackTrace();
                return "";
            }
        });
    }

    public static CompletableFuture<String> readBaseClassNameAsync(String filePath) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
                String pattern = "class\\s+\\w+\\s*:?\\s*(\\w+)";
                Matcher match = Pattern.compile(pattern).matcher(fileContent);
                if (!match.find()) return "";
                return match.group(1);
            } catch (IOException e) {
                e.printStackTrace();
                return "";
            }
        });
    }

    public static CompletableFuture<List<String>> readBaseClassGenericArgumentsAsync(String filePath) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
                String pattern = "class\\s+\\w+\\s*(?:<([\\w,\\s]+)>)?";
                Matcher match = Pattern.compile(pattern).matcher(fileContent);

                if (!match.find() || match.group(1) == null) {
                    return List.of();
                }

                String[] genericArguments = match.group(1).split(",");
                return Stream.of(genericArguments).map(String::trim).collect(Collectors.toList());
            } catch (IOException e) {
                e.printStackTrace();
                return List.of();
            }
        });

    }

    public static CompletableFuture<List<PropertyInfo>> readClassPropertiesAsync(String filePath, String projectPath)
    {
        return CompletableFuture.supplyAsync(() -> {
            try {
                String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
                Pattern annotationPattern = Pattern.compile("@\\w+");
                Pattern propertyPattern = Pattern.compile("(private|protected|public)\\s+(static\\s+)?(\\w+)\\s+(\\w+);");
                Pattern builtInTypePattern = Pattern.compile("^(boolean|byte|char|double|float|int|long|object|short|String)$", Pattern.CASE_INSENSITIVE);
                Matcher matcher = propertyPattern.matcher(fileContent);
                List<PropertyInfo> properties = new ArrayList<>();

                while (matcher.find()) {
                    String accessModifier = matcher.group(1) != null ? matcher.group(1).trim() : "private";
                    String type = matcher.group(3);
                    String name = matcher.group(4);
                    String typeName = type.replace("?", "");
                    String nameSpace = null;

                    List<String> annotations = new ArrayList<>();
                    Matcher annotationMatcher = annotationPattern.matcher(fileContent.substring(0, matcher.start()));
                    while (annotationMatcher.find()) {
                        annotations.add(annotationMatcher.group());
                    }


                    if (!builtInTypePattern.matcher(typeName).matches()) {
                        List<String> potentialPropertyTypeFilePaths = DirectoryHelper.getFilesInDirectoryTree(projectPath, typeName + ".java");
                        List<String> usingNameSpacesInFile = readUsingNameSpacesAsync(filePath).join();

                        for (String potentialPropertyTypeFilePath : potentialPropertyTypeFilePaths) {
                            String potentialPropertyNameSpace = capitalizeWords(potentialPropertyTypeFilePath
                                    .replace(projectPath, "")
                                    .replace("\\", ".")
                                    .replace("." + typeName + ".java", "")
                                    .substring(1));

                            if (usingNameSpacesInFile.contains(potentialPropertyNameSpace)) {
                                nameSpace = potentialPropertyNameSpace;
                                break;
                            }
                        }
                    }

                    PropertyInfo propertyInfo = new PropertyInfo(name, type, accessModifier, nameSpace,annotations);
                    properties.add(propertyInfo);
                }

                return properties;
            } catch (IOException e) {
                e.printStackTrace();
                return List.of();
            }
        });

    }

    private static String capitalizeWords(String value) {
        Pattern pattern = Pattern.compile("\\b(\\w)");
        Matcher matcher = pattern.matcher(value);
        StringBuffer sb = new StringBuffer();

        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group().toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    public static CompletableFuture<List<String>> readUsingNameSpacesAsync(String filePath) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                List<String> fileContent = Files.readAllLines(Paths.get(filePath));
                Pattern importPattern = Pattern.compile("^import\\s+(.+);");
                return fileContent.stream()
                        .filter(line -> importPattern.matcher(line).matches())
                        .map(line -> {
                            Matcher matcher = importPattern.matcher(line);
                            matcher.find();
                            return matcher.group(1);
                        })
                        .collect(Collectors.toList());
            } catch (IOException e) {
                e.printStackTrace();
                return List.of();
            }
        });
    }
}

