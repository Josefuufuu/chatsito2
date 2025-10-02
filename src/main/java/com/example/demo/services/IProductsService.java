package com.example.demo.services;

import java.util.List;

import com.example.demo.model.Products;

public interface IProductsService {
    List<Products> getAllProducts();
    Products getProductById(Long id);
    Products createProduct(Products product);
    Products updateProduct(Long id, Products product);
    void deleteProduct(Long id);
}
