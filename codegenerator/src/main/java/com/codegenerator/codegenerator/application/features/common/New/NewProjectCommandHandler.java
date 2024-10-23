package com.codegenerator.codegenerator.application.features.common.New;

import com.codegenerator.codegenerator.application.features.common.responses.BaseResponse;

import java.util.concurrent.SubmissionPublisher;

public abstract class NewProjectCommandHandler  extends SubmissionPublisher<BaseResponse> {
    public abstract void handle (NewProjectCommand command);
}
