package com.br.fatec.edu.todoapp.image.service

import java.nio.file.Path
import java.util.stream.Stream

import org.springframework.core.io.Resource
import org.springframework.web.multipart.MultipartFile

interface ImageStorageService {
    void init();

    void save(MultipartFile file);

    void save(MultipartFile file, String fileName);
    
    Resource load(String filename);

    void deleteAll();

    Stream<Path> loadAll();
}
