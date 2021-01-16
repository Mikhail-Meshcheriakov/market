package ru.geekbrains.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.market.model.Product;
import ru.geekbrains.market.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
    }

    public List<Product> findAll() {
        return (List<Product>) productRepository.findAll();
    }

    public List<Product> findAllByPrice(int min, int max, int page) {
        List<Product> products = productRepository.findAllByPriceBetween(min, max);
        int numberOfProducts = products.size();
        if (page <= 0 || (page * 10) > numberOfProducts) {
            page = 1;
        }
        int fromIndex = page * 10 - 10;
        int toIndex = Math.min(page * 10, numberOfProducts);
        return products.subList(fromIndex, toIndex);
    }

    public Product saveOrUpdate(Product product) {
        return productRepository.save(product);
    }

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }
}
