package fr.simplon.domedespuys.services.interfaces;

import fr.simplon.domedespuys.dto.AdressDTO;
import fr.simplon.domedespuys.entities.Adress;

public interface IAdressService extends
        ICreate<Adress, AdressDTO>,
        IUpdate<Adress, AdressDTO>,
        IDelete
{
}