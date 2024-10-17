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

/**
 * Utility class for file operations.
 * Provides methods to create files, remove lines based on a predicate, and remove specific content from files.
 */
public class FileHelper {

    /**
     * Asynchronously creates a file with the specified content.
     *
     * @param filePath    The path of the file to create.
     * @param fileContent The content to write to the file.
     * @throws IOException if an I/O error occurs.
     */
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

    /**
     * Asynchronously removes lines from a file that match a given predicate.
     *
     * @param filePath The path of the file to modify.
     * @param predicate The predicate to determine which lines to remove.
     * @throws IOException if an I/O error occurs.
     */
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

    /**
     * Asynchronously removes specific contents from a file.
     *
     * @param filePath The path of the file to modify.
     * @param contents The contents to remove from the file.
     * @throws IOException if an I/O error occurs.
     */
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

