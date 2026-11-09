package fr.simplon.domedespuys.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import fr.simplon.domedespuys.entities.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
