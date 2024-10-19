package com.codegenerator.pipelinearchgen.application.features.create.commands.New;

import java.util.concurrent.Flow;

public class NewProjectSubscriber implements Flow.Subscriber<NewProjectResponse>{

    private Flow.Subscription subscription;

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(NewProjectResponse response) {

        System.out.println("Current Status: " + response.getCurrentStatusMessage());

        if (response.getOutputMessage() != null) {
            System.out.println(response.getOutputMessage());
        }
        if (response.getLastOperationMessage() != null) {
            System.out.println("✔️ " + response.getLastOperationMessage());
        }
        if (response.getNewFilePathsResult() != null) {
            System.out.println("🆕 Created files:");
            response.getNewFilePathsResult().forEach(path -> System.out.println(path));
        }
        if (response.getUpdatedFilePathsResult() != null) {
            System.out.println("🔼 Updated files:");
            response.getUpdatedFilePathsResult().forEach(path -> System.out.println(path));
        }

        subscription.request(1);
    }
    @Override
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }

    @Override
    public void onComplete() {
        System.out.println("Created completed.");
    }
}
