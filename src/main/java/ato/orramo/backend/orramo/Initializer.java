package ato.orramo.backend.orramo;

import ato.orramo.backend.orramo.model.Event;
import ato.orramo.backend.orramo.model.Group;
import ato.orramo.backend.orramo.model.GroupRepository;
import org.springframework.boot.CommandLineRunner;

import java.time.Instant;
import java.util.Collections;
import java.util.stream.Stream;

public class Initializer implements CommandLineRunner {
    private final GroupRepository repository;

    public Initializer(GroupRepository repository){
        this.repository = repository;
    }

    @Override
    public void run(String... strings) {
        Stream.of("Denver JUG", "Utah JUG", "Seattle JUG",
                "Richmond JUG").forEach(name ->
                repository.save(new Group(name))
        );

        Group djug = repository.findByName("Denver JUG");
        Event e = Event.builder().title("Full Stack Reactive")
                .description("Reactive with Spring Boot + React")
                .date(Instant.parse("2018-12-12T18:00:00.000Z"))
                .build();
        djug.setEvents(Collections.singleton(e));
        repository.save(djug);

        repository.findAll().forEach(System.out::println);
    }
}
