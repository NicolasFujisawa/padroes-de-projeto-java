package com.br.fatec.edu.todoapp.todo.controller.v2.converter

import com.br.fatec.edu.todoapp.todo.model.Todo
import com.br.fatec.edu.todoapp.todo.view.TodoJson

class TodoConverter {
    static Todo convertFromJson(TodoJson todoJson) {
        Todo todo = new Todo()
        todo.setTask(todoJson.task)
        todo.setIsCompleted(todoJson.is_completed)
        todo.setImages(ImageConverter.convertAllJson(todoJson.images))
        print(todo)
        return todo
    }
    
    static List<Todo> convertAllFromJson(List<TodoJson> todosJson) {
        List<Todo> todos = new ArrayList<>()
        for(TodoJson todoJson : todosJson) {
            todos.add(convertFromJson(todoJson))
        }
        return todos
    }
    
    static TodoJson convertFromTodo(Todo todo) {
        TodoJson todoJson = new TodoJson()
        todoJson.setId(todo.id)
        todoJson.setTask(todo.task)
        todoJson.setIs_completed(todo.isCompleted)
        todoJson.setImages(ImageConverter.convertAllFromImage(todo.images))
        return todoJson
    }
    
    static List<TodoJson> convertAllFromTodo(List<Todo> todos) {
        List<TodoJson> todosJson = new ArrayList<>()
        for(Todo todo : todos) {
            todosJson.add(convertFromTodo(todo))
        }
        return todosJson
    }
}
