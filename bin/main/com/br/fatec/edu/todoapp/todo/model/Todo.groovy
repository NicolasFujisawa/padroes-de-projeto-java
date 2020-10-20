package com.br.fatec.edu.todoapp.todo.model

import groovy.transform.EqualsAndHashCode
import groovy.transform.TupleConstructor

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = 'todo')
@EqualsAndHashCode
@TupleConstructor
class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id
    
    @Column(name = 'task')
    String task
    
    @Column(name = 'is_completed')
    Boolean isCompleted
}