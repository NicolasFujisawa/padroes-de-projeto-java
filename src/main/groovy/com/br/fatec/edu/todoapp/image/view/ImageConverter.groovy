package com.br.fatec.edu.todoapp.image.view


import com.br.fatec.edu.todoapp.image.model.Image

import liquibase.util.file.FilenameUtils
import org.springframework.web.multipart.MultipartFile

class ImageConverter {
    static final String UPLOAD_PATH = "http://localhost:8080/uploads/files"

    static Image renderFromRequest(MultipartFile multipartFile) {
        Image image = new Image()
        String extension = FilenameUtils.getExtension(multipartFile.originalFilename)
        String name = UUID.randomUUID().toString()
        String path = "${name}.${extension}"
        image.setPath(path)
        return image
    }

    static List<Image> renderManyFromRequest(MultipartFile[] requestImages) {
        List<Image> images = new ArrayList<>()
        for(MultipartFile requestImage : requestImages) {
            images.add(renderFromRequest(requestImage))
        }
        return images
    }

    static ResponseImage renderFromImage(Image image) {
        ResponseImage imageJson = new ResponseImage()
        imageJson.setId(image.id)
        imageJson.setUrl("${UPLOAD_PATH}/${image.path}")
        return imageJson
    }

    static List<ResponseImage> renderManyFromImage(List<Image> images) {
        List<ResponseImage> imagesJson = new ArrayList<>()
        for(Image image : images) {
            imagesJson.add(renderFromImage(image))
        }
        return imagesJson
    }
}
