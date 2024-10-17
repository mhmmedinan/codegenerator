package com.codegenerator.core.codegen.commandline.git;
import java.io.IOException;

public class GitCommandHelper {

    public static ProcessBuilder getGitProcess() {
        ProcessBuilder processBuilder = new ProcessBuilder("git");
        processBuilder.directory(new java.io.File(System.getProperty("user.dir")));
        processBuilder.redirectErrorStream(true); // Hata akışını standart çıktıya yönlendir
        return processBuilder;
    }

    public static void runAsync(String arguments) throws IOException, InterruptedException {
        ProcessBuilder processBuilder = getGitProcess();
        processBuilder.command(arguments.split(" ")); // Argümanları ayırarak ekle
        Process process = processBuilder.start();
        process.waitFor(); // Sürecin tamamlanmasını bekle
    }

    public static void commitChangesAsync(String message) throws IOException, InterruptedException {
        ProcessBuilder addCommand = getGitProcess();
        addCommand.command("add", "."); // Tüm değişiklikleri ekle
        Process addProcess = addCommand.start();
        addProcess.waitFor(); // Ekleme işleminin tamamlanmasını bekle

        runAsync("commit -m \"" + message + "\"");
    }
}
