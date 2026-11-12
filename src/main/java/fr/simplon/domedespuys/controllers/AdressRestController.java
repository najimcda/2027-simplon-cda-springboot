package fr.simplon.domedespuys.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import fr.simplon.domedespuys.Views.AdressView;
import fr.simplon.domedespuys.dto.AdressDTO;
import fr.simplon.domedespuys.entities.Adress;
import fr.simplon.domedespuys.services.interfaces.IAdressService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/adress")
public class AdressRestController {

    private final IAdressService adressService;

    @PostMapping
    @JsonView(AdressView.AdressDetailView.class)
    public Adress createAdress(@RequestBody AdressDTO dto) {
        return adressService.create(dto);
    }

    @PutMapping("/{id}")
    @JsonView(AdressView.AdressDetailView.class)
    public Adress updateAdress(@PathVariable String id, @RequestBody AdressDTO dto) {
        return adressService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable String id) {
        return adressService.delete(id);
    }

}