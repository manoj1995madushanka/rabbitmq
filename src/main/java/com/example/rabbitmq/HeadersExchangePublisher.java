package com.example.rabbitmq;


import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * contains logic for publish headers exchange message
 * */
public class HeadersExchangePublisher {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        String message = "Message to Mobile and AC exchange";

        // creating property list
        Map<String,Object> headersMap = new HashMap<>();
        headersMap.put("item1","mobile");
        headersMap.put("item2","television");

        AMQP.BasicProperties basicProperties = new AMQP.BasicProperties();
        basicProperties = basicProperties.builder().headers(headersMap).build();


        channel.basicPublish("Headers-Exchange","",basicProperties,message.getBytes(StandardCharsets.UTF_8));

        channel.close();
        connection.close();
    }
}
