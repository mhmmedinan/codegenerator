package com.codegenerator.codegenerator.application.features.common.generate;

import com.codegenerator.codegenerator.application.features.common.responses.BaseResponse;
import java.util.concurrent.SubmissionPublisher;

public abstract class GenerateCrudCommandHandler  extends SubmissionPublisher<BaseResponse> {
    public abstract void handle (GenerateCrudCommand command);
}
