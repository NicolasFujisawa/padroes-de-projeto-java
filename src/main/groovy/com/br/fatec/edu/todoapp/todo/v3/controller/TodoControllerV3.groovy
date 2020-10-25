package com.br.fatec.edu.todoapp.todo.v3.controller


import com.br.fatec.edu.todoapp.todo.model.Todo
import com.br.fatec.edu.todoapp.todo.service.TodoService

import ch.qos.logback.classic.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("v3/todo")
class TodoControllerV3 {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(TodoControllerV3.class)
    
    @Autowired
    TodoService todoService

    @GetMapping
    ResponseEntity<Object> getAllTodoList() {
        LOGGER.info("Getting all todos")
        List<Todo> todos = todoService.findAll()
        LOGGER.info("Total of todo read: ${todos.size()}")
        return ResponseEntity.ok(todos)
    }

    @PostMapping
    ResponseEntity<Todo> saveTodo(@RequestBody Todo todo) {
        LOGGER.info("Saving ${todo.task} todo")
        LOGGER.info("Image:  ${todo.images.size()} images")
        
        Todo response = todoService.saveTodo(todo)
        LOGGER.info("[${response.id}] ${response.task} todo saved with ${response.images.size()} images")
        return ResponseEntity.created().body(response)
    }
}
