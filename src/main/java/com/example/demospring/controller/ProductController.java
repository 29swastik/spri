package com.example.demospring.controller;

import com.example.demospring.dto.SearchRequestDTO;
import com.example.demospring.dto.SearchResponseDTO;
import com.example.demospring.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
public class ProductController {

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @PostConstruct
    public void init() {

    }

    @Autowired
    private ProductService productService;

    @PostMapping(path = "/search/{searchTerm}")
    public SearchResponseDTO Search(@RequestBody SearchRequestDTO request, @PathVariable String searchTerm) {

        return productService.getProduct(request, searchTerm);

    }
}
