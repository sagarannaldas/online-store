package com.sagarannaldas.online_store.repositories.specifications;

import com.sagarannaldas.online_store.entities.Product;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class ProductsSpec {
    public static Specification<Product> hasName(String name) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("name"), "%" + name + "%");
    }

    public static Specification<Product> hasPriceGreaterThanOrEqualTo(BigDecimal price) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), price);
    }

    public static Specification<Product> hasPriceLessThanOrEqualTo(BigDecimal price) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("price"), price);
    }
}
