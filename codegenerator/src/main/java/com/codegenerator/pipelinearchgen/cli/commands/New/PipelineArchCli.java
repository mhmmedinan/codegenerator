package com.codegenerator.pipelinearchgen.cli.commands.New;

import com.codegenerator.pipelinearchgen.cli.commands.generate.GenerateCrudCliCommand;
import picocli.CommandLine;

@CommandLine.Command(name = "pipelinearch", mixinStandardHelpOptions = true, version = "1.0",
        description = "PipelineArch CLI for generating Spring Boot projects.",
        subcommands = {NewProjectCommand.class, GenerateCrudCliCommand.class, CommandLine.HelpCommand.class})
public class PipelineArchCli implements Runnable {

    @CommandLine.Spec
    CommandLine.Model.CommandSpec spec;

    @Override
    public void run() {
        System.out.println("""
                #####  # #####  ###### #      # #    # ######   ##   #####   ####  #    #\s
                #    # # #    # #      #      # ##   # #       #  #  #    # #    # #    #\s
                #    # # #    # #####  #      # # #  # #####  #    # #    # #      ######\s
                #####  # #####  #      #      # #  # # #      ###### #####  #      #    #\s
                #      # #      #      #      # #   ## #      #    # #   #  #    # #    #\s
                #      # #      ###### ###### # #    # ###### #    # #    #  ####  #    #\s
                Welcome to PipelineArch CLI!
                Version: 1.0

                Available commands:
                  pipelinearch new [projectName]  - Create a new project
                  pipelinearch help               - Show help information
                """);
        CommandLine.usage(spec.commandLine(), System.out);
    }
}