package se.ecutb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.ecutb.dto.PersonDto;
import se.ecutb.dto.PersonDtoWithTodo;
import se.ecutb.dto.TodoDto;
import se.ecutb.model.Person;
import se.ecutb.model.Todo;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonDtoConversionServiceImpl implements PersonDtoConversionService {

    @Autowired
    public PersonDtoConversionServiceImpl() {
    }

    @Override
    public PersonDto convertToPersonDto(Person person) {
        return new PersonDto(person.getPersonId(), person.getFirstName(), person.getLastName());
    }

    @Override
    public PersonDtoWithTodo convertToPersonDtoWithTodo(Person person, List<Todo> assignedTodos) {
        List<TodoDto> assigned = new ArrayList<>();
        for (Todo todo: assignedTodos) {
            TodoDtoConversionServiceImpl todoDto = new TodoDtoConversionServiceImpl();
            assigned.add(todoDto.convertToDto(todo));
        }
        return new PersonDtoWithTodo(person.getPersonId(), person.getFirstName(), person.getLastName(), assigned);
    }
}
