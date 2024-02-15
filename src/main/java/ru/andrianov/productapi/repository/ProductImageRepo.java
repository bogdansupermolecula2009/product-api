package ru.andrianov.productapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.andrianov.productapi.model.ProductImageEntity;

public interface ProductImageRepo extends JpaRepository<ProductImageEntity, Long> {
}
