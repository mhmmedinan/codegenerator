package com.codegenerator.codegenerator.application.features.create.commands.New;

import com.codegenerator.core.application.constants.DirectoryPath;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class MessagesPropertiesCreator {

    public static void create(Path basePath){
        createMessageEnProperties(basePath);
        createMessageTrProperties(basePath);
        createMessageProperties(basePath);
    }

    public static void createMessageTrProperties(Path basePath) {
        Path propertiesPath = basePath.resolve(DirectoryPath.Paths.RESOURCES_MESSAGES_PROPERTIES_TR_PATH);
        String propertiesContent = """
                //test
                idNotExists = id mevcut değil
                """;

        try (BufferedWriter writer = Files.newBufferedWriter(propertiesPath)) {
            writer.write(propertiesContent);
            System.out.println("Created message_tr.properties: " + propertiesPath);
        } catch (IOException e) {
            System.err.println("Failed to create message_tr.properties: " + e.getMessage());
        }
    }

    public static void createMessageEnProperties(Path basePath) {
        Path propertiesPath = basePath.resolve(DirectoryPath.Paths.RESOURCES_MESSAGES_PROPERTIES_EN_PATH);
        String propertiesContent = """
                //test
                idNotExists = id not exists
                """;

        try (BufferedWriter writer = Files.newBufferedWriter(propertiesPath)) {
            writer.write(propertiesContent);
            System.out.println("Created message_en.properties: " + propertiesPath);
        } catch (IOException e) {
            System.err.println("Failed to create message_en.properties: " + e.getMessage());
        }
    }

    public static void createMessageProperties(Path basePath) {
        Path propertiesPath = basePath.resolve(DirectoryPath.Paths.RESOURCES_MESSAGES_PROPERTIES_PATH);
        String propertiesContent = """
                //test
                idNotExists = id not exists
                """;

        try (BufferedWriter writer = Files.newBufferedWriter(propertiesPath)) {
            writer.write(propertiesContent);
            System.out.println("Created message.properties: " + propertiesPath);
        } catch (IOException e) {
            System.err.println("Failed to create message.properties: " + e.getMessage());
        }
    }
}
