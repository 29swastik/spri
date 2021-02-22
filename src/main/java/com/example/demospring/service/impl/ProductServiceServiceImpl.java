package com.example.demospring.service.impl;

import com.example.demospring.dto.ProductDTO;
import com.example.demospring.dto.SearchRequestDTO;
import com.example.demospring.dto.SearchResponseDTO;
import com.example.demospring.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class ProductServiceServiceImpl implements ProductService {

    @Override
    public SearchResponseDTO getProduct(SearchRequestDTO request, String id) {
        SearchResponseDTO responseDTO = new SearchResponseDTO();
        ProductDTO productDTO = new ProductDTO();
        productDTO.setDescription("Samsung Galaxy 12MP camera");
        productDTO.setInStock(true);
        productDTO.setSalePrice(10000);
        productDTO.setTitle("M21");
        responseDTO.setProducts(Arrays.asList(productDTO));
        return responseDTO;
    }
}
