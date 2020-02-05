package se.ecutb.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IdSequencersImpl implements IdSequencers {
    private int personId = 0;
    private int todoId = 0;

    @Autowired
    public IdSequencersImpl() {}

    @Override
    public int nextPersonId() {
        personId++;
        return personId;
    }

    @Override
    public int nextTodoId() {
        todoId++;
        return todoId;
    }

    @Override
    public void clearPersonId() {
        personId = 0;
    }

    @Override
    public void clearTodoId() {
        todoId = 0;
    }
}
