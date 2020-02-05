package se.ecutb.data;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import se.ecutb.model.Todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TodoRepositoryImpl implements TodoRepository {
    private List<Todo> todoList = new ArrayList<>();

    @Override
    public Todo persist(Todo todo) {
        todoList.add(todo);
        return todo;
    }

    @Override
    public Optional<Todo> findById(int todoId) {
        return todoList.stream().filter(todo -> todo.getTodoId()==todoId)
                .findFirst();
    }

    @Override
    public List<Todo> findByTaskDescriptionContains(String taskDescription) {
        List<Todo> descriptions = new ArrayList<>();
        for (Todo todo: todoList) {
            if (todo.getTaskDescription().toLowerCase().contains(taskDescription.toLowerCase())) {
                descriptions.add(todo);
            }
        }
        return descriptions;
    }

    @Override
    public List<Todo> findByDeadLineBefore(LocalDate endDate) {
        List<Todo> deadLineBefore = new ArrayList<>();
        for (Todo todo: todoList) {
            if (todo.getDeadLine().isBefore(endDate)) {
                deadLineBefore.add(todo);
            }
        }
        return deadLineBefore;
    }

    @Override
    public List<Todo> findByDeadLineAfter(LocalDate startDate) {
        List<Todo> deadLineAfter = new ArrayList<>();
        for (Todo todo: todoList) {
            if (todo.getDeadLine().isAfter(startDate)) {
                deadLineAfter.add(todo);
            }
        }
        return deadLineAfter;
    }

    @Override
    public List<Todo> findByDeadLineBetween(LocalDate start, LocalDate end) {
        List<Todo> deadLineBetween = new ArrayList<>();
        for (Todo todo: todoList) {
            if (todo.getDeadLine().isAfter(start) && todo.getDeadLine().isBefore(end)) {
                deadLineBetween.add(todo);
            }
        }
        return deadLineBetween;
    }

    @Override
    public List<Todo> findByAssigneeId(int personId) {
        return todoList.stream().filter(todo -> todo.getTodoId()==personId)
                .collect(Collectors.toList());
    }

    @Override
    public List<Todo> findAllUnassignedTasks() {
        List<Todo> unassigned = new ArrayList<>();
        for (Todo todo: todoList) {
            if (Objects.isNull(todo.getAssignee())) {
                unassigned.add(todo);
            }
        }
        return unassigned;
    }

    @Override
    public List<Todo> findAllAssignedTasks() {
        List<Todo> assigned = new ArrayList<>();
        for (Todo todo: todoList) {
            if (Objects.nonNull(todo.getAssignee())) {
                assigned.add(todo);
            }
        }
        return assigned;
    }

    @Override
    public List<Todo> findByDone(boolean isDone) {
        return todoList.stream().filter(todo -> todo.isDone()==isDone)
                .collect(Collectors.toList());
    }

    @Override
    public List<Todo> findAll() {
        return todoList;
    }

    @Override
    public boolean delete(int todoId) throws IllegalArgumentException {
        for (Todo todo: todoList) {
            if (!todoList.contains(todo)) {
                continue;
            }
            else if(todo.getTodoId()==todoId) {
                todoList.remove(todo);
                return true;
            }
        }
        if (!todoList.contains(todoId)) {
            throw new IllegalArgumentException();
        }
        return false;
    }

    @Override
    public void clear() {
        todoList.clear();
    }
}
