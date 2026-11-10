package fr.simplon.domedespuys.services.interfaces;

import fr.simplon.domedespuys.dto.PurchaseProductDTO;
import fr.simplon.domedespuys.entities.Purchase;
import fr.simplon.domedespuys.entities.PurchaseProduct;


public interface ICreatePurchaseProduct {

    PurchaseProduct create(PurchaseProductDTO dto, Purchase purchase);
}