package fr.simplon.domedespuys.services;

import fr.simplon.domedespuys.dto.ImageDTO;
import fr.simplon.domedespuys.entities.Image;
import fr.simplon.domedespuys.entities.Product;
import fr.simplon.domedespuys.repositories.ImageRepository;
import fr.simplon.domedespuys.services.interfaces.IImageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ImageServiceImpl implements IImageService {

    private ImageRepository imageRepository;

    @Override
    public Image create(ImageDTO dto, Product product) {
        Image image = new Image();
        image.setProduct(product);
        image.setIsDefault(dto.isDefault);
        image.setPath(dto.path);
        image.setExtension("png");
        imageRepository.save(image);
        return image;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }
}