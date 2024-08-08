package xyz.gdom.fakemessages;

import jakarta.annotation.PostConstruct;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;


@SpringBootApplication
public class FakeMessagesApplication {

    @Autowired
    RabbitAdmin admin;

    @Autowired
    Queue queue;

    @PostConstruct
    private void init() {
        admin.declareQueue(queue);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(FakeMessagesApplication.class, args);
    }
}
