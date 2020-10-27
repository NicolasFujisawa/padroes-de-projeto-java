package com.br.fatec.edu.todoapp.todo.view

import com.br.fatec.edu.todoapp.image.view.ResponseImage

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import groovy.transform.TupleConstructor

@EqualsAndHashCode
@TupleConstructor
@ToString
class ResponseTodo {
    Integer id
    String task
    Boolean is_completed
    List<ResponseImage> images
}
