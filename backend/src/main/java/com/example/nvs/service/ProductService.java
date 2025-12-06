package com.example.nvs.service;

import com.example.nvs.model.Product;
import com.example.nvs.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository repo;

    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    // Get all products
    public List<Product> all() {
        return repo.findAll();
    }

    // Save a new product
    public Product save(Product p) {
        return repo.save(p);
    }

    // Find product by ID
    public Product find(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + id));
    }

    // Update product
    public Product update(Long id, Product updatedProduct) {
        Product existingProduct = find(id); // fetch existing
        existingProduct.setName(updatedProduct.getName());
        existingProduct.setDescription(updatedProduct.getDescription());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setStock(updatedProduct.getStock());
        return repo.save(existingProduct);
    }

    // Delete product
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
