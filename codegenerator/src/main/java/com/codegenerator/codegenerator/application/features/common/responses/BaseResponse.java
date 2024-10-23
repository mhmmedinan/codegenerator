package com.codegenerator.codegenerator.application.features.common.responses;

import com.codegenerator.core.application.commands.BaseStreamCommandResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
public class BaseResponse extends BaseStreamCommandResponse {
    private Collection<String> newFilePathsResult;
    private Collection<String> updatedFilePathsResult;
}
