package ru.andrianov.productapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.andrianov.productapi.model.ProductEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<ProductEntity, Long> {

    List<ProductEntity> findAllByOrderByCreatedAtAsc();

    Optional<ProductEntity> findByProductId(Long id);

    Long deleteByProductId(Long id);
}
