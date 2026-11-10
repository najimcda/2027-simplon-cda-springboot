package fr.simplon.domedespuys.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPostDTO {

    public String email;
    public String password;
    public String firstName;
    public String lastName;
}