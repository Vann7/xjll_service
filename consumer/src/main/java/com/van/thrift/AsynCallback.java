package com.van.thrift;

import org.apache.thrift.async.AsyncMethodCallback;

import java.util.concurrent.CountDownLatch;



public class AsynCallback implements AsyncMethodCallback<com.van.thrift.user.User> {


    private CountDownLatch latch;


    public AsynCallback(CountDownLatch latch) {
        this.latch  = latch;
    }

    @Override
    public void onComplete(com.van.thrift.user.User response) {
        System.out.println("onComplete");
        try {
            // Thread.sleep(1000L * 1);
            System.out.println("AsynCall result =:" + response);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            latch.countDown();
        }
    }

    @Override
    public void onError(Exception e) {
        System.out.println("onError :" + e.getMessage());
        latch.countDown();
    }
}
