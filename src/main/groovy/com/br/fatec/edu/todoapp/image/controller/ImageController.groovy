package com.br.fatec.edu.todoapp.image.controller

import com.br.fatec.edu.todoapp.image.service.ImageStorageService

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.Resource
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/uploads")
class ImageController {

    @Autowired
    ImageStorageService imageStorageService

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/files/{filename:.+}")
    ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = imageStorageService.load(filename)
        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file)
    }
    
}
