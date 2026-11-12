package fr.simplon.domedespuys.services.interfaces;

import fr.simplon.domedespuys.dto.PurchasePostDTO;
import fr.simplon.domedespuys.entities.Purchase;

public interface IPurchaseService extends
        ICreate<Purchase, PurchasePostDTO>,
        IUpdate<Purchase, PurchasePostDTO>,
        ICollection<Purchase>,
        IGetItem<Purchase>{
}