package com.andersenlab;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

import java.nio.charset.StandardCharsets;

public class Producer {
    private static final String QUEUE = "Hello";
    private static final String MESSAGE = "Hello World!";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE, false, false, false, null);

            channel.basicPublish("", QUEUE, null, MESSAGE.getBytes(StandardCharsets.UTF_8));
            System.out.println("Sent: " + MESSAGE);
        }
    }
}
