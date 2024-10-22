package com.codegenerator.core.codegen.commandline.git;
import java.io.IOException;

public class GitCommandHelper {

    public static ProcessBuilder getGitProcess() {
        ProcessBuilder processBuilder = new ProcessBuilder("git");
        processBuilder.directory(new java.io.File(System.getProperty("user.dir")));
        processBuilder.redirectErrorStream(true);
        return processBuilder;
    }

    public static void runAsync(String arguments) throws IOException, InterruptedException {
        ProcessBuilder processBuilder = getGitProcess();
        processBuilder.command(arguments.split(" "));
        Process process = processBuilder.start();
        process.waitFor();
    }

    public static void commitChangesAsync(String message) throws IOException, InterruptedException {
        ProcessBuilder addCommand = getGitProcess();
        addCommand.command("add", ".");
        Process addProcess = addCommand.start();
        addProcess.waitFor();

        runAsync("commit -m \"" + message + "\"");
    }
}
