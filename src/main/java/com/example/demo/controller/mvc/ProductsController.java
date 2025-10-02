package com.example.demo.controller.mvc;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.example.demo.model.Products;
import com.example.demo.services.IProductsService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class ProductsController {

    private final IProductsService productService;

    @GetMapping("/")
    public String redirectToProducts() {
        return "redirect:/products";
    }

    @GetMapping("/products")
    public String getProducts(Model model) {
        List<Products> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/products/new")
    public String showCreateForm(Model model) {
        model.addAttribute("product", new Products());
        model.addAttribute("formTitle", "Crear producto");
        model.addAttribute("submitLabel", "Guardar");
        return "product-form";
    }

    @PostMapping("/products")
    public String createProduct(@ModelAttribute("product") Products product) {
        productService.createProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/products/{id}")
    public String getProductDetail(Model model, @PathVariable Long id) {
        Products productDetail = productService.getProductById(id);
        if (productDetail == null) {
            return "redirect:/products";
        }
        model.addAttribute("product", productDetail);
        return "productdetail";
    }

    @GetMapping("/products/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Products product = productService.getProductById(id);
        if (product == null) {
            return "redirect:/products";
        }
        model.addAttribute("product", product);
        model.addAttribute("formTitle", "Editar producto");
        model.addAttribute("submitLabel", "Actualizar");
        return "product-form";
    }

    @PutMapping("/products/{id}")
    public String updateProduct(@PathVariable Long id, @ModelAttribute("product") Products product) {
        Products updated = productService.updateProduct(id, product);
        if (updated == null) {
            return "redirect:/products";
        }
        return "redirect:/products";
    }

    @DeleteMapping("/products/{id}")
    public String deleteProduct(@PathVariable Long id) {
        try {
            productService.deleteProduct(id);
        } catch (RuntimeException ex) {
            // Si el producto no existe simplemente regresamos al listado
        }
        return "redirect:/products";
    }
}
