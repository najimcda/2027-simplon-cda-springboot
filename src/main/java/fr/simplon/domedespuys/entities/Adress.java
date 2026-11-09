package fr.simplon.domedespuys.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Adress {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@Column(nullable = false)
private String line;


@Column()
private String extraLine;


@Column(nullable = false)
private String city;


@Column(nullable = false)
private String zipCode;


@Column(nullable = false)
private String country;


@Column(nullable = true)
private String phone;


@Column(nullable = false)
private String firstname;


@Column(nullable = false)
private String lastname;


@Column(nullable = false)
private LocalDateTime createdAt;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private User user;
}