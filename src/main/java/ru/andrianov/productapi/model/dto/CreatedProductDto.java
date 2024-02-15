package ru.andrianov.productapi.model.dto;

import jakarta.persistence.Lob;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatedProductDto {

    @Valid

    @NotBlank
    @Size(min = 2, max = 100, message = "title must be between 2 and 100 characters")
    private String title;

    @NotBlank
    @Size(min = 2, max = 50, message = "category must be between 2 and 50 characters")
    private String category;

    @NotBlank
    @Size(min = 2, max = 1000, message = "description must be between 2 and 1000 characters")
    private String description;

    @NotNull
    @Min(value = 0, message = "price must be greater than 0")
    private Double price;


}
