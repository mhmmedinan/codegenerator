package com.codegenerator.core.codegen.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FileHelper {

    public static CompletableFuture<Void> createFileAsync(String filePath, String fileContent) {
        return CompletableFuture.runAsync(() -> {
            try {
                File file = new File(filePath);
                File directory = file.getParentFile();
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                    writer.write(fileContent);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    public static CompletableFuture<Void> removeLinesAsync(String filePath, Predicate<String> predicate) {
        return CompletableFuture.runAsync(() -> {
            try {
                List<String> fileLines = Files.readAllLines(Paths.get(filePath));
                List<String> filteredLines = fileLines.stream()
                        .filter(predicate.negate())
                        .collect(Collectors.toList());
                Files.write(Paths.get(filePath), filteredLines);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }


    public static CompletableFuture<Void> removeContentAsync(String filePath, List<String> contents) {
        return CompletableFuture.runAsync(() -> {
            try {
                String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
                for (String content : contents) {
                    fileContent = fileContent.replace(content, "");
                }
                Files.write(Paths.get(filePath), fileContent.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}

