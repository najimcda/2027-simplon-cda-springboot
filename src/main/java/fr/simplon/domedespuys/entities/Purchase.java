package fr.simplon.domedespuys.entities;

import com.fasterxml.jackson.annotation.JsonView;
import fr.simplon.domedespuys.Views.PurchaseView;
import fr.simplon.domedespuys.enums.EnumPurchaseStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "purchase")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(PurchaseView.Id.class)
    private Long id;

    @Column(unique = true, nullable = false)
    @JsonView(PurchaseView.Number.class)
    private String number;

    @Column(nullable = false)
    @JsonView(PurchaseView.Status.class)
    private String status = EnumPurchaseStatus.PENDING.getValue();

    @Column(nullable = false)
    @JsonView(PurchaseView.CreatedAt.class)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "purchase")
    @JsonView(PurchaseView.PurchaseProduct.class)
    private List<PurchaseProduct> purchaseProducts;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    @JsonView(PurchaseView.User.class)
    private User user;

    public void setAdress(Adress first) {
        
    }
}