package fr.simplon.domedespuys.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductPostDTO {

    private String label;
    private String description;
    private int price;
    private Long idBrand;
    private List<String> images;
}