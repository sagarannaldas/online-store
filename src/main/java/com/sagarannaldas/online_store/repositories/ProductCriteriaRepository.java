package com.sagarannaldas.online_store.repositories;

import com.sagarannaldas.online_store.entities.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductCriteriaRepository {
    List<Product> findProductsByCriteria(String name, BigDecimal minPrice, BigDecimal maxPrice);
}
