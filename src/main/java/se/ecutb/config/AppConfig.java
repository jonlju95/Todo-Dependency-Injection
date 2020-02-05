package se.ecutb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import se.ecutb.data.*;
import se.ecutb.service.CreatePersonService;
import se.ecutb.service.CreatePersonServiceImpl;
import se.ecutb.service.CreateTodoService;
import se.ecutb.service.CreateTodoServiceImpl;

@Configuration
@ComponentScan("se.ecutb.jonatanl")
public class AppConfig {
    @Bean
    public PersonRepository personRepository() {
        return new PersonRepositoryImpl();
    }

    @Bean
    public TodoRepository todoRepository() {
        return new TodoRepositoryImpl();
    }

    @Bean
    public IdSequencers idSequencers() { return new IdSequencersImpl(); }

    @Bean
    public CreatePersonService createPersonService() { return new CreatePersonServiceImpl(); }

    @Bean
    public CreateTodoService createTodoService() { return new CreateTodoServiceImpl(); }
}
