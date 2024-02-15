package ru.andrianov.productapi.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import ru.andrianov.productapi.model.ProductImageEntity;
import ru.andrianov.productapi.model.dto.CreatedProductDto;
import ru.andrianov.productapi.model.dto.ProductDto;
import ru.andrianov.productapi.model.dto.ProductImageDto;
import ru.andrianov.productapi.model.dto.UpdatedProductDto;
import ru.andrianov.productapi.service.ProductService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Transactional
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getProducts() {
        return ResponseEntity.ok(service.getProducts());
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long id) {
        return ResponseEntity.ok(service.getProduct(id));
    }

    @PostMapping("/products")
    public ResponseEntity<ProductDto> createProduct(@Valid
                                                    @RequestBody CreatedProductDto createdProductDto) {
        return ResponseEntity.ok(service.createProduct(createdProductDto));
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<ProductDto> updateProduct(@Valid
                                                    @RequestBody UpdatedProductDto updatedProductDto,
                                                    @PathVariable Long id) {
        return ResponseEntity.ok(service.updateProduct(updatedProductDto, id));
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        service.deleteProduct(id);
        return ResponseEntity.ok().build();

    }

    @PostMapping("/products/media/image")
    public ResponseEntity<ProductImageEntity> uploadImage(@Valid
                                                          @RequestBody ProductImageDto imageDto) {
        return ResponseEntity.ok(service.uploadImage(imageDto));
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    private Map<String, String> handleConstraintValidationException(ConstraintViolationException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getConstraintViolations().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getMessage();
            errors.put(fieldName, errorMessage);
        });

        return errors;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    private Map<String, String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return errors;
    }
}
