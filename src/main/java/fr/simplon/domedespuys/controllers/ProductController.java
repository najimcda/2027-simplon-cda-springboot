package fr.simplon.domedespuys.controllers;

import fr.simplon.domedespuys.entities.Product;
import fr.simplon.domedespuys.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

    private final ProductRepository productRepository;

    @GetMapping
    public List<Product> getBrands() {
        return productRepository.findAll();
    }

    @GetMapping("/slug/{slug}")
    public Optional<Product> getProductBySlug(@PathVariable String slug) {
        return productRepository.findBySlug(slug);
    }

    @GetMapping("/{id}")
    public Optional<Product> getProductById(@PathVariable String id) {
        return productRepository.findById(Long.valueOf(id));
    }



}
