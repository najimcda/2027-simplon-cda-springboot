package fr.simplon.domedespuys.services.interfaces;

import fr.simplon.domedespuys.dto.ProductDTO;
import fr.simplon.domedespuys.entities.Product;

public interface IPurchaseService extends
        ICreate<Purchase, PurchaseDTO>,
        IUpdate<Purchase, PurchaseDTO>,
        ICollection<Purchase>,
        IGetItem<Purchase>{
}