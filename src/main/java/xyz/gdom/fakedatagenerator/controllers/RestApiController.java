package xyz.gdom.fakedatagenerator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import xyz.gdom.fakedatagenerator.person.Person;
import xyz.gdom.fakedatagenerator.person.PersonService;

import static org.springframework.http.MediaType.TEXT_EVENT_STREAM_VALUE;

@RestController
public class RestApiController {

    @Autowired
    private PersonService personService;

    @GetMapping(value = "/stream/people/unlimited/interval/{interval}", produces = TEXT_EVENT_STREAM_VALUE)
    public Flux<Person> streamFakePeopleUnlimited(@PathVariable int limit, @PathVariable long interval) {
        return personService.generatePeopleWithIntervalAndLimit(interval, limit).log();
    }

    @GetMapping(value = "/stream/people/limit/{limit}/interval/{interval}", produces = TEXT_EVENT_STREAM_VALUE)
    public Flux<Person> streamFakePeopleWithLimit(@PathVariable int limit, @PathVariable long interval) {
        return personService.generatePeopleWithIntervalAndLimit(interval, limit).log();
    }

    @GetMapping("/get/people/limit/{limit}")
    public Flux<Person> getFakePeopleWithLimit(@PathVariable int limit) {
        return personService.generatePeople(limit).log();
    }
}