package fr.simplon.domedespuys.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchasePostDTO {

    public AdressDTO address;
    public List<PurchaseProductDTO> purchaseProducts;
}