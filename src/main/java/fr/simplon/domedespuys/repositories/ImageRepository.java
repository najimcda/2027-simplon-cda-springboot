package fr.simplon.domedespuys.repositories;

import fr.simplon.domedespuys.entities.Image;
import fr.simplon.domedespuys.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    List<Image> findAllByProduct(Product product);

    boolean existsByProductAndIsDefaultTrue(Product product);
}