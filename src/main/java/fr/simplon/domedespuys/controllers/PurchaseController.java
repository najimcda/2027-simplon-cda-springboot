package fr.simplon.domedespuys.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import fr.simplon.domedespuys.Views.PurchaseView;
import fr.simplon.domedespuys.entities.Purchase;
import fr.simplon.domedespuys.repositories.PurchaseRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/purchase")
public class PurchaseController {

    private final PurchaseRepository purchaseRepository;

    @GetMapping
    @JsonView(PurchaseView.PurchaseListView.class)
    public List<Purchase> getPurchases() {
        return purchaseRepository.findAll();
    }

    @GetMapping("/{id}")
    @JsonView(PurchaseView.PurchaseDetailView.class)
    public Optional<Purchase> getPurchaseById(@PathVariable String id) {
        return purchaseRepository.findById(Math.toIntExact(Long.parseLong(id)));
    }



}