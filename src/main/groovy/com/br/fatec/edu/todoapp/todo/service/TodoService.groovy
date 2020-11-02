package com.br.fatec.edu.todoapp.todo.service

import com.br.fatec.edu.todoapp.todo.model.Todo

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface TodoService {
    Page<Todo> findAll(Pageable pageable)

    Todo findById(Integer todoId)

    Todo saveTodo(Todo todo)
    
    void deleteTodoById(Integer todoId)
}
