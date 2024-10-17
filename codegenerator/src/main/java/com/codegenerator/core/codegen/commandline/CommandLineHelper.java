package com.codegenerator.core.codegen.commandline;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.CompletableFuture;

public class CommandLineHelper {

    public static String getOSCommandLine() {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            return "cmd.exe";
        } else if (os.contains("mac")) {
            return "/bin/sh";
        } else { // Unix/Linux
            return "/bin/bash";
        }
    }

    public static CompletableFuture<String> runCommandAsync(String command) {
        return CompletableFuture.supplyAsync(() -> {
            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.command(getOSCommandLine(), "-c", command); // Bash veya sh için -c parametresi
            processBuilder.redirectErrorStream(true); // Hataları standart çıktıya yönlendir

            StringBuilder output = new StringBuilder();

            try {
                Process process = processBuilder.start();
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;

                while ((line = reader.readLine()) != null) {
                    output.append(line).append("\n");
                }

                int exitCode = process.waitFor(); // Komutun çıkış kodunu al
                if (exitCode != 0) {
                    throw new IOException("Command exited with error code: " + exitCode);
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }

            return output.toString();
        });
    }
}
