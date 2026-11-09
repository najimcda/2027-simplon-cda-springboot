package fr.simplon.domedespuys.entities;

import com.fasterxml.jackson.annotation.JsonView;
import fr.simplon.domedespuys.Views.PurchaseProductView;
import fr.simplon.domedespuys.Views.PurchaseView;
import fr.simplon.domedespuys.Views.UserView;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class PurchaseProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(PurchaseProductView.Id.class)
    private Long id;

    @Column(nullable = false)
    @JsonView(PurchaseProductView.Quantity.class)
    private Integer quantity;

    @Column(nullable = false)
    @JsonView(PurchaseProductView.Price.class)
    private Integer price;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    @JsonView(PurchaseProductView.Purchase.class)
    private Purchase purchase;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    @JsonView(PurchaseProductView.Product.class)
    private Product product;
}

