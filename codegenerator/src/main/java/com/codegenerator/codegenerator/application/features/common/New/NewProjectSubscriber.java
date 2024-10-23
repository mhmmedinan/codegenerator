package com.codegenerator.codegenerator.application.features.common.New;

import com.codegenerator.codegenerator.application.features.common.responses.BaseResponse;

import java.util.concurrent.Flow;

public class NewProjectSubscriber implements Flow.Subscriber<BaseResponse>{

    private Flow.Subscription subscription;

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(BaseResponse response) {


        if (response.getLastOperationMessage() != null) {
            System.out.println(response.getLastOperationMessage());
        }
        if (response.getNewFilePathsResult() != null) {
            System.out.println("Created files:");
            response.getNewFilePathsResult().forEach(System.out::println);
        }
        if (response.getUpdatedFilePathsResult() != null) {
            System.out.println("Updated files:");
            response.getUpdatedFilePathsResult().forEach(System.out::println);
        }

        subscription.request(1);
    }
    @Override
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }

    @Override
    public void onComplete() {

    }
}
