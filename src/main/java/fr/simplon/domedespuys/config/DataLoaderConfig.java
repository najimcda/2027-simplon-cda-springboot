package fr.simplon.domedespuys.config;

import com.github.javafaker.Faker;
import fr.simplon.domedespuys.entities.*;
import fr.simplon.domedespuys.enums.EnumPurchaseStatus;
import fr.simplon.domedespuys.repositories.*;
import fr.simplon.domedespuys.utils.Slugger;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;

@Component
@AllArgsConstructor
public class DataLoaderConfig implements CommandLineRunner {

    private final AdressRepository adressRepository;
    private final BrandRepository brandRepository;
    private final Slugger slugger;
    private final ProductRepository productRepository;
    private final ImageRepository imageRepository;
    private final PurchaseRepository purchaseRepository;
    private final PurchaseProductRepository purchaseProductRepository;
    private UserRepository userRepository;

    @Override
    public void run(String @NonNull ... args) {
        createUsers();
        createAdress();
        createBrand();
        createProduct();
        createImage();
        createPurchase();
        createPurchaseProduct();
    }

    private void createUsers() {
        if (0 == userRepository.count()) {
            Faker faker = new Faker(Locale.of("fr"));

            for (int i = 0; i < 100; i++) {
                User user = new User();
                user.setFirstname(faker.name().firstName());
                user.setLastname(faker.name().lastName());
                String email = faker.internet().emailAddress((user.getFirstname() + "." + user.getLastname()).toLowerCase());
                user.setEmail(email);
                user.setPassword(faker.internet().password(12, 20));
                if (hasProbability(2, 3)) {
                    user.setPhone(faker.phoneNumber().phoneNumber());
                }
                LocalDateTime localDate = new java.sql.Date(faker.date().birthday(0, 5).getTime()).toLocalDate().atTime(0, 0);
                user.setCreatedAt(localDate);
                userRepository.save(user);
            }

            userRepository.flush();
        }
    }

    private boolean hasProbability(int expected, int max) {
        Random random = new Random();
        // random.nextInt(max) = génère un entier aléatoire entre 0 et MAX
        // expected = la proba attendue
        // Si le random est inférieure ou égale à la prob, alors on renvoie true
        return random.nextInt(max) < expected;
    }

    private void createBrand() {
        if (0 == brandRepository.count()) {
            Faker faker = new Faker(Locale.of("fr"));

            for (int i = 0; i < 50; i++) {
                Brand brand = new Brand();
                brand.setLabel(faker.company().name());
                brand.setLogo(faker.company().logo());
                brand.setSlug(slugger.slugify(brand.getLabel()));
                brandRepository.save(brand);
            }

            brandRepository.flush();

        }
    }

    private void createAdress() {
        if (0 == adressRepository.count()) {
            Faker faker = new Faker(Locale.of("fr"));

            for (int i = 0; i < 100; i++) {
                Adress adress = new Adress();
                Random random = new Random();
                adress.setCity(faker.address().city());
                adress.setLine(faker.address().streetAddress());
                if (hasProbability(1, 4)) {
                    adress.setExtraLine(faker.address().buildingNumber());
                }
                adress.setZipCode(faker.address().zipCode());
                adress.setCountry("France");
                adress.setFirstname(faker.name().firstName());
                adress.setLastname(faker.name().lastName());
                LocalDateTime localDate = new java.sql.Date(faker.date().birthday(0, 4).getTime()).toLocalDate().atTime(0, 0);
                adress.setCreatedAt(localDate);
                adress.setUser(userRepository.findById(random.nextLong(1, 100)).orElseThrow());
                adressRepository.save(adress);
            }
            adressRepository.flush();

        }
    }

    private void createProduct() {
        if (0 == productRepository.count()) {
            Faker faker = new Faker(Locale.of("fr"));

            for (int i = 0; i < 100; i++) {
                Product product = new Product();
                Random random = new Random();
                product.setLabel(i + "-" + faker.commerce().productName());
                product.setDescription(faker.lorem().paragraph(2));
                product.setPrice(faker.number().numberBetween(499, 3999));


                product.setSlug(slugger.slugify(product.getLabel()));
                LocalDateTime localDate = new java.sql.Date(faker.date().birthday(0, 5).getTime()).toLocalDate().atTime(0, 0);
                product.setCreatedAt(localDate);
                product.setBrand(brandRepository.findById(random.nextLong(1, 50)).orElseThrow());
                productRepository.save(product);
            }
            productRepository.flush();
        }

    }

    private void createImage() {
        if (imageRepository.count() == 0) {
            Faker faker = new Faker(Locale.of("fr"));
            Random random = new Random();
            List<String> extensions = List.of("jpg", "png", "webp");
            List<Product> products = productRepository.findAll();

            for (int i = 0; i < 100; i++) {
                Product product = products.get(random.nextInt(products.size()));
                System.out.println("Image " + i + " produit " + product.getId());
                Image image = new Image();
                image.setPath(faker.internet().url());
                image.setExtension(extensions.get(random.nextInt(extensions.size())));
                image.setProduct(product);
                image.setIsDefault(!imageRepository.existsByProductAndIsDefaultTrue(product));
                imageRepository.save(image);
            }
            imageRepository.flush();
        }
    }

    private void createPurchase() {
        if (purchaseRepository.count() == 0) {
            Faker faker = new Faker(Locale.of("fr"));
            Random random = new Random();
            List<User> users = userRepository.findAll();

            for (int i = 0; i < 200; i++) {
                Purchase purchase = new Purchase();
                // Numéro unique : PUR-2026-00001, PUR-2026-00002, ...
                purchase.setNumber(String.format("PUR-%d-%05d", LocalDateTime.now().getYear(), i + 1));
                EnumPurchaseStatus[] statuses = EnumPurchaseStatus.values();
                purchase.setStatus(statuses[random.nextInt(statuses.length)]);
                LocalDateTime localDate = new java.sql.Date(faker.date().birthday(0, 2).getTime()).toLocalDate().atTime(0, 0);
                purchase.setCreatedAt(localDate);
                purchase.setUser(users.get(random.nextInt(users.size())));
                purchaseRepository.save(purchase);
            }
            purchaseRepository.flush();
        }
    }
    private void createPurchaseProduct() {
        if (purchaseProductRepository.count() == 0) {
            Random random = new Random();
            List<Purchase> purchases = purchaseRepository.findAll();
            List<Product> products = productRepository.findAll();

            for (Purchase purchase : purchases) {
                // 1 à 5 produits différents par commande
                List<Product> shuffled = new ArrayList<>(products);
                Collections.shuffle(shuffled, random);
                int nbLines = random.nextInt(1, 6);

                for (Product product : shuffled.subList(0, nbLines)) {
                    PurchaseProduct purchaseProduct = new PurchaseProduct();
                    purchaseProduct.setPurchase(purchase);
                    purchaseProduct.setProduct(product);
                    purchaseProduct.setQuantity(random.nextInt(1, 4));
                    purchaseProduct.setPrice(product.getPrice()); // prix figé au moment de la commande
                    purchaseProductRepository.save(purchaseProduct);
                }
            }
            purchaseProductRepository.flush();
        }
    }
}
