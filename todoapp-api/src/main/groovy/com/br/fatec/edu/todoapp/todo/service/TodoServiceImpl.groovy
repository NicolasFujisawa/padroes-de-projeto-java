package com.br.fatec.edu.todoapp.todo.service

import com.br.fatec.edu.todoapp.todo.model.Todo
import com.br.fatec.edu.todoapp.todo.repository.TodoRepository

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class TodoServiceImpl implements TodoService {
    
    @Autowired
    TodoRepository todoRepository
    
    @Override
    Page<Todo> findAll(Pageable pageable) {
        return todoRepository.findAll(pageable)
    }

    @Override
    Todo findById(Integer todoId) {
        if(todoRepository.existsById(todoId)) {
            return todoRepository.findById(todoId).get()
        }
    }

    @Override
    Todo saveTodo(Todo todo) {
        todoRepository.save(todo)
    }

    @Override
    void deleteTodoById(Integer todoId) {
        if(todoRepository.existsById(todoId)) {
            todoRepository.deleteById(todoId)
        }
    }
}
