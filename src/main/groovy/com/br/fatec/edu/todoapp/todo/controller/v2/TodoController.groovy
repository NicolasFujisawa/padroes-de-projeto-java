package com.br.fatec.edu.todoapp.todo.controller.v2

import com.br.fatec.edu.todoapp.todo.controller.v2.converter.TodoConverter
import com.br.fatec.edu.todoapp.todo.model.Todo
import com.br.fatec.edu.todoapp.todo.service.TodoService
import com.br.fatec.edu.todoapp.todo.view.TodoJson
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("v2/todo")
class TodoController {
    
    @Autowired
    TodoService todoService
    
    @GetMapping
    ResponseEntity<Object> getAllTodoList() {
        new ResponseEntity<Object>(todoService.findAll(), HttpStatus.OK)  
    }
    
    @PostMapping
    ResponseEntity<Todo> saveTodo(@RequestBody TodoJson todoJson) {
        Todo todo = TodoConverter.convertFrom(todoJson)
        new ResponseEntity<Todo>(todoService.saveTodo(todo), HttpStatus.CREATED)
    }

    @PutMapping('/{todoId}')
    ResponseEntity<Todo> updateTodo(@RequestBody Todo todo, @PathVariable Integer todoId){
        todo.setId todoId
        ResponseEntity.ok todoService.saveTodo(todo) as Todo
    }

    @DeleteMapping('/{todoId}')
    ResponseEntity<Void> deleteTodo(@PathVariable Integer todoId){
        todoService.deleteTodoById todoId
        ResponseEntity.noContent().build()
    }

    @GetMapping('/{todoId}')
    ResponseEntity<Todo> getTodoById(@PathVariable Integer todoId){
        Todo saved = todoService.findById todoId
        if(!saved) {
            return ResponseEntity.notFound().build()
        }
        ResponseEntity.ok saved as Todo
    }
    
}
