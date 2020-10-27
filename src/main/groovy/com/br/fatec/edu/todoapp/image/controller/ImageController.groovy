package com.br.fatec.edu.todoapp.image.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/uploads")
class ImageController {
    
    @GetMapping
    MultipartFile show(@PathVariable String fileName) {
        
    }
    
}
