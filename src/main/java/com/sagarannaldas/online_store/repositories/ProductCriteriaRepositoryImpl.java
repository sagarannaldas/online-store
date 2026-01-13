
package com.sagarannaldas.online_store.repositories;

import com.sagarannaldas.online_store.entities.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@Repository
public class ProductCriteriaRepositoryImpl implements ProductCriteriaRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public List<Product> findProductsByCriteria(String name, BigDecimal minPrice, BigDecimal maxPrice) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = builder.createQuery(Product.class);
        // from products
        Root<Product> root = criteriaQuery.from(Product.class);

        List<Predicate> predicates = new ArrayList<>();
        if (name != null) {
            // name like %name%
            predicates.add(builder.like(root.get("name"), "%" + name + "%"));
        }
        if (minPrice != null) {
            // price >= minPrice
            predicates.add(builder.greaterThanOrEqualTo(root.get("price"), minPrice));
        }
        if (maxPrice != null) {
            // price <= macPrice
            predicates.add(builder.lessThanOrEqualTo(root.get("price"), maxPrice));
        }

        criteriaQuery.select(root).where(predicates.toArray(new Predicate[predicates.size()]));

        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
