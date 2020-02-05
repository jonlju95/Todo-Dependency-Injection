package se.ecutb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import se.ecutb.data.PersonRepository;
import se.ecutb.data.PersonRepositoryImpl;
import se.ecutb.data.TodoRepository;
import se.ecutb.data.TodoRepositoryImpl;

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
}
