package ru.andrianov.productapi.controller;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.andrianov.productapi.model.ImageEntity;
import ru.andrianov.productapi.model.dto.ImageDto;
import ru.andrianov.productapi.service.ImageService;

import java.io.IOException;
import java.util.List;

@RestController
@Transactional
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;


    @PostMapping("/products/media/image")
    public ResponseEntity<ImageDto> uploadImage(@RequestParam("image") MultipartFile image) {
        try {
            return ResponseEntity.ok(imageService.uploadImage(image));
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }

    @GetMapping("/products/media/image/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) throws IOException {
        ImageEntity imageEntity = imageService.getImage(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + imageEntity.getName() + "\"")
                .contentType(MediaType.valueOf(imageEntity.getContentType()))
                .body(imageEntity.getData());
    }

    @GetMapping("/products/media/image")
    public ResponseEntity<List<ImageDto>> getImages() throws IOException {
        return ResponseEntity.ok(imageService.getAllImages());
    }

    @DeleteMapping("/products/media/image/{id}")
    public ResponseEntity<?> deleteImage(@PathVariable Long id) {
        imageService.deleteImage(id);
        return ResponseEntity.ok().build();
    }
}
