package com.br.fatec.edu.todoapp.todo.service

import com.br.fatec.edu.todoapp.todo.model.Todo
import com.br.fatec.edu.todoapp.todo.repository.TodoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TodoServiceImpl implements TodoService {
    
    @Autowired
    TodoRepository todoRepository
    
    @Override
    List<Todo> findAll() {
        todoRepository.findAll()
    }

    @Override
    Todo findById(Integer todoId) {
        todoRepository.findById todoId get()
    }

    @Override
    Todo saveTodo(Todo todo) {
        todoRepository.save todo
    }

    @Override
    Todo deleteTodoById(Integer todoId) {
        todoRepository.deleteById(todoId)
    }
}
