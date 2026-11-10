package fr.simplon.domedespuys.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPostDTO {

    private String email;
    private String password;
    private String firstName;
    private String lastName;
}