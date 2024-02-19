package ru.andrianov.productapi.factory;

import lombok.experimental.UtilityClass;
import ru.andrianov.productapi.model.ProductEntity;
import ru.andrianov.productapi.model.dto.ProductDto;


@UtilityClass
public class ProductDtoFactory {
    public static ProductDto transformProductEntityToDto(ProductEntity product) {
        ProductDto dto = new ProductDto();
        dto.setId(product.getProductId());
        dto.setTitle(product.getTitle());
        dto.setCategory(product.getCategory());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setCreatedAt(product.getCreatedAt());
        dto.setThumbnailId(product.getThumbnail() == null ? null : product.getThumbnail().getImageId());
        return dto;
    }
}
