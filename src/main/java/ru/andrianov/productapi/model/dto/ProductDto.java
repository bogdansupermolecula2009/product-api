package ru.andrianov.productapi.model.dto;

import lombok.Getter;
import lombok.Setter;
import ru.andrianov.productapi.model.ProductImageEntity;

import java.time.LocalDateTime;

@Getter
@Setter
public class ProductDto {

    private Long id;

    private String title;

    private String category;

    private String description;

    private Double price;

    private LocalDateTime createdAt;

    private String thumbnail;
}
