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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Component
@AllArgsConstructor
public class DataLoaderConfig implements CommandLineRunner {

    private UserRepository userRepository;
    private AdressRepository adressRepository;
    private BrandRepository brandRepository;
    private ProductRepository productRepository;
    private ImageRepository imageRepository;
    private PurchaseRepository purchaseRepository;
    private PurchaseProductRepository purchaseProductRepository;
    private Slugger slugger;

    private final int NB_USERS = 200;

    @Override
    public void run(String @NonNull ... args) {
        createUsers();
        createAdress();
        createBrand();
        createProduct();
        createImages();
        createPurchase();
        createPurchaseProduct();
    }

    private void createPurchaseProduct() {
        if (0 == purchaseProductRepository.count()) {
            List<Purchase> purchases = purchaseRepository.findAll();
            for (Purchase purchase : purchases) {
                createPurchaseProductByPurchase(purchase);

                do {
                    createPurchaseProductByPurchase(purchase);
                } while(hasProbability(1, 3));
            }

            purchaseProductRepository.flush();
        }
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

            for (int i = 0; i < (NB_USERS + 150); i++) {
                Adress adress = new Adress();
                adress.setCity(faker.address().city());
                adress.setCountry("France");
                adress.setZipCode(faker.address().zipCode());
                adress.setLine(faker.address().streetAddress());

                if (hasProbability(1, 4)) {
                    adress.setExtraLine(faker.address().buildingNumber());
                }

                Random random = new Random();
                Long userId = random.nextLong(1, 100);
                Optional<User> optionalUser = userRepository.findById(userId);
                // Si l'objet attendu via notre "Optional" est présent (ifPresent),
                // alors on appelle la fonction "setUser" depuis l'objet "address" avec l'objet présent dans l'optional
                optionalUser.ifPresent(adress::setUser);

                if (hasProbability(2, 4)) {
                    adress.setFirstname(adress.getUser().getFirstname());
                    adress.setLastname(adress.getUser().getLastname());
                    adress.setPhone(adress.getUser().getPhone());
                } else {
                    adress.setFirstname(faker.name().firstName());
                    adress.setLastname(faker.name().lastName());
                    adress.setPhone(faker.phoneNumber().phoneNumber());
                }

                int year = adress.getUser().getCreatedAt().getYear();
                int currentYear = LocalDate.now().getYear();
                adress.setCreatedAt(getRandomDate(faker, 0, currentYear - year));

                adressRepository.save(adress);
            }

            adressRepository.flush();
        }
    }

    private void createUsers() {
        if (0 == userRepository.count()) {
            Faker faker = new Faker(Locale.of("fr"));

            for (int i = 0; i < NB_USERS; i++) {
                User user = new User();
                user.setFirstname(faker.name().firstName());
                user.setLastname(faker.name().lastName());
                String email = faker.internet().emailAddress((user.getFirstname() + "." + user.getLastname()).toLowerCase());
                user.setEmail(email);
                user.setPassword(faker.internet().password(12, 20));
                if (hasProbability(2, 3)) {
                    user.setPhone(faker.phoneNumber().phoneNumber());
                }
                user.setCreatedAt(getRandomDate(faker, 0, 5));
                userRepository.save(user);
            }

            userRepository.flush();
        }
    }

    private void createProduct() {
        if (0 == productRepository.count()) {
            Faker faker = new Faker(Locale.of("fr"));
            List<String> productsUsed = new ArrayList<>();

            for (int i = 0; i < 300; i++) {
                String name;
                do {
                    name = faker.commerce().productName();
                } while (productsUsed.contains(name));
                productsUsed.add(name);

                Product product = getProduct(
                        faker,
                        name,
                        name + " : " + faker.lorem().words(80),
                        faker.number().numberBetween(499, 30000)
                );

                Optional<Brand> optionalBrand = brandRepository.findById((long) faker.number().numberBetween(1,50));
                optionalBrand.ifPresent(product::setBrand);

                productRepository.save(product);
            }

            productRepository.flush();
        }
    }

    public void createImages() {
        if (0 == imageRepository.count()) {
            List<Product> products = productRepository.findAll();
            for (Product product : products) {
                createImagesByProduct(product, true);

                do {
                    createImagesByProduct(product, false);
                } while(hasProbability(1, 2));
            }

            imageRepository.flush();
        }
    }

    public void createPurchase() {
        if(0 == purchaseRepository.count()){
            Faker faker = new Faker(Locale.of("fr"));

            for (int i = 0; i < 500; i++) {
                Purchase purchase = new Purchase();
                String tmpNumber;
                if (i < 10) {
                    tmpNumber = "0000000000" + i;
                } else if (i < 100) {
                    tmpNumber = "000000000" + i;
                } else {
                    tmpNumber = "00000000" + i;
                }
                String number = "PUR-" + LocalDateTime.now().getYear() + "-" + tmpNumber;
                purchase.setNumber(number);

                User user;
                List<Adress> adresses;
                Random random = new Random();

                do {
                    Long idUser = random.nextLong(1,100);
                    Optional<User> optionalUser = userRepository.findById(idUser);
                    user = optionalUser.orElseThrow();
                    adresses = adressRepository.findAllByUser(user);
                } while (adresses.isEmpty());

                purchase.setUser(user);
                purchase.setAdress(adresses.getFirst());

                int year = user.getCreatedAt().getYear();
                int currentYear = LocalDate.now().getYear();
                int yearBetween = currentYear - year;
                purchase.setCreatedAt(getRandomDate(faker, 0, yearBetween));

                if (yearBetween > 1) {
                    purchase.setStatus(EnumPurchaseStatus.DELIVERED.getValue());
                } else {
                    int status = faker.number().numberBetween(1, 5);
                    switch (status) {
                        case 1:
                            purchase.setStatus(EnumPurchaseStatus.PENDING.getValue());
                            break;
                        case 2:
                            purchase.setStatus(EnumPurchaseStatus.PAID.getValue());
                            break;
                        case 3:
                            purchase.setStatus(EnumPurchaseStatus.SHIPPED.getValue());
                            break;
                        default:
                            purchase.setStatus(EnumPurchaseStatus.CANCELLED.getValue());
                            break;
                    }
                }

                purchaseRepository.save(purchase);
            }
            purchaseRepository.flush();
        }
    }

    private void createPurchaseProductByPurchase(Purchase purchase) {
        Faker faker = new Faker(Locale.of("fr"));
        PurchaseProduct purchaseProduct = new PurchaseProduct();
        purchaseProduct.setPurchase(purchase);
        purchaseProduct.setQuantity(faker.number().numberBetween(1, 6));

        Optional<Product> productOptional = productRepository.findById(faker.number().numberBetween(1L, 300L));
        productOptional.ifPresent(purchaseProduct::setProduct);

        int priceProduct = purchaseProduct.getProduct().getPrice();
        purchaseProduct.setPrice(priceProduct);
        if (hasProbability(1, 6)) {
            purchaseProduct.setPrice((int)(priceProduct * 1.2));
        }

        purchaseProductRepository.save(purchaseProduct);
    }

    private Product getProduct(Faker faker, String label, String description, int price) {
        Product product = new Product();
        product.setLabel(label);
        product.setCreatedAt(getRandomDate(faker, 0, 10));
        product.setPrice(price);
        product.setSlug(slugger.slugify(label));
        product.setDescription(description);
        return product;
    }

    private LocalDateTime getRandomDate(Faker faker, int min, int max) {
        return new java.sql.Date(faker.date().birthday(min, max).getTime()).toLocalDate().atTime(0, 0);
    }

    private void createImagesByProduct(Product product, boolean isDefault) {
        Image image = new Image();
        Random rand = new Random();
        image.setPath("https://picsum.photos/400/300?r=" + rand.nextInt(300));
        image.setExtension("png");
        image.setIsDefault(isDefault);
        image.setProduct(product);
        imageRepository.save(image);
    }

    private boolean hasProbability(int expected, int max) {
        Random random = new Random();
        // random.nextInt(max) = génère un entier aléatoire entre 0 et MAX
        // expected = la proba attendue
        // Si le random est inférieure ou égale à la prob, alors on renvoie true
        return random.nextInt(max) < expected;
    }

}