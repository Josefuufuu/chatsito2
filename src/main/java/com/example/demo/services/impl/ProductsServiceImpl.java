package com.example.demo.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Products;
import com.example.demo.repository.IProductsRepository;
import com.example.demo.services.IProductsService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductsServiceImpl implements IProductsService {

    private final IProductsRepository productsRepository;
    
    @Override
    public List<Products> getAllProducts() {
        return productsRepository.findAll();
    }

    @Override
    public Products getProductById(Long id) {
        return productsRepository.findById(id).orElse(null);
    }

    @Override
    public Products createProduct(Products product) {
        return productsRepository.save(product);
    }

    @Override
    public Products updateProduct(Long id, Products product) {
        if (!productsRepository.existsById(id)) {
            return null;
        }
        product.setId(id);
        return productsRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        if (productsRepository.existsById(id)) {
            productsRepository.deleteById(id);
        } else {
            throw new RuntimeException("Product not found with id: " + id);
        }
    }

}
