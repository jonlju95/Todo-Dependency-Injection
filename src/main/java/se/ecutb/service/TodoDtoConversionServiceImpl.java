package se.ecutb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.ecutb.dto.TodoDto;
import se.ecutb.model.Todo;

@Service
public class TodoDtoConversionServiceImpl implements TodoDtoConversionService {

    @Autowired
    public TodoDtoConversionServiceImpl() {
    }

    @Override
    public TodoDto convertToDto(Todo todo) {
        Integer assigneeId = 0;
        if (todo.getAssignee()==null) {
            assigneeId=null;
        }

        return new TodoDto(todo.getTodoId(), todo.getTaskDescription(), todo.getDeadLine(), assigneeId, todo.isDone());
    }
}
