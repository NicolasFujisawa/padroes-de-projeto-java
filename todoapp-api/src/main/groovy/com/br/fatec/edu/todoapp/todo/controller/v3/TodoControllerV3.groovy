package com.br.fatec.edu.todoapp.todo.controller.v3

import javax.servlet.ServletContext

import com.br.fatec.edu.todoapp.image.model.Image
import com.br.fatec.edu.todoapp.image.service.ImageStorageService
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
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
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

    @Autowired
    ImageStorageService imageStorageService

    @GetMapping
    @CrossOrigin(origins = "*")
    ResponseEntity<Object> list(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        LOGGER.info("Listing todos in page")
        Page<Todo> todos = todoService.findAll(PageRequest.of(page, size, Sort.by("id").descending()))
        List<ResponseTodo> todosJson = TodoConverter.renderManyFromTodo(todos as List<Todo>)
        LOGGER.info("Total of todo read: ${todosJson.size()}")
        return ResponseEntity.ok(todosJson)
    }

    @PostMapping
    @CrossOrigin(origins = "*")
    ResponseEntity<ResponseTodo> create(@ModelAttribute RequestTodo request) {
        LOGGER.info("Creating ${request.task} todo")
        Todo todo = TodoConverter.renderFromJson(request)
        int i = 0
        for(MultipartFile image : request.images) {
            LOGGER.info("File name: ${image.getOriginalFilename()}")
            if(image.contentType.equalsIgnoreCase("image/jpg")
                    || image.getContentType().equalsIgnoreCase("image/jpeg")
                    || image.getContentType().equalsIgnoreCase("image/png")) {
                imageStorageService.save(image, todo.images.get(i++).path)
            }
        }

        for(Image image : todo.images) {
            image.setTodo(todo)
        }

        Todo savedTodo = todoService.saveTodo(todo)
        LOGGER.info("[${savedTodo.id}] todo saved with ${savedTodo.images.size()} images")
        return ResponseEntity.created().body(TodoConverter.renderFromTodo(savedTodo))
    }

    @GetMapping("/{todoId}")
    @CrossOrigin(origins = "*")
    ResponseEntity<ResponseTodo> show(@PathVariable Integer todoId) {
        LOGGER.info("Showing [${todoId}] todo")
        Todo todo = todoService.findById(todoId)
        if(!todo) {
            return ResponseEntity.notFound().build()
        }
        return ResponseEntity.ok(TodoConverter.renderFromTodo(todo))
    }

    @DeleteMapping
    @CrossOrigin(origins = "*")
    ResponseEntity<ResponseTodo> delete(@RequestParam("id") Integer todoId) {
        LOGGER.info("Deleting [${todoId}] todo")
        todoService.deleteTodoById(todoId)
        return ResponseEntity.noContent().build()
    }

    @PutMapping
    @CrossOrigin(origins = "*")
    ResponseEntity<ResponseTodo> edit(@RequestParam("id") Integer todoId) {
        LOGGER.info("Editing [${todoId}] todo")
        Todo todo = todoService.findById(todoId)
        if (!todo) {
            return ResponseEntity.notFound().build()
        }
        todo.setIsCompleted(true)
        todoService.saveTodo(todo)
        return ResponseEntity.ok().build()
    }
}
