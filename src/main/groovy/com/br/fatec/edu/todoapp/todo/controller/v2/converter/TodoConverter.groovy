package com.br.fatec.edu.todoapp.todo.controller.v2.converter

import com.br.fatec.edu.todoapp.todo.model.Todo
import com.br.fatec.edu.todoapp.todo.view.TodoJson

class TodoConverter {
    static Todo convertFrom(TodoJson todoJson) {
        Todo todo = new Todo()
        todo.setTask(todoJson.task)
        todo.setIsCompleted(todoJson.is_complete)
        todo.setImages()
        return todo
    }
}
