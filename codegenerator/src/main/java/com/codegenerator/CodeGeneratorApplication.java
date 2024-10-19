package com.codegenerator;

import com.codegenerator.pipelinearchgen.cli.commands.New.PipelineArchCli;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import picocli.CommandLine;

import java.net.URISyntaxException;

@SpringBootApplication
public class CodeGeneratorApplication {

    public static void main(String[] args) throws URISyntaxException {

		SpringApplication.run(CodeGeneratorApplication.class, args);
		int exitCode = new CommandLine(new PipelineArchCli()).execute(args);
		System.exit(exitCode);
	}


}
