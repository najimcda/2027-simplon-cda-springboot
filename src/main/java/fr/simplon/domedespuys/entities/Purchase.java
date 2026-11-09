package fr.simplon.domedespuys.entities;

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
    private Long id;

    @Column(unique = true, nullable = false)
    private String number;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private EnumPurchaseStatus status = EnumPurchaseStatus.PENDING;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "purchase")
    private List<PurchaseProduct> purchaseProducts;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private User user;

}