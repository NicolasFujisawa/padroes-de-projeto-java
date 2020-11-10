package com.br.fatec.edu.todoapp.todo.repository

import com.br.fatec.edu.todoapp.todo.model.Todo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TodoRepository extends JpaRepository<Todo, Integer> {
}