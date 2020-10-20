package com.br.fatec.edu.todoapp.image.model

import com.br.fatec.edu.todoapp.todo.model.Todo
import groovy.transform.EqualsAndHashCode
import groovy.transform.TupleConstructor

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = 'images')
@EqualsAndHashCode
@TupleConstructor
class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id
    
    @Column(name = "path")
    String path

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "todo_id", nullable = false)
    Todo todo
}
