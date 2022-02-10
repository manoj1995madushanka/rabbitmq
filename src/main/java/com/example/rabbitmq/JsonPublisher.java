package com.example.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class JsonPublisher {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("from_date", "21-02-2022");
        jsonObject.put("to_date", "21-03-2022");
        jsonObject.put("email", "abc@gmail.com");
        jsonObject.put("query", "select * from data");

        // publish json object to Queue-1
        channel.basicPublish("", "Queue-1", null, jsonObject.toString().getBytes(StandardCharsets.UTF_8));

        channel.close();
        connection.close();
    }
}
