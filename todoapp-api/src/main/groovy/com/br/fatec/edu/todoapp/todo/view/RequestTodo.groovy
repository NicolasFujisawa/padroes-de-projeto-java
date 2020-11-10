package com.br.fatec.edu.todoapp.todo.view


import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import groovy.transform.TupleConstructor
import org.springframework.web.multipart.MultipartFile

@EqualsAndHashCode
@TupleConstructor
@ToString
class RequestTodo {
    Integer id
    String task
    Boolean is_completed
    MultipartFile[] images
}
