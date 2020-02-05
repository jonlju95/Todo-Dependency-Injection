package se.ecutb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.ecutb.data.IdSequencersImpl;
import se.ecutb.model.AbstractTodoFactory;
import se.ecutb.model.Person;
import se.ecutb.model.Todo;

import java.time.LocalDate;

@Service
public class CreateTodoServiceImpl extends AbstractTodoFactory implements CreateTodoService {
    private IdSequencersImpl sequencers = new IdSequencersImpl();

    @Autowired
    public CreateTodoServiceImpl() {
    }

    @Override
    public Todo createTodo(String taskDescription, LocalDate deadLine, Person assignee) throws IllegalArgumentException {
        if (taskDescription == null || deadLine == null || assignee == null) {
            throw new IllegalArgumentException();
        }
        return createTodoItem(sequencers.nextTodoId(), taskDescription, deadLine, assignee);
    }

    @Override
    public Todo createTodo(String taskDescription, LocalDate deadLine) throws IllegalArgumentException {
        if (taskDescription == null || deadLine == null) {
            throw new IllegalArgumentException();
        }
        return createTodoItem(sequencers.nextTodoId(), taskDescription, deadLine);
    }
}
