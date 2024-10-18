package com.codegenerator;

import com.codegenerator.core.codegen.file.DirectoryHelper;
import com.codegenerator.core.codegen.helpers.PlatformHelper;
import com.codegenerator.pipelinearchgen.cli.commands.New.NewProjectCommand;
import com.codegenerator.pipelinearchgen.cli.commands.New.PipelineArchCli;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import picocli.CommandLine;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootApplication
public class CodeGeneratorApplication {

    public static void main(String[] args) throws URISyntaxException {

		SpringApplication.run(CodeGeneratorApplication.class, args);
		int exitCode = new CommandLine(new PipelineArchCli()).execute(args);
		System.exit(exitCode);
	}


}
