package se.ecutb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.ecutb.dto.TodoDto;
import se.ecutb.model.Person;
import se.ecutb.model.Todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class TodoServiceImpl implements TodoService {
    private List<Todo> todoList = new ArrayList<>();
    @Autowired private CreateTodoService todoService;
    @Autowired private TodoDtoConversionService todoDtoConversionService;


    @Override
    public Todo createTodo(String taskDescription, LocalDate deadLine, Person assignee) {
        Todo newTodo = null;
        try {
            newTodo = todoService.createTodo(taskDescription, deadLine, assignee);
            todoList.add(newTodo);
            System.out.println(newTodo);
            return newTodo;
        }catch (IllegalArgumentException e) {
            return null;
        }
        finally {
            todoList.add(newTodo);   
        }
    }

    @Override
    public TodoDto findById(int todoId) throws IllegalArgumentException {
        if (!todoList.contains(todoId)){
            throw new IllegalArgumentException();
        }
        try {
            for (Todo todo: todoList) {
                if (todo.getTodoId()==todoId) {
                    return todoDtoConversionService.convertToDto(todo);
                }
            }
        }catch (IllegalArgumentException e) {
            return null;
        }
        return null;
    }

    @Override
    public List<TodoDto> findByTaskDescription(String taskDescription) {
        List<TodoDto> wantedDesc = new ArrayList<>();
        for (Todo todo:todoList) {
            if (todo.getTaskDescription().toLowerCase().contains(taskDescription.toLowerCase())) {
                wantedDesc.add(todoDtoConversionService.convertToDto(todo));
            }
        }
        return wantedDesc;
    }

    @Override
    public List<TodoDto> findByDeadLineBefore(LocalDate endDate) {
        List<TodoDto> wantedBefore = new ArrayList<>();
        for (Todo todo:todoList) {
            if (todo.getDeadLine().isBefore(endDate)) {
                wantedBefore.add(todoDtoConversionService.convertToDto(todo));
            }
        }
        return wantedBefore;
    }

    @Override
    public List<TodoDto> findByDeadLineAfter(LocalDate startDate) {
        List<TodoDto> wantedAfter = new ArrayList<>();
        for (Todo todo:todoList) {
            if (todo.getDeadLine().isAfter(startDate)) {
                wantedAfter.add(todoDtoConversionService.convertToDto(todo));
            }
        }
        return wantedAfter;
    }

    @Override
    public List<TodoDto> findByDeadLineBetween(LocalDate startDate, LocalDate endDate) {
        List<TodoDto> wantedBetween = new ArrayList<>();
        for (Todo todo:todoList) {
            if (todo.getDeadLine().isBefore(endDate) && todo.getDeadLine().isAfter(startDate)) {
                wantedBetween.add(todoDtoConversionService.convertToDto(todo));
            }
        }
        return wantedBetween;
    }

    @Override
    public List<TodoDto> findAssignedTasksByPersonId(int personId) {
        List<TodoDto> wantedAssignedById = new ArrayList<>();
        for (Todo todo:todoList) {
            if (todo.getAssignee().getPersonId()==personId) {
                wantedAssignedById.add(todoDtoConversionService.convertToDto(todo));
            }
        }
        return wantedAssignedById;
    }

    @Override
    public List<TodoDto> findUnassignedTasks() {
        List<TodoDto> wantedUnassigned = new ArrayList<>();
        for (Todo todo:todoList) {
            if (todo.getAssignee()==null) {
                wantedUnassigned.add(todoDtoConversionService.convertToDto(todo));
            }
        }
        return wantedUnassigned;
    }

    @Override
    public List<TodoDto> findAssignedTasks() {
        List<TodoDto> wantedAssigned = new ArrayList<>();
        for (Todo todo:todoList) {
            if (todo.getAssignee()!=null) {
                wantedAssigned.add(todoDtoConversionService.convertToDto(todo));
            }
        }
        return wantedAssigned;
    }

    @Override
    public List<TodoDto> findByDoneStatus(boolean done) {
        List<TodoDto> wantedDone = new ArrayList<>();
        for (Todo todo:todoList) {
            if (todo.isDone()==done) {
                wantedDone.add(todoDtoConversionService.convertToDto(todo));
            }
        }
        return wantedDone;
    }

    @Override
    public List<TodoDto> findAll() {
        List<TodoDto> allWanted = new ArrayList<>();
        for (Todo todo: todoList) {
            allWanted.add(todoDtoConversionService.convertToDto(todo));
        }
        return allWanted;
    }

    @Override
    public boolean delete(int todoId) throws IllegalArgumentException {
        if (!todoList.contains(todoId)) {
            throw new IllegalArgumentException();
        }
        try {
            for (Todo todo: todoList) {
                if (todo.getTodoId()==todoId) {
                    todoList.remove(todo);
                    return true;
                }
            }
        }catch (IllegalArgumentException e) {
            return false;
        }
        return false;
    }
}
