package ru.andrianov.productapi.model.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductImageDto {

    private Long productId;

    private String data;

    private String contentType;
}
