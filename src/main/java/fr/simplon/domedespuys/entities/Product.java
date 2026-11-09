package fr.simplon.domedespuys.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String label;

    @Column(nullable = true)
    private String description;

    @Column(nullable = false)
    private Integer price;

    @Column(unique = true, nullable = false)
    private String slug;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private Brand brand;

    @OneToMany(mappedBy = "product")
    List<Image> images;


}
