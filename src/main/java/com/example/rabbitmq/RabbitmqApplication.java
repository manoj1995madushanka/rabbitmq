package com.example.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * contains publishing message for consumer
 * */
@SpringBootApplication
public class RabbitmqApplication {

	public static void main(String[] args) throws IOException, TimeoutException {
		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection connection = connectionFactory.newConnection();
		Channel channel = connection.createChannel();

		String message = "First message from RabbitMQ";

		// exchange, routing key, properties, body
		channel.basicPublish("","Queue-1",null,message.getBytes(StandardCharsets.UTF_8));

		/**
		 * exchange to exchange message passing
		 * from direct exchange to fanout exchange
		 * */
		String messageExchange = "Exchange to exchange message";
		channel.basicPublish("Direct-Exchange", "fanout", null, messageExchange.getBytes(StandardCharsets.UTF_8));

		channel.close();
		connection.close();
	}

}
