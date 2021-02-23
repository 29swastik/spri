package com.example.demospring.service;

import com.example.demospring.dto.ProductDTO;
import com.example.demospring.dto.SearchRequestDTO;
import com.example.demospring.dto.SearchResponseDTO;

import java.util.List;

public interface ProductService {

    SearchResponseDTO getProductLocation(SearchRequestDTO requestDTO);

}
