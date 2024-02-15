package ru.andrianov.productapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Table(name = "image")
@Data
public class ProductImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long imageId;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @MapsId
    private ProductEntity product;

    @Lob
    @NotBlank
    private String data;

    @NotBlank
    private String contentType;
}
