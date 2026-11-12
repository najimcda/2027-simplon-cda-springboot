package fr.simplon.domedespuys.entities;

import com.fasterxml.jackson.annotation.JsonView;
import fr.simplon.domedespuys.Views.UserView;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(UserView.Id.class)
    private Long id;

    @Column(nullable = false)
    @JsonView(UserView.Email.class)
    private String email;

    // Pas de @JsonView : le mot de passe ne sort jamais
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @JsonView(UserView.Firstname.class)
    private String firstname;

    @Column(nullable = false)
    @JsonView(UserView.Lastname.class)
    private String lastname;

    @Column(length = 80)
    private String phone;

    @Column(nullable = false)
    @JsonView(UserView.CreatedAt.class)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    List<Purchase> purchases;

    @OneToMany(mappedBy = "user")
    @JsonView(UserView.Adresses.class)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    List<Adress> adresses;
}