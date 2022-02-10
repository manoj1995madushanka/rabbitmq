package com.example.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * contains logic for send message for topic exchange
 * */
public class TopicExchangePublisher {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        String message = "Message to Mobile and AC exchange";

        // exchange, routing key, properties, body
        channel.basicPublish("Topic-Exchange","tv.mobile.ac",null,message.getBytes(StandardCharsets.UTF_8));

        channel.close();
        connection.close();
    }
}
