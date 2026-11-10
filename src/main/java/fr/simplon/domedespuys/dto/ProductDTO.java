package fr.simplon.domedespuys.dto;

import fr.simplon.domedespuys.entities.Brand;
import fr.simplon.domedespuys.entities.Image;

import java.util.List;

public class ProductDTO {
    public String label;
    public String description;
    public Integer price;
    public Brand brand;
    public List<Image> image;
}