package com.codegenerator.core.codegen.code;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.regex.*;
import java.util.stream.Collectors;

public class JavaCodeInjector {

    public static CompletableFuture<Void> addCodeLinesToMethodAsync(String filePath, String methodName, List<String> codeLines)
    {
        return CompletableFuture.runAsync(() -> {
            try {
                List<String> fileContent = Files.readAllLines(Paths.get(filePath));
                String methodStartRegex = "((public|protected|private protected|private)\\s+)?(static\\s+)?(void|[a-zA-Z]+(<.*>)?)\\s+\\b" + methodName + "\\b\\s*\\(";
                String scopeBlockStartRegex = "\\{";
                String scopeBlockEndRegex = "\\}";
                int methodStartIndex = -1;
                int methodEndIndex = -1;
                int curlyBracketCountInMethod = 1;

                for (int i = 0; i < fileContent.size(); ++i) {
                    Matcher methodStart = Pattern.compile(methodStartRegex).matcher(fileContent.get(i));
                    if (!methodStart.find())
                        continue;
                    methodStartIndex = i;
                    if (!Pattern.compile("\\{").matcher(fileContent.get(i)).find())
                        for (int j = methodStartIndex + 1; j < fileContent.size(); ++j) {
                            if (!Pattern.compile("\\{").matcher(fileContent.get(j)).find())
                                continue;
                            methodStartIndex = j;
                            break;
                        }
                }

                for (int i = methodStartIndex + 1; i < fileContent.size(); ++i) {
                    if (Pattern.compile(scopeBlockStartRegex).matcher(fileContent.get(i)).find())
                        ++curlyBracketCountInMethod;
                    if (Pattern.compile(scopeBlockEndRegex).matcher(fileContent.get(i)).find())
                        --curlyBracketCountInMethod;
                    if (curlyBracketCountInMethod != 0)
                        continue;
                    methodEndIndex = i;
                    for (int j = methodEndIndex - 1; j > methodStartIndex; --j) {
                        if (Pattern.compile(scopeBlockEndRegex).matcher(fileContent.get(j)).find())
                            break;
                        if (Pattern.compile("\\)\\s+return").matcher(fileContent.get(j)).find())
                            break;
                        if (Pattern.compile("\\s+return").matcher(fileContent.get(j)).find() && Pattern.compile("(if|else if|else)\\s*\\(").matcher(fileContent.get(j - 1)).find())
                            break;
                        if (Pattern.compile("\\s+return").matcher(fileContent.get(j)).find()) {
                            methodEndIndex = j;
                            break;
                        }
                    }
                    break;
                }

                if (methodStartIndex == -1 || methodEndIndex == -1)
                    throw new Exception(methodName + " not found in \"" + filePath + "\".");

                List<String> methodContent = fileContent.subList(methodStartIndex + 1, methodEndIndex);
                int minimumSpaceCountInMethod = methodContent.size() < 2
                        ? countLeadingSpaces(fileContent.get(methodStartIndex)) * 2
                        : methodContent.stream().filter(line -> !line.isEmpty()).mapToInt(JavaCodeInjector::countLeadingSpaces).min().orElse(0);

                fileContent.addAll(methodEndIndex, codeLines.stream().map(line -> " ".repeat(minimumSpaceCountInMethod) + line).collect(Collectors.toList()));
                Files.write(Paths.get(filePath), fileContent);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }



    public CompletableFuture<Void> addCodeLinesAsPropertyAsync(String filePath, String propertyName, String propertyType, List<String> codeLines)
    {
        return CompletableFuture.runAsync(() -> {
            try {
                String content = new String(Files.readAllBytes(Paths.get(filePath)));
                String classRegex = "class\\s+\\w+\\s*\\{";
                Pattern pattern = Pattern.compile(classRegex);
                Matcher matcher = pattern.matcher(content);

                if (matcher.find()) {
                    int classStart = matcher.end();
                    StringBuilder updatedContent = new StringBuilder(content);
                    String propertyDeclaration = "private " + propertyType + " " + propertyName + ";\n";
                    updatedContent.insert(classStart, propertyDeclaration);
                    String codeToInsert = String.join("\n", codeLines);
                    updatedContent.append("\n" + codeToInsert);
                    Files.write(Paths.get(filePath), updatedContent.toString().getBytes());
                } else {
                    throw new IllegalArgumentException("Class not found in file: " + filePath);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static CompletableFuture<Void> addCodeLinesAsPropertyAsync(String filePath, List<String> codeLines)
    {
        return CompletableFuture.runAsync(() -> {
            try {
                List<String> fileContent = Files.readAllLines(Paths.get(filePath));
                String propertyStartRegex = "(public|protected|private protected|private)?\\s*(static)?\\s+(\\w+(<.*>)?)\\s+(\\w+)\\s*(\\{.*\\}|=.+;)";
                int indexToAdd = -1;

                for (int i = 0; i < fileContent.size(); ++i) {
                    Matcher propertyStart = Pattern.compile(propertyStartRegex).matcher(fileContent.get(i));
                    if (propertyStart.find())
                        indexToAdd = i;
                }

                int propertySpaceCountInClass;
                if (indexToAdd == -1) {
                    String classRegex = "class\\s+(\\w+)";
                    for (int i = 0; i < fileContent.size(); ++i) {
                        Matcher propertyStart = Pattern.compile(classRegex).matcher(fileContent.get(i));
                        if (propertyStart.find())
                            indexToAdd = i;
                        if (!Pattern.compile("\\{").matcher(fileContent.get(i)).find())
                            for (int j = indexToAdd + 1; j < fileContent.size(); ++j) {
                                if (!Pattern.compile("\\{").matcher(fileContent.get(j)).find())
                                    continue;
                                indexToAdd = j;
                                break;
                            }
                    }
                    propertySpaceCountInClass = countLeadingSpaces(fileContent.get(indexToAdd)) * 2;
                } else {
                    propertySpaceCountInClass = countLeadingSpaces(fileContent.get(indexToAdd));
                }

                List<String> updatedFileContent = new ArrayList<>(fileContent);
                updatedFileContent.addAll(indexToAdd + 1, codeLines.stream()
                        .map(line -> " ".repeat(propertySpaceCountInClass) + line)
                        .collect(Collectors.toList()));
                Files.write(Paths.get(filePath), updatedFileContent);

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }



    public static CompletableFuture<Void> addImportToFile(String filePath, List<String> importLines)
    {
        return CompletableFuture.runAsync(() -> {
            try {
                List<String> fileContent = Files.readAllLines(Paths.get(filePath));
                List<String> importLinesToAdd = importLines.stream()
                        .filter(importLine -> !fileContent.contains(importLine))
                        .collect(Collectors.toList());
                Pattern importPattern = Pattern.compile("^import\\s+.*;$");
                int indexToAdd = 0;

                for (int i = 0; i < fileContent.size(); ++i) {
                    String fileLine = fileContent.get(i);
                    if (importPattern.matcher(fileLine).matches())
                        continue;
                    indexToAdd = i;
                    break;
                }

                fileContent.addAll(indexToAdd, importLinesToAdd);
                Files.write(Paths.get(filePath), fileContent);

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static CompletableFuture<Void> addMethodToClass(String filePath, String className, List<String> codeLines)
    {
        return CompletableFuture.runAsync(() -> {
            try {
                List<String> fileContent = Files.readAllLines(Paths.get(filePath));
                Pattern classStartPattern = Pattern.compile("((public|protected|private protected|private)\\s+)?(static\\s+)?\\s+\\b" + className);
                Pattern scopeBlockStartPattern = Pattern.compile("\\{");
                Pattern scopeBlockEndPattern = Pattern.compile("\\}");
                int classStartIndex = -1;
                int classEndIndex = -1;
                int curlyBracketCountInClass = 1;

                for (int i = 0; i < fileContent.size(); ++i) {
                    Matcher methodStart = classStartPattern.matcher(fileContent.get(i));
                    if (!methodStart.find())
                        continue;
                    classStartIndex = i;
                    if (!scopeBlockStartPattern.matcher(fileContent.get(i)).find())
                        for (int j = classStartIndex + 1; j < fileContent.size(); ++j) {
                            if (!scopeBlockStartPattern.matcher(fileContent.get(j)).find())
                                continue;
                            classStartIndex = j;
                            break;
                        }
                    break;
                }

                for (int i = classStartIndex + 1; i < fileContent.size(); ++i) {
                    if (scopeBlockStartPattern.matcher(fileContent.get(i)).find())
                        ++curlyBracketCountInClass;
                    if (scopeBlockEndPattern.matcher(fileContent.get(i)).find())
                        --curlyBracketCountInClass;
                    if (curlyBracketCountInClass != 0)
                        continue;
                    classEndIndex = i;
                    break;
                }

                if (classStartIndex == -1 || classEndIndex == -1)
                    throw new Exception(className + " not found in \"" + filePath + "\".");

                List<String> classContent = fileContent.subList(classStartIndex + 1, classEndIndex);
                int minimumSpaceCountInClass = classContent.size() < 2
                        ? countLeadingSpaces(fileContent.get(classStartIndex)) * 2
                        : classContent.stream().filter(line -> !line.isEmpty()).mapToInt(JavaCodeInjector::countLeadingSpaces).min().orElse(0);

                fileContent.addAll(classEndIndex, codeLines.stream().map(line -> " ".repeat(minimumSpaceCountInClass) + line).collect(Collectors.toList()));
                Files.write(Paths.get(filePath), fileContent);

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }


    private static int countLeadingSpaces(String line)
    {
        return (int) line.chars().takeWhile(Character::isWhitespace).count();
    }
}
