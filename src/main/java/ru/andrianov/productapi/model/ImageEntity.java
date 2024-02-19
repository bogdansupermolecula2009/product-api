package ru.andrianov.productapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;

@Entity
@Table(name = "image")
@Data
public class ImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long imageId;

    @NotBlank
    private String name;

    @NotBlank
    private String contentType;

    @Lob
    @JdbcTypeCode(Types.BINARY)
    private byte[] data;

    @OneToOne(mappedBy = "thumbnail",cascade = CascadeType.PERSIST)
    private ProductEntity product;
}
