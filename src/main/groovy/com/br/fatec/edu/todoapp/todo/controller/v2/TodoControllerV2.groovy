package com.br.fatec.edu.todoapp.todo.controller.v2

import com.br.fatec.edu.todoapp.todo.controller.v2.converter.TodoConverter
import com.br.fatec.edu.todoapp.todo.model.Todo
import com.br.fatec.edu.todoapp.todo.service.TodoService
import com.br.fatec.edu.todoapp.todo.view.TodoJson
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("v2/todo")
class TodoControllerV2 {
    
    @Autowired
    TodoService todoService
    
    @GetMapping
    ResponseEntity<Object> getAllTodoList() {
        List<TodoJson> todosJson = TodoConverter.convertAllFromTodo(todoService.findAll())
        System.out.println(todosJson) 
        ResponseEntity.ok(todosJson)
    }
    
    @PostMapping
    ResponseEntity<TodoJson> saveTodo(@RequestBody TodoJson todoJson) {
        Todo todo = TodoConverter.convertFromJson(todoJson)
        TodoJson responseJson = TodoConverter.convertFromTodo(todoService.saveTodo(todo))
        ResponseEntity.created().body(responseJson)
    }

    @PutMapping('/{todoId}')
    ResponseEntity<TodoJson> updateTodo(@RequestBody TodoJson todoJson, @PathVariable Integer todoId){
        Todo todo = TodoConverter.convertFromJson(todoJson)
        todo.setId(todoId)
        TodoJson responseJson = TodoConverter.convertFromTodo(todoService.saveTodo(todo))
        ResponseEntity.ok(responseJson)
    }

    @DeleteMapping('/{todoId}')
    ResponseEntity<Void> deleteTodo(@PathVariable Integer todoId){
        todoService.deleteTodoById(todoId)
        ResponseEntity.noContent().build()
    }

    @GetMapping('/{todoId}')
    ResponseEntity<TodoJson> getTodoById(@PathVariable Integer todoId){
        Todo saved = todoService.findById(todoId)
        if(!saved) {
            return ResponseEntity.notFound().build()
        }
        ResponseEntity.ok(TodoConverter.convertFromTodo(saved))
    }
    
}
