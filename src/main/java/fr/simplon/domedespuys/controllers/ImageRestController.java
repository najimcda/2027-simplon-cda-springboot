package fr.simplon.domedespuys.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import fr.simplon.domedespuys.Views.ImageView;
import fr.simplon.domedespuys.dto.ImageDTO;
import fr.simplon.domedespuys.entities.Image;
import fr.simplon.domedespuys.services.interfaces.IImageService;
import fr.simplon.domedespuys.services.interfaces.IProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/image")
public class ImageRestController {

    private final IImageService imageService;
    private final IProductService productService;

    @PostMapping("/product/{productId}")
    @JsonView(ImageView.ImageDetailView.class)
    public Image createImage(@PathVariable String productId, @RequestBody ImageDTO dto) {
        return imageService.create(dto, productService.getById(productId));
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable String id) {
        return imageService.delete(id);
    }

}