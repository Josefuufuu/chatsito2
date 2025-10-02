package com.example.demo.controller.mvc;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.services.IProductsService;

import org.springframework.ui.Model;
import lombok.RequiredArgsConstructor;
import com.example.demo.model.Products;
import java.util.*;

@RequiredArgsConstructor
@Controller
public class ProductsController {
    
    private final IProductsService productService;

    @GetMapping("products")
    public String getProducts(Model model){
        List<Products> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("productdetail/{product}")
    public String getProductDetail(Model model, @PathVariable Long product){
        Products productDetail = productService.getProductById(product);
        model.addAttribute("product", productDetail);
        return "productdetail";
    }
}
