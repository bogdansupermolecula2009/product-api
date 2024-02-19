package ru.andrianov.productapi.model.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ImageDto {

    private Long productId;

    private String name;

    private String contentType;

    private String url;

}
