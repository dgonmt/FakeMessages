package xyz.gdom.fakemessages.Person;


import net.datafaker.Faker;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;


@Service
public class PersonService {

    Faker faker = new Faker();

    public Person generateFullName() {
        return new Person(
                faker.name().fullName(),
                faker.finance().iban(),
                faker.address().fullAddress());
    }

    public Stream<Person> generateStream() {
        return faker.stream(
                () -> {
                    var p =  new Person(
                            faker.name().fullName(),
                            faker.finance().iban(),
                            faker.address().fullAddress());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return p;})
                .generate();
    }
}