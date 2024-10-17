package com.codegenerator;

import com.codegenerator.pipelinearchgen.cli.commands.New.NewProjectCommand;
import com.codegenerator.pipelinearchgen.cli.commands.New.PipelineArchCli;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import picocli.CommandLine;

@SpringBootApplication
public class CodeGeneratorApplication {

    public static void main(String[] args) {

		SpringApplication.run(CodeGeneratorApplication.class, args);
		int exitCode = new CommandLine(new PipelineArchCli()).execute(args);
		System.exit(exitCode);
	}

}
