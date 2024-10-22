package com.codegenerator.core.codegen.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;

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
}

