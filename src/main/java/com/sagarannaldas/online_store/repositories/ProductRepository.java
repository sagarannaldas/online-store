package com.sagarannaldas.online_store.repositories;

import com.sagarannaldas.online_store.entities.Product;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {

    //String
    List<Product> findByName(String name);

    List<Product> findByNameLike(String name);

    List<Product> findByNameNotLike(String name);

    List<Product> findByNameContaining(String name);

    List<Product> findByNameStartingWith(String name);

    List<Product> findByNameEndingWith(String name);

    List<Product> findByNameEndingWithIgnoreCase(String name);

    List<Product> findByNameContainingAndNameStartingWith(String search, String name);

    // Number - select * from products where price = ?
    List<Product> findByPrice(BigDecimal price);

    List<Product> findByPriceGreaterThan(BigDecimal price);

    List<Product> findByPriceLessThanEqual(BigDecimal price);

    List<Product> findByPriceBetween(BigDecimal lower, BigDecimal upper);

    // null
    List<Product> findByDescriptionNull();

    List<Product> findByDescriptionNotNull();

    // multiple conditions
    List<Product> findByDescriptionNullAndNameNull();

    // order (OrderBy)
    List<Product> findByNameOrderByPriceAsc(String name);

    List<Product> findByNameOrderByPriceDesc(String name);

    List<Product> findTop5ByNameOrderByPriceAsc(String name);

    List<Product> findTop5ByNameLikeOrderByPriceDesc(String name);

    // limit (Top/First)
    // Find products whose prices are in a given range nd sort by name
    // List<Product> findByPriceBetweenOrderByName(BigDecimal lower, BigDecimal upper);
    // SQL or JPQL
//    @Query(value = "select * from products p join categories where p.price between :min and :max order by p.name", nativeQuery = true)
    // stored procedure
    @Procedure("findProductByPrice")
    List<Product> findProducts(BigDecimal min, BigDecimal max);

    @Query(value = "select count(*) from products where price between :min and :max", nativeQuery = true)
    long countProducts(@Param("min") BigDecimal min, @Param("max") BigDecimal max);

    // for modifications
    @Modifying
    @Query(value = "update products set price = :price where category_id = :categoryId", nativeQuery = true)
    void updatePriceByCategoryId(BigDecimal price, Byte categoryId);

}
