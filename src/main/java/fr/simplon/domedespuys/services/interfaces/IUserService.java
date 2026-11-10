package fr.simplon.domedespuys.services.interfaces;

import fr.simplon.domedespuys.dto.UserDTO;
import fr.simplon.domedespuys.entities.User;

public interface IUserService extends
        ICreate<User, UserDTO>,
        IUpdate<User, UserDTO>,
        IGetItem<User>,
        IDelete
{
}