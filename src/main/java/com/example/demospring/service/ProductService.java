package com.example.demospring.service;

import com.example.demospring.dto.SearchRequestDTO;
import com.example.demospring.dto.SearchResponseDTO;

public interface ProductService {

    SearchResponseDTO getProduct(SearchRequestDTO request, String id);
}
