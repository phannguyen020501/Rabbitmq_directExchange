package com.pn;

import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer {
    private DirectExchangeChannel directChannel;

    public void start() throws IOException, TimeoutException {
        //create connection
        Connection connection = ConnectionManager.createConnection();

        //create channel
        directChannel = new DirectExchangeChannel(connection, Constant.EXCHANGE_NAME);

        //create direct exchange
        directChannel.declareExchange();

        //create queues
        directChannel.declareQueues(Constant.DEV_QUEUE_NAME,Constant.MANAGER_QUEUE_NAME,
                Constant.GENERAL_QUEUE_NAME);

        //binding queues with routing keys
        directChannel.performQueueBinding(Constant.DEV_QUEUE_NAME, Constant.DEV_ROUTING_KEY);
        directChannel.performQueueBinding(Constant.MANAGER_QUEUE_NAME, Constant.MANAGER_ROUTING_KEY);
        directChannel.performQueueBinding(Constant.GENERAL_QUEUE_NAME, Constant.GENERAL_ROUTING_KEY);

    }

    public void subscribe() throws IOException {
        //subsribe messsage
        directChannel.subcribeMessage(Constant.DEV_QUEUE_NAME);
        directChannel.subcribeMessage(Constant.MANAGER_QUEUE_NAME);
        directChannel.subcribeMessage(Constant.GENERAL_QUEUE_NAME);

    }
}
