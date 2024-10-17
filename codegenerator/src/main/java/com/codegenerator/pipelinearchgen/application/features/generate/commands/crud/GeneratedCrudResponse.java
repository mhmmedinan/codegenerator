package com.codegenerator.pipelinearchgen.application.features.generate.commands.crud;

import com.codegenerator.core.application.commands.BaseStreamCommandResponse;
import lombok.Getter;
import lombok.Setter;
import java.util.Collection;

@Getter
@Setter
public class GeneratedCrudResponse extends BaseStreamCommandResponse {
    private Collection<String> newFilePathsResult;
    private Collection<String> updatedFilePathsResult;
}
