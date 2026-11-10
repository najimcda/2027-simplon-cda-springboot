package fr.simplon.domedespuys.services;

import fr.simplon.domedespuys.dto.UserPostDTO;
import fr.simplon.domedespuys.entities.User;
import fr.simplon.domedespuys.repositories.UserRepository;
import fr.simplon.domedespuys.services.interfaces.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@AllArgsConstructor
public class UserServiceImpl implements IUserService {

    private UserRepository userRepository;


    @Override
    public User create(UserDTO dto) {
        User user = new User();
        dtoToObject(dto, user);
        return user;
    }

    @Override
    public User getById(String id) {
        return userRepository
                .findById(Long.parseLong(id))
                .orElse(null);
    }

    @Override
    public User update(String id, UserPostDTO dto) {
        User user = getById(id);
        if (user != null) {
            dtoToObject(dto, user);
        }
        return user;
    }

    @Override
    public boolean delete(String id) {
        try {
            userRepository.deleteById(Long.parseLong(id));
            return true;
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }



    private void dtoToObject(UserPostDTO dto, User user) {
        user.setEmail(dto.email);
        user.setPassword(dto.password);
        user.setFirstname(dto.firstName);
        user.setLastname(dto.lastName);
        user.setCreatedAt(dto.createdAt);

        userRepository.saveAndFlush(user);
    }

}