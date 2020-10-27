package com.br.fatec.edu.todoapp.todo.view

import com.br.fatec.edu.todoapp.image.view.ImageConverter
import com.br.fatec.edu.todoapp.todo.model.Todo

class TodoConverter {
    static Todo renderFromJson(RequestTodo todoJson) {
        Todo todo = new Todo()
        todo.setTask(todoJson.task)
        todo.setIsCompleted(todoJson.is_completed)
        todo.setImages(ImageConverter.renderManyFromRequest(todoJson.images))
        print(todo)
        return todo
    }
    
    static List<Todo> renderManyFromJson(List<RequestTodo> todosJson) {
        List<Todo> todos = new ArrayList<>()
        for(RequestTodo todoJson : todosJson) {
            todos.add(renderFromJson(todoJson))
        }
        return todos
    }
    
    static ResponseTodo renderFromTodo(Todo todo) {
        ResponseTodo responseTodo = new ResponseTodo()
        responseTodo.setId(todo.id)
        responseTodo.setTask(todo.task)
        responseTodo.setIs_completed(todo.isCompleted)
        responseTodo.setImages(ImageConverter.renderManyFromImage(todo.images))
        return responseTodo
    }
    
    static List<ResponseTodo> renderManyFromTodo(List<Todo> todos) {
        List<ResponseTodo> responseTodo = new ArrayList<>()
        for(Todo todo : todos) {
            responseTodo.add(renderFromTodo(todo))
        }
        return responseTodo
    }
}
