package fr.simplon.domedespuys.services;

import fr.simplon.domedespuys.dto.AdressDTO;
import fr.simplon.domedespuys.entities.Adress;
import fr.simplon.domedespuys.repositories.AdressRepository;
import fr.simplon.domedespuys.repositories.UserRepository;
import fr.simplon.domedespuys.services.interfaces.IAdressService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@AllArgsConstructor
public class AdressServiceImpl implements IAdressService {

    private final UserRepository userRepository;
    private AdressRepository adressRepository;




    private Adress getById(String id) {
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
        Adress adress = getById(id);
        if (adress == null) {
            return false;
        }
        adressRepository.delete(adress);
        return true;
    }

    @Override
    public Adress create(AdressDTO dto) {
        Adress adress = new Adress();
        adress.setCreatedAt(LocalDateTime.now());
        adress.setUser(userRepository.findById(1L).orElseThrow());
        dtoToObject(dto, adress);
        return adress;
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
        adressRepository.saveAndFlush(adress);
    }


}