package com.pn;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class App {
    public static void main(String[] args) throws IOException, TimeoutException {
        //create producer
        Producer producer = new Producer();
        producer.start();

        //publich some message
        producer.send("this message for all dev", Constant.DEV_ROUTING_KEY);
        producer.send("this message for all manager", Constant.MANAGER_ROUTING_KEY);
        producer.send("this message for everyone", Constant.GENERAL_ROUTING_KEY);

        Consumer consumer = new Consumer();
        consumer.start();
        consumer.subscribe();
    }
}
