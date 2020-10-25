package todo


import com.br.fatec.edu.todoapp.todo.model.Todo
import com.br.fatec.edu.todoapp.todo.service.TodoService

import jdk.internal.net.http.Response
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.event.annotation.BeforeTestClass

class TestTodo {
    
    @Autowired
    TodoService todoService

    static API_ROOT = "http://localhost:8080/v3/todo"
    static readingTodoId
    static writingTodoId
    
    @BeforeTestClass
    testCascadePersist() {
        Todo readingTodo = new Todo(task: 'Reading', isCompleted: false)
        Todo writingTodo = new Todo(task: 'Writing', isCompleted: false)

        final Response readingResponse =
            RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(readingTodo).post(API_ROOT)

        Todo cookingTodoResponse = readingResponse.as Todo.class
        readingTodoId = cookingTodoResponse.getId()

        final Response writingResponse =
            RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(writingTodo).post(API_ROOT)

        Todo writingTodoResponse = writingResponse.as Todo.class
        writingTodoId = writingTodoResponse.getId()
    }
}
