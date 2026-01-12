package com.sagarannaldas.online_store.services;

import com.sagarannaldas.online_store.entities.Address;
import com.sagarannaldas.online_store.entities.Category;
import com.sagarannaldas.online_store.entities.Product;
import com.sagarannaldas.online_store.entities.User;
import com.sagarannaldas.online_store.repositories.*;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final AddressRepository addressRepository;
    private final CategoryRepository categoryRepository;
    private final EntityManager entityManager;
    private final ProductRepository productRepository;

    @Transactional
    public void showEntityStates() {
        var user = User.builder()
                .name("John")
                .email("john@gmail.com")
                .password("password")
                .build();

        if (entityManager.contains(user)) {
            System.out.println("Persistent");
        } else {
            System.out.println("Transient/ Detached");
        }
        userRepository.save(user);

        if (entityManager.contains(user)) {
            System.out.println("Persistent");
        } else {
            System.out.println("Transient/ Detached");
        }
    }

    @Transactional
    public void showRelatedEntityStates() {
        var profile = profileRepository.findById(1L).orElseThrow();
        System.out.println(profile.getUser().getEmail());
    }

    public void fetchAddress(Long userId) {
        var address = addressRepository.findById(1L).orElseThrow();
        System.out.println(address);
    }

    public void persistRelated() {
        var user = User.builder()
                .name("mac")
                .email("mac@gmail.com")
                .password("password")
                .build();

        var address = Address.builder()
                .city("hyd")
                .state("hyd")
                .street("kphb")
                .zip("50003")
                .build();

        user.addAddress(address);
        userRepository.save(user);
    }

    public void deleteRelated() {
        userRepository.deleteById(1L);
    }

    @Transactional
    public void childDeleteRelated() {
        var user = userRepository.findById(1L).orElseThrow();
        var address = user.getAddresses().get(0);
        user.removeAddress(address);
        userRepository.save(user);
    }

    @Transactional
    public void manageProduct() {
        var category = categoryRepository.findById((byte) 1).orElseThrow();

        var product = Product.builder()
                .name("product 2")
                .price(BigDecimal.valueOf(12))
                .description("new product 2")
                .category(category)
                .build();

        productRepository.save(product);
    }

    @Transactional
    public void wishlistProducts() {
        var user = userRepository.findById(2L).orElseThrow();
        var products = productRepository.findAll();
        products.forEach(user::addFavouriteProduct);
        userRepository.save(user);
    }

    @Transactional
    public void removeProductFromWishList() {
        productRepository.deleteById(5L);
    }

    @Transactional
    public void updateProductPrice() {
        productRepository.updatePriceByCategoryId(BigDecimal.valueOf(12), (byte) 1);
    }

    @Transactional
    public void fetchUser() {
        var user = userRepository.findByEmail("john@gmail.com").orElseThrow();
        System.out.println(user);
    }
}
