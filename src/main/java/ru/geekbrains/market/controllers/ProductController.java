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
    public List<Product> getAllProducts(@RequestParam(required = false) Integer min_price, @RequestParam(required = false) Integer max_price) {
        if (min_price != null && max_price != null) {
            return productService.findAllByPrice(min_price, max_price);
        }
        if (min_price != null) {
            return productService.findAllByPriceGreater(min_price);
        }
        if (max_price != null) {
            return productService.findAllByPriceLess(max_price);
        }
        return (List<Product>) productService.findAll();
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
