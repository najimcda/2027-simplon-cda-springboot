package fr.simplon.domedespuys.repositories;

import fr.simplon.domedespuys.entities.Adress;
import fr.simplon.domedespuys.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdressRepository extends JpaRepository<Adress, Long> {
    List<Adress> findAllByUser(User user);
}
