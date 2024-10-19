package com.codegenerator.pipelinearchgen.application.features.create.commands.New;

import java.util.*;
import java.util.concurrent.SubmissionPublisher;


public class NewProjectCommandHandler extends SubmissionPublisher<NewProjectResponse> {

    public void handle(NewProjectCommand request){

        try {
            NewProjectResponse response = new NewProjectResponse();
            List<String> newFilePaths = new ArrayList<>();
            List<String> updatedFilePaths = new ArrayList<>();

            newFilePaths.addAll(DirectoryCreator.handle(request));
            response.setLastOperationMessage("Files have been created.");
            this.submit(response);
            response.setNewFilePathsResult(newFilePaths);
            response.setUpdatedFilePathsResult(updatedFilePaths);
            this.close();
        }
        catch (Exception e){
            this.closeExceptionally(e);
        }
    }

}
