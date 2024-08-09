package xyz.gdom.fakedatagenerator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import xyz.gdom.fakedatagenerator.message.MessageService;
import xyz.gdom.fakedatagenerator.person.Person;

import static org.springframework.http.MediaType.TEXT_EVENT_STREAM_VALUE;

@RestController
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping(value = "/publish/people/limit/{limit}/interval/{interval}", produces = TEXT_EVENT_STREAM_VALUE)
    public Flux<Person> publishFakePeopleWithLimit(@PathVariable int limit, @PathVariable long interval) {
        return messageService.publishPeopleLimited(interval, limit).log();
    }

    @GetMapping(value = "/publish/people/unlimited/interval/{interval}", produces = TEXT_EVENT_STREAM_VALUE)
    public Flux<Person> publishFakePeopleUnlimited(@PathVariable long interval) {
        return messageService.publishPeopleUnlimited(interval).log();
    }


}