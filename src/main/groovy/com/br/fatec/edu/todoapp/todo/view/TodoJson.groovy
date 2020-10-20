package com.br.fatec.edu.todoapp.todo.view

import com.br.fatec.edu.todoapp.image.view.ImageJson
import groovy.transform.EqualsAndHashCode
import groovy.transform.TupleConstructor

@EqualsAndHashCode
@TupleConstructor
class TodoJson {
    Integer id
    String task
    Boolean is_complete
    List<ImageJson> images
}
