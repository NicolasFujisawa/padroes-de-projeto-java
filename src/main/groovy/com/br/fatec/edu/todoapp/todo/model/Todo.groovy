package com.br.fatec.edu.todoapp.todo.model

import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table

import com.br.fatec.edu.todoapp.image.model.Image

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import groovy.transform.TupleConstructor

@Entity
@Table(name = 'todos')
@EqualsAndHashCode
@TupleConstructor
@ToString
class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id
    
    @Column(name = 'task')
    String task
    
    @Column(name = 'is_completed')
    Boolean isCompleted
    
    @OneToMany(mappedBy = "todo", cascade = CascadeType.PERSIST, orphanRemoval = true)
    List<Image> images
}