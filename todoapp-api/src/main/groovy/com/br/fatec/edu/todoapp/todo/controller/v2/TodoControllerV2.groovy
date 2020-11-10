package com.br.fatec.edu.todoapp.todo.controller.v2

import com.br.fatec.edu.todoapp.todo.view.TodoConverter
import com.br.fatec.edu.todoapp.todo.model.Todo
import com.br.fatec.edu.todoapp.todo.service.TodoService
import com.br.fatec.edu.todoapp.todo.view.ResponseTodo
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
        List<ResponseTodo> todosJson = TodoConverter.renderManyFromTodo(todoService.findAll())
        System.out.println(todosJson) 
        ResponseEntity.ok(todosJson)
    }
    
    @PostMapping
    ResponseEntity<ResponseTodo> saveTodo(@RequestBody ResponseTodo todoJson) {
        Todo todo = TodoConverter.renderFromJson(todoJson)
        ResponseTodo responseJson = TodoConverter.renderFromTodo(todoService.saveTodo(todo))
        ResponseEntity.created().body(responseJson)
    }

    @PutMapping('/{todoId}')
    ResponseEntity<ResponseTodo> updateTodo(@RequestBody ResponseTodo todoJson, @PathVariable Integer todoId){
        Todo todo = TodoConverter.renderFromJson(todoJson)
        todo.setId(todoId)
        ResponseTodo responseJson = TodoConverter.renderFromTodo(todoService.saveTodo(todo))
        ResponseEntity.ok(responseJson)
    }

    @DeleteMapping('/{todoId}')
    ResponseEntity<Void> deleteTodo(@PathVariable Integer todoId){
        todoService.deleteTodoById(todoId)
        ResponseEntity.noContent().build()
    }

    @GetMapping('/{todoId}')
    ResponseEntity<ResponseTodo> getTodoById(@PathVariable Integer todoId){
        Todo saved = todoService.findById(todoId)
        if(!saved) {
            return ResponseEntity.notFound().build()
        }
        ResponseEntity.ok(TodoConverter.renderFromTodo(saved))
    }
    
}
