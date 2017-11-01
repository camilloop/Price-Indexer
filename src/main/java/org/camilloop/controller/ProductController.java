package org.camilloop.controller;

import org.camilloop.model.Product;
import org.camilloop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;


@RestController
@RequestMapping("/api/products")
public class ProductController {

    private ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Product>> allProducts() {
        Iterable<Product> allProducts = productRepository.findAll();
        return ResponseEntity.ok(allProducts);
    }

    @GetMapping(path = "/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> getProductByName(@PathVariable String name) {
        Product product = productRepository.findByName(name);
        return ResponseEntity.ok(product);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveProduct(@RequestBody Product product) {
        Product save = productRepository.save(product);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{name}")
                .buildAndExpand(save.getName())
                .toUri();
        return ResponseEntity.created(location).body(save);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deleteWebsiteByName(@PathVariable String name) {
        productRepository.deleteById(name);
        return ResponseEntity.noContent().build();
    }
}
