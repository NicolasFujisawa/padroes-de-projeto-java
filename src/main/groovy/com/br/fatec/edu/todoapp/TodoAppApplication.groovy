package com.br.fatec.edu.todoapp

import javax.annotation.Resource

import com.br.fatec.edu.todoapp.image.service.ImageStorageService

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class TodoAppApplication implements CommandLineRunner {
    
    @Resource
    ImageStorageService imageStorageService
    
	static void main(String[] args) {
		SpringApplication.run TodoAppApplication, args
	}

    @Override
    void run(String... args) throws Exception {
        this.imageStorageService.init()
    }
}
