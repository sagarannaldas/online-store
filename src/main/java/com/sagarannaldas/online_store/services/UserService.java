package com.sagarannaldas.online_store.services;

import com.sagarannaldas.online_store.entities.*;
import com.sagarannaldas.online_store.repositories.*;
import com.sagarannaldas.online_store.repositories.specifications.ProductsSpec;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
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
                .name("kphb")
                .email("kphb@gmail.com")
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
    public void fetchProducts() {
        var products = productRepository.findProducts(BigDecimal.valueOf(1), BigDecimal.valueOf(12));
        System.out.println(products);

        // Query by Example
        var product = new Product();
        product.setName("product");

        var matcher = ExampleMatcher.matching()
                .withIncludeNullValues()
                .withIgnorePaths("id", "description")
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        var example = Example.of(product, matcher);

        var products1 = productRepository.findAll(example);
        products1.forEach(System.out::println);
    }

    @Transactional
    public void fetchProductsByCriteria() {
        var products = productRepository.findProductsByCriteria("prod", BigDecimal.valueOf(1), null);
        System.out.println(products);
    }

    public void fetchProductsBySpecifications(String name, BigDecimal minPrice, BigDecimal maxPrice) {
        Specification<Product> spec = (root, query, builder) -> null;
        if (name != null) {
            spec = spec.and(ProductsSpec.hasName(name));
        }
        if (minPrice != null) {
            spec = spec.and(ProductsSpec.hasPriceLessThanOrEqualTo(minPrice));
        }
        if (maxPrice != null) {
            spec = spec.and(ProductsSpec.hasPriceGreaterThanOrEqualTo(maxPrice));
        }

        productRepository.findAll(spec).forEach(System.out::println);
    }

    @Transactional
    public void fetchUser() {
        var users = userRepository.findAllWithAddress();
        users.forEach(user -> {
            System.out.println(user);
            user.getAddresses().forEach(System.out::println);
        });
    }

    public void fetchSortedProducts() {
        var sort = Sort.by("name").and(Sort.by("price").descending());
        productRepository.findAll(sort).forEach(System.out::println);
    }

    public void fetchPaginatedProducts(int pageNumber, int size) {
        PageRequest pageRequest = PageRequest.of(pageNumber, size);
        Page<Product> page = productRepository.findAll(pageRequest);
        var products = page.getContent();
        products.forEach(System.out::println);

        var totalPages = page.getTotalPages();
        var totalElements = page.getTotalElements();
        System.out.println("Total pages: " + totalPages + " " + "Total elements: " + totalElements);

    }

    @Transactional
    public void printLoyaltyProfiles() {
        var users = userRepository.findLoyalUsers(2);
        users.forEach(p -> System.out.println(p.getId() + " " + p.getEmail()));
    }
}
