package ru.andrianov.productapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import ru.andrianov.productapi.factory.ImageDtoFactory;
import ru.andrianov.productapi.model.ImageEntity;
import ru.andrianov.productapi.model.dto.ImageDto;
import ru.andrianov.productapi.repository.ImageRepo;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static org.apache.commons.io.FileUtils.*;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepo imageRepository;


    public ImageDto uploadImage(MultipartFile image) throws IOException {

        ImageEntity imageEntity = new ImageEntity();
        imageEntity.setName(StringUtils.cleanPath(image.getOriginalFilename()));
        imageEntity.setContentType(image.getContentType());
        imageEntity.setData(image.getBytes());
        imageRepository.save(imageEntity);

        return ImageDtoFactory.transformImageEntityToDto(imageEntity);
    }

    public ImageEntity getImage(Long id) throws IOException {
        return imageRepository.findByImageId(id);

    }

    public List<ImageDto> getAllImages(){
        return imageRepository.findAll().stream()
                .map(ImageDtoFactory::transformImageEntityToDto)
                .collect(Collectors.toList());
    }

    public Long deleteImage(Long id){
        return imageRepository.deleteByImageId(id);
    }
}
