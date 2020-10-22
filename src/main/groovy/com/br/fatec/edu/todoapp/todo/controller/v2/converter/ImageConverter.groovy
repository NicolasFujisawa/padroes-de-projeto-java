package com.br.fatec.edu.todoapp.todo.controller.v2.converter

import com.br.fatec.edu.todoapp.image.model.Image
import com.br.fatec.edu.todoapp.image.view.ImageJson

class ImageConverter {
    static Image convertFromJson(ImageJson imageJson) {
        Image image = new Image()
        image.setId(imageJson.id)
        image.setPath(imageJson.url)
        return image
    }
    
    static List<Image> convertAllJson(List<ImageJson> imagesJson) {
        List<Image> images = new ArrayList<>()
        for(ImageJson imageJson : imagesJson) {
            images.add(convertFromJson(imageJson))
        }
        return images
    }
    
    static ImageJson convertFromImage(Image image) {
        ImageJson imageJson = new ImageJson()
        imageJson.setId(image.id)
        imageJson.setUrl(image.path)
        return imageJson
    }
    
    static List<ImageJson> convertAllFromImage(List<Image> images) {
        List<ImageJson> imagesJson = new ArrayList<>()
        for(Image image : images) {
            imagesJson.add(convertFromImage(image))
        }
        return imagesJson
    }
}
