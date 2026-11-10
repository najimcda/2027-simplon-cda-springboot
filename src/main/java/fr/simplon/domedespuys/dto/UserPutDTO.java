package fr.simplon.domedespuys.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPutDTO {

    private String firstName;
    private String lastName;
    private String phone;
}