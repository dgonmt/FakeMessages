package xyz.gdom.fakedatagenerator.person;

import net.datafaker.Faker;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.time.Duration;

@Service
public class PersonService {

    Faker faker = new Faker();

    public Mono<Person> generatePerson() {
        return Mono.just(new Person(
                faker.name().fullName(),
                faker.finance().iban(),
                faker.address().fullAddress()));
    }

    public Flux<Person> generatePeopleWithIntervalUnlimited(long intervalMillis) {
        return Flux.interval(Duration.ofMillis(intervalMillis)).flatMap(p -> generatePerson());
    }

    public Flux<Person> generatePeopleWithIntervalAndLimit(long intervalMillis, int limit) {
        return Flux.interval(Duration.ofMillis(intervalMillis)).flatMap(p -> generatePerson()).take(limit);
    }

    public Flux<Person> generatePeople(int limit) {
        return Flux.range(1, limit).flatMap(p -> generatePerson()).take(limit);
    }
}