package com.br.fatec.edu.todoapp.todo.service

import com.br.fatec.edu.todoapp.todo.model.Todo

interface TodoService {
    List<Todo> findAll()

    Todo findById(Integer todoId)

    Todo saveTodo(Todo todo)
    
    void deleteTodoById(Integer todoId)
}
