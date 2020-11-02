package com.br.fatec.edu.todoapp.todo.controller.v3


import com.br.fatec.edu.todoapp.image.model.Image
import com.br.fatec.edu.todoapp.todo.model.Todo
import com.br.fatec.edu.todoapp.todo.service.TodoService
import com.br.fatec.edu.todoapp.todo.view.RequestTodo
import com.br.fatec.edu.todoapp.todo.view.ResponseTodo
import com.br.fatec.edu.todoapp.todo.view.TodoConverter

import ch.qos.logback.classic.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("v3/todo")
class TodoControllerV3 {

    private static final Logger LOGGER = LoggerFactory.getLogger(TodoControllerV3.class) as Logger

    @Autowired
    TodoService todoService

    @GetMapping
    ResponseEntity<Object> list(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        LOGGER.info("Listing all todos")
        Page<Todo> todos = todoService.findAll(PageRequest.of(page, size, Sort.by("id")))
        List<ResponseTodo> todosJson = TodoConverter.renderManyFromTodo(todos as List<Todo>)
        LOGGER.info("Total of todo read: ${todosJson.size()}")
        return ResponseEntity.ok(todosJson)
    }

    @PostMapping
    ResponseEntity<ResponseTodo> create(@ModelAttribute RequestTodo request) {
        LOGGER.info("Creating ${request.task} todo")
        Todo todo = TodoConverter.renderFromJson(request)
    
        for(MultipartFile image : request.images) {
            LOGGER.info("File name: ${image.contentType}")
        }

        for(Image image : todo.images) {
            image.setTodo(todo)
        }

        Todo savedTodo = todoService.saveTodo(todo)
        LOGGER.info("[${savedTodo.id}] ${savedTodo.task} todo saved with ${savedTodo.images.size()} images")
        return ResponseEntity.created().body(TodoConverter.renderFromTodo(savedTodo))
    }
    
    @GetMapping("/{todoId}")
    ResponseEntity<ResponseTodo> show(@PathVariable Integer todoId) {
        LOGGER.info("Showing [${todoId}] todo")
        Todo todo = todoService.findById(todoId)
        if(!todo) {
            return ResponseEntity.notFound().build()
        }
        return ResponseEntity.ok(TodoConverter.renderFromTodo(todo))
    }
    
    @DeleteMapping
    ResponseEntity<ResponseTodo> delete(@RequestParam("id") Integer todoId) {
        LOGGER.info("Deleting [${todoId}] todo")
        todoService.deleteTodoById(todoId)
        return ResponseEntity.noContent().build()
    }
}
