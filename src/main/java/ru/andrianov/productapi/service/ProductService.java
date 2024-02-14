package ru.andrianov.productapi.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.andrianov.productapi.exception.ProductNotFoundException;
import ru.andrianov.productapi.factory.ProductDtoFactory;
import ru.andrianov.productapi.model.ProductEntity;
import ru.andrianov.productapi.model.dto.CreatedProductDto;
import ru.andrianov.productapi.model.dto.ProductDto;
import ru.andrianov.productapi.model.dto.UpdatedProductDto;
import ru.andrianov.productapi.repository.ProductRepo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepo productRepo;

    public List<ProductDto> getProducts() {
        return productRepo.findAllByOrderByCreatedAtAsc().stream()
                .map(ProductDtoFactory::transformProductEntityToDto)
                .collect(Collectors.toList());
    }

    public ProductDto getProduct(Long id) {
        return ProductDtoFactory.transformProductEntityToDto(productRepo
                .findByProductId(id).orElseThrow(() -> new ProductNotFoundException("Product not found")));
    }

    public ProductDto createProduct(CreatedProductDto createdProductDto) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setCreatedAt(LocalDateTime.now());
        productEntity.setTitle(createdProductDto.getTitle());
        productEntity.setCategory(createdProductDto.getCategory());
        productEntity.setDescription(createdProductDto.getDescription());
        productEntity.setPrice(createdProductDto.getPrice());
        productRepo.save(productEntity);
        return ProductDtoFactory.transformProductEntityToDto(productEntity);
    }

    public ProductDto updateProduct(UpdatedProductDto updatedProductDto, Long id) {
        ProductEntity productEntity = productRepo
                .findByProductId(id).orElseThrow(() -> new ProductNotFoundException("Product not found"));
        productEntity.setTitle(updatedProductDto.getTitle());
        productEntity.setCategory(updatedProductDto.getCategory());
        productEntity.setDescription(updatedProductDto.getDescription());
        productEntity.setPrice(updatedProductDto.getPrice());
        productRepo.save(productEntity);
        return ProductDtoFactory.transformProductEntityToDto(productEntity);
    }

    public Long deleteProduct(Long id) {
        ProductEntity productEntity = productRepo
                .findByProductId(id).orElseThrow(() -> new ProductNotFoundException("Product not found"));
        return productRepo.deleteByProductId(productEntity.getProductId());
    }
}
