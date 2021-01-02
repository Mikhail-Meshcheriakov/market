package ru.geekbrains.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.market.model.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByPriceGreaterThanEqual(Integer price);

    List<Product> findAllByPriceLessThanEqual(Integer price);

    List<Product> findAllByPriceBetween(Integer min, Integer max);
}
