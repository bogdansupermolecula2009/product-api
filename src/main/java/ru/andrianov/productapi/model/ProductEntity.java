package ru.andrianov.productapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.util.StreamUtils;

import java.time.LocalDateTime;

@Entity
@Table(name = "product")
@Data
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;

    @NotBlank
    @Size(min = 2, max = 100, message = "Размер поля от 2 до 100 символов")
    private String title;

    @NotBlank
    @Size(min = 2, max = 50)
    private String category;

    @NotBlank
    @Size(min = 2, max = 1000)
    private String description;

    @NotNull
    @Min(value = 0)
    private Double price;

    @NotNull
    private LocalDateTime createdAt;


}
