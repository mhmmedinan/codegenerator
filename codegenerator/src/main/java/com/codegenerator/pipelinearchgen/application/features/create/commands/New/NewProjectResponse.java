package com.codegenerator.pipelinearchgen.application.features.create.commands.New;

import com.codegenerator.core.application.commands.BaseStreamCommandResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
public class NewProjectResponse extends BaseStreamCommandResponse {
    private Collection<String> newFilePathsResult;
    private Collection<String> updatedFilePathsResult;
}
