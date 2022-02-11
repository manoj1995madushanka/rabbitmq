package com.example.rabbitmq.directexchange;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * contain logic for publish message to direct exchange queue
 *
 * */
public class DirectExchangePublisher {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        String message = "Message to Mobile exchange";

        // exchange, routing key, properties, body
        channel.basicPublish("Direct-Exchange","mobile",null,message.getBytes(StandardCharsets.UTF_8));

        channel.close();
        connection.close();
    }
}
