package ru.geekbrains.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.market.model.Product;
import ru.geekbrains.market.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.findProductById(id).get();
    }

    @GetMapping
    public List<Product> getAllProducts(@RequestParam(name = "minPrice", defaultValue = "0") Integer minPrice,
                                        @RequestParam(name = "maxPrice", required = false) Integer maxPrice,
                                        @RequestParam(name = "page", defaultValue = "1") Integer page) {
        if (maxPrice == null) {
            maxPrice = Integer.MAX_VALUE;
        }
        return productService.findAllByPrice(minPrice, maxPrice, page);
    }

    @PostMapping
    public Product savaNewProduct(@RequestBody Product product) {
        return productService.saveOrUpdate(product);
    }

    @GetMapping("/delete/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
    }
}
