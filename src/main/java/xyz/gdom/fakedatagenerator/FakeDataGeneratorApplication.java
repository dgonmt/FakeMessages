package xyz.gdom.fakedatagenerator;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import jakarta.annotation.PostConstruct;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Fake Data Generator"))
public class FakeDataGeneratorApplication {

    @Autowired
    RabbitAdmin admin;

    @Autowired
    Queue queue;

    @PostConstruct
    private void init() {
        admin.declareQueue(queue);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(FakeDataGeneratorApplication.class, args);
    }
}
