package xyz.gdom.fakemessages.fakemessages;

import lombok.Setter;
import net.datafaker.Faker;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import xyz.gdom.fakemessages.Person.Person;
import xyz.gdom.fakemessages.Person.PersonService;

import java.util.stream.Stream;

@Setter
@Service
public class MessageSender {

    @Value("${queue.name}")
    private String queueName;

    @Autowired
    private PersonService personService;

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private MessageConverter messageConverter;

    Faker faker = new Faker();

    public void send(String message) {
        template.convertAndSend(queueName, message);
    }

    public void sendFakePerson() {
        template.setMessageConverter(messageConverter);
        template.convertAndSend(queueName, personService.generateFullName());
    }

    public Stream<Person> generateStream(long interval) {
        return faker.stream(
                        () -> {
                            var p =  new Person(
                                    faker.name().fullName(),
                                    faker.finance().iban(),
                                    faker.address().fullAddress());
                            try {
                                Thread.sleep(interval);
                                template.setMessageConverter(messageConverter);
                                template.convertAndSend(queueName, p);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            return p;})
                .generate();
    }
}
