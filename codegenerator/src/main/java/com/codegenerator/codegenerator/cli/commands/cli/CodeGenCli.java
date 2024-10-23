package com.codegenerator.codegenerator.cli.commands.cli;

import com.codegenerator.codegenerator.cli.commands.New.NewProjectCliCommand;
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
        if (spec == null || spec.commandLine().getParseResult().originalArgs().isEmpty()) {
            BannerPrinter.printBanner();
            CommandLine.usage(this, System.out);
        } else {
            spec.commandLine().execute(spec.commandLine().getParseResult().originalArgs().toArray(new String[0]));
        }
    }

}