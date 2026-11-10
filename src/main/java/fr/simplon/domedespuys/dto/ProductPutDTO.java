package fr.simplon.domedespuys.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductPutDTO {

    public String label;
    public String description;
    public int price;
    public Long idBrand;
    public List<String> images;
}