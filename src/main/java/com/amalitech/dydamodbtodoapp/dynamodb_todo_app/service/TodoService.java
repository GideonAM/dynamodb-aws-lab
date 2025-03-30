package com.amalitech.dydamodbtodoapp.dynamodb_todo_app.service;

import com.amalitech.dydamodbtodoapp.dynamodb_todo_app.dto.TodoDto;
import com.amalitech.dydamodbtodoapp.dynamodb_todo_app.entity.Todo;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final DynamoDBMapper dynamoDBMapper;

    public List<Todo> findAllTodos() {
        return dynamoDBMapper.scan(Todo.class, new DynamoDBScanExpression());
    }

    public TodoDto createTodo(TodoDto todo) {
        Todo newTodo = new Todo(
                null,
                todo.getTitle(),
                todo.getDescription(),
                false,
                String.valueOf(LocalDateTime.now()),
                String.valueOf(LocalDateTime.now())
        );
        dynamoDBMapper.save(newTodo);

        return TodoDto.builder()
                .id(newTodo.getId())
                .title(newTodo.getTitle())
                .description(newTodo.getDescription())
                .completed(newTodo.isCompleted())
                .createdAt(newTodo.getCreatedAt())
                .updatedAt(newTodo.getUpdatedAt())
                .build();
    }

    public TodoDto findById(String id) {
        Todo todo = dynamoDBMapper.load(Todo.class, id);

        if (todo != null)
            return TodoDto.builder()
                    .id(todo.getId())
                    .title(todo.getTitle())
                    .description(todo.getDescription())
                    .completed(todo.isCompleted())
                    .createdAt(todo.getCreatedAt())
                    .updatedAt(todo.getUpdatedAt())
                    .build();

        throw new RuntimeException("Todo not found");
    }

    public Todo updateTodo(String id, TodoDto todo) {
        Todo item = dynamoDBMapper.load(Todo.class, id);

        if (item != null) {
            item.setTitle(todo.getTitle());
            item.setDescription(todo.getDescription());
            item.setCompleted(todo.isCompleted());
            item.setUpdatedAt(String.valueOf(LocalDateTime.now()));
            dynamoDBMapper.save(item);
            return item;
        }

        throw new RuntimeException("Todo not found");
    }

    public void deleteTodo(String id) {
        Todo item = dynamoDBMapper.load(Todo.class, id);
        if (item != null) {
            dynamoDBMapper.delete(item);
            return;
        }
        throw new RuntimeException("Todo not found");
    }
}
