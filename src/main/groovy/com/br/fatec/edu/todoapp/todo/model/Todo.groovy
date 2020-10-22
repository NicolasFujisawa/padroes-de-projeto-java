package com.br.fatec.edu.todoapp.todo.model

import com.br.fatec.edu.todoapp.image.model.Image
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import groovy.transform.TupleConstructor

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table

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
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "todo")
    List<Image> images
}