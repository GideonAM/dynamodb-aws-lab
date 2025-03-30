package com.amalitech.dydamodbtodoapp.dynamodb_todo_app.controller;

import com.amalitech.dydamodbtodoapp.dynamodb_todo_app.dto.TodoDto;
import com.amalitech.dydamodbtodoapp.dynamodb_todo_app.entity.Todo;
import com.amalitech.dydamodbtodoapp.dynamodb_todo_app.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodos() {
        return ResponseEntity.ok(todoService.findAllTodos());
    }

    @PostMapping
    public ResponseEntity<TodoDto> createTodo(@RequestBody TodoDto todo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(todoService.createTodo(todo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoDto> findById(@PathVariable String id) {
        return ResponseEntity.ok(todoService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable String id, @RequestBody TodoDto todo) {
        return ResponseEntity.ok(todoService.updateTodo(id, todo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable String id) {
        todoService.deleteTodo(id);
        return ResponseEntity.noContent().build();
    }
}
