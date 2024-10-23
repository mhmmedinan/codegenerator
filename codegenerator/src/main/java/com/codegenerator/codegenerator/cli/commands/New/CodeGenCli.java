package com.codegenerator.codegenerator.cli.commands.New;

import com.codegenerator.codegenerator.cli.commands.generate.GenerateCrudCliCommand;
import picocli.CommandLine;

@CommandLine.Command(name = "codegen", mixinStandardHelpOptions = true, version = "1.0",
        description = "CodeGenerator CLI for generating Spring Boot projects.",
        subcommands = {NewProjectCliCommand.class, GenerateCrudCliCommand.class, CommandLine.HelpCommand.class})
public class CodeGenCli implements Runnable {

    @CommandLine.Spec
    CommandLine.Model.CommandSpec spec;

    @Override
    public void run() {
        System.out.println("""
                 ####   ####  #####  ######  ####  ###### #    # ###### #####    ##   #####  ####  ##### \s
                #    # #    # #    # #      #    # #      ##   # #      #    #  #  #    #   #    # #    #\s
                #      #    # #    # #####  #      #####  # #  # #####  #    # #    #   #   #    # #    #\s
                #      #    # #    # #      #  ### #      #  # # #      #####  ######   #   #    # ##### \s
                #    # #    # #    # #      #    # #      #   ## #      #   #  #    #   #   #    # #   # \s
                 ####   ####  #####  ######  ####  ###### #    # ###### #    # #    #   #    ####  #    #\s
                Welcome to CodeGenerator CLI!
                Version: 1.0

                Available commands:
                  codegen new [projectName]  - Create a new project
                  codegen help               - Show help information
                """);
        CommandLine.usage(spec.commandLine(), System.out);
    }
}