package fr.simplon.domedespuys.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import fr.simplon.domedespuys.dto.BrandDTO;
import fr.simplon.domedespuys.entities.Brand;
import fr.simplon.domedespuys.services.interfaces.IBrandService;
import fr.simplon.domedespuys.Views.BrandView;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/brand")
public class BrandRestController {

    private final IBrandService brandService;

    @GetMapping
    @JsonView(BrandView.BrandListView.class)
    public List<Brand> getBrands() {
        return brandService.getCollection();
    }

    @GetMapping("/{id}")
    @JsonView(BrandView.BrandDetailView.class)
    public Brand getBrandById(@PathVariable String id) {
        return brandService.getById(id);
    }

    @PostMapping
    @JsonView(BrandView.BrandDetailView.class)
    public Brand createBrand(@RequestBody BrandDTO dto) {
        return brandService.create(dto);
    }

    @PutMapping("/{id}")
    @JsonView(BrandView.BrandDetailView.class)
    public Brand updateBrand(@PathVariable String id, @RequestBody BrandDTO dto) {
        return brandService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @JsonView(BrandView.BrandDetailView.class)
    public boolean delete(@PathVariable String id) {
        return brandService.delete(id);
    }

}