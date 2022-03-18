package com.pn;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class DirectExchangeChannel {
    private String exchangeName;
    private Channel channel;
    private Connection connection;


    public DirectExchangeChannel( Connection connection, String exchangeName) throws IOException {
        this.exchangeName = exchangeName;
        this.channel = connection.createChannel();
        this.connection = connection;
    }



    public void declareExchange() throws IOException {
        //      exchangeDeclare(exchange, builtinExangeType (direct, fanout, pub/sub), durable)
        channel.exchangeDeclare(exchangeName, BuiltinExchangeType.DIRECT, true);

    }

    public void declareQueues(String ...queueNames) throws IOException {
        for(String queue: queueNames){
            //queueDecalre    - (queuename, durable, exclusive, autoDelete, argument)
            channel.queueDeclare(queue, true, false, false, null);
            //channel.queueDelete(queue);
        }
    }

    public void performQueueBinding(String queueName, String routingKey) throws IOException {
        // create binding - (queue, exchange, routingkey)
        channel.queueBind(queueName, exchangeName, routingKey);
    }

    public void subcribeMessage(String queueName) throws IOException {
        //basicConsume (queue, deliverCallback, cancelCallback)
        //deliverCallback: notified when a message is delivered
        //cancelCallback: called when the consumer is cancelled for reason than by a call to channel
        channel.basicConsume(queueName, ((consumerTag, message)-> {
            System.out.println("[received] [" + queueName + "]:" + consumerTag);
            System.out.println("[received] [" + queueName + "]:" + new String(message.getBody()));
        }), consumerTag -> {
            System.out.println("Comsumer tag " + consumerTag);
        });
    }

    public void publishMessage(String message, String routingKey) throws IOException {
        System.out.println("[send] ["+ routingKey+"]: " + message);
        channel.basicPublish(exchangeName, routingKey, null,
                            message.getBytes(StandardCharsets.UTF_8));
    }

}
