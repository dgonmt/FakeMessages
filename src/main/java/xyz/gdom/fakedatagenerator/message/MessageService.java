package xyz.gdom.fakedatagenerator.message;

import lombok.Setter;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import xyz.gdom.fakedatagenerator.person.Person;
import xyz.gdom.fakedatagenerator.person.PersonService;

@Setter
@Service
public class MessageService {

    @Value("${queue.name}")
    private String queueName;

    @Autowired
    private PersonService personService;

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private MessageConverter messageConverter;

    public Person publishPerson(Person p) {
        template.setMessageConverter(messageConverter);
        template.convertAndSend(queueName, p);
        return p;
    }

    public Flux<Person> publishPeopleLimited(long intervalMillis, int limit) {
        return personService.generatePeopleWithIntervalAndLimit(intervalMillis, limit).map(this::publishPerson);
    }

    public Flux<Person> publishPeopleUnlimited(long intervalMillis) {
        return personService.generatePeopleWithIntervalUnlimited(intervalMillis).map(this::publishPerson);
    }
}