package fr.simplon.domedespuys.services;

import fr.simplon.domedespuys.dto.AdressDTO;
import fr.simplon.domedespuys.entities.Adress;
import fr.simplon.domedespuys.entities.Brand;
import fr.simplon.domedespuys.repositories.AdressRepository;
import fr.simplon.domedespuys.services.interfaces.IAdressService;
import fr.simplon.domedespuys.utils.Slugger;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class AdressServiceImpl implements IAdressService {

    private AdressRepository adressRepository;


    @Override
    public Adress create(AdressDTO dto) {
        Adress adress = new Adress();
        dtoToObject(dto, adress);
        return adress;
    }

    @Override
    public Adress getById(String id) {
        return adressRepository
                .findById(Long.parseLong(id))
                .orElse(null);
    }

    @Override
    public Adress update(String id, AdressDTO dto) {
        Adress adress = getById(id);
        if (adress != null) {
            dtoToObject(dto, adress);
        }
        return adress;
    }

    @Override
    public boolean delete(String id) {
        try {
            adressRepository.deleteById(Long.parseLong(id));
            return true;
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private void dtoToObject(AdressDTO dto, Adress adress) {
        adress.setLine(dto.line);
        adress.setExtraLine(dto.extraline);
        adress.setCity(dto.city);
        adress.setZipCode(dto.zipcode);
        adress.setCountry(dto.country);
        adress.setPhone(dto.phone);
        adress.setFirstname(dto.firstname);
        adress.setLastname(dto.lastname);
        adress.setCreatedAt(dto.createdAt);
        adressRepository.saveAndFlush(adress);
    }

}