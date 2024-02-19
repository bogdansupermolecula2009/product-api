package ru.andrianov.productapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.andrianov.productapi.model.ImageEntity;

@Repository
public interface ImageRepo extends JpaRepository<ImageEntity, Long> {

    ImageEntity findByImageId(Long id);

    Long deleteByImageId(Long id);

}
