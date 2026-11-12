package fr.simplon.domedespuys.entities;

import com.fasterxml.jackson.annotation.JsonView;
import fr.simplon.domedespuys.Views.AdressView;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Adress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(AdressView.Id.class)
    private Long id;

    @Column(nullable = false)
    @JsonView(AdressView.Line.class)
    private String line;

    @Column
    @JsonView(AdressView.ExtraLine.class)
    private String extraLine;

    @Column(nullable = false)
    @JsonView(AdressView.City.class)
    private String city;

    @Column(nullable = false)
    @JsonView(AdressView.ZipCode.class)
    private String zipCode;

    @Column(nullable = false)
    @JsonView(AdressView.Country.class)
    private String country;

    @Column(nullable = true)
    @JsonView(AdressView.Phone.class)
    private String phone;

    @Column(nullable = false)
    @JsonView(AdressView.Firstname.class)
    private String firstname;

    @Column(nullable = false)
    @JsonView(AdressView.Lastname.class)
    private String lastname;

    @Column(nullable = false)
    @JsonView(AdressView.CreatedAt.class)
    private LocalDateTime createdAt;

    // Pas de @JsonView ici : l'utilisateur (et son mot de passe) ne doit pas sortir dans la réponse
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private User user;
}