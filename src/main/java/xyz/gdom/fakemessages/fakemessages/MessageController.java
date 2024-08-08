package xyz.gdom.fakemessages.fakemessages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import xyz.gdom.fakemessages.Person.Person;

import static org.springframework.http.MediaType.TEXT_EVENT_STREAM_VALUE;

@RestController
public class MessageController {

    @Autowired
    private MessageSender sender;

    @GetMapping(value = "/stream/person/limit/{limit}/interval/{interval}", produces = TEXT_EVENT_STREAM_VALUE)
    public Flux<Person> streamFakePerson(@PathVariable int limit, @PathVariable long interval) {
        return Flux.fromStream(sender.generateStream(interval)).take(limit);
    }
}