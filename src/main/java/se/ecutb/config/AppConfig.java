package se.ecutb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import se.ecutb.data.*;
import se.ecutb.service.*;

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

    @Bean
    public PersonDtoConversionService personDtoConversionService() { return new PersonDtoConversionServiceImpl(); }

    @Bean
    public TodoDtoConversionService todoDtoConversionService() { return new TodoDtoConversionServiceImpl(); }

}
