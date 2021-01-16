package ru.geekbrains.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.market.dto.ProductDto;
import ru.geekbrains.market.model.Product;
import ru.geekbrains.market.services.ProductService;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        return productService.findProductById(id).get();
    }

    @GetMapping
    public Page<ProductDto> getAllProducts(@RequestParam(name = "min_price", defaultValue = "0") Integer minPrice,
                                           @RequestParam(name = "max_price", required = false) Integer maxPrice,
                                           @RequestParam(name = "page", defaultValue = "1") Integer page) {
        if (page < 1) {
            page = 1;
        }
        return productService.findAll(page);
    }

    @PostMapping
    public ProductDto savaNewProduct(@RequestBody Product product) {
        product.setId(null);
        return productService.saveOrUpdate(product);
    }

    @PutMapping
    public ProductDto updateProduct(@RequestBody Product product) {
        return productService.saveOrUpdate(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
    }
}
