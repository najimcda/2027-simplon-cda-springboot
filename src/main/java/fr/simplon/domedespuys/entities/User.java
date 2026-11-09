package fr.simplon.domedespuys.entities;

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
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;


@Column(nullable = false)
private String password;


@Column(nullable = false)
private String firstname;


@Column(nullable = false)
private String lastname;


@Column(length = 80)
private String phone;


@Column(nullable = false)
private LocalDateTime createdAt;


@OneToMany(mappedBy = "user")
List<Purchase> purchases;

@OneToMany(mappedBy = "user")
List<Adress> adresses;
}
