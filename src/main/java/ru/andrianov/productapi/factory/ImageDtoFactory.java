package ru.andrianov.productapi.factory;

import lombok.experimental.UtilityClass;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.andrianov.productapi.model.ImageEntity;
import ru.andrianov.productapi.model.dto.ImageDto;

@UtilityClass
public class ImageDtoFactory {

    public static ImageDto transformImageEntityToDto(ImageEntity imageEntity) {
        ImageDto imageDto = new ImageDto();
        imageDto.setProductId(imageEntity.getProduct() == null ? null : imageEntity.getProduct().getProductId());
        imageDto.setName(imageEntity.getName());
        imageDto.setContentType(imageEntity.getContentType());
        imageDto.setUrl(ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/products/media/image/")
                .path(imageEntity.getImageId().toString())
                .toUriString());
        return imageDto;

    }
}
