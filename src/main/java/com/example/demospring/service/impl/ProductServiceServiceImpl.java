package com.example.demospring.service.impl;

import com.example.demospring.client.SearchClient;
import com.example.demospring.dto.ProductDTO;
import com.example.demospring.dto.SearchRequestDTO;
import com.example.demospring.dto.SearchResponseDTO;
import com.example.demospring.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.*;

@Service
public class ProductServiceServiceImpl implements ProductService {

    @Autowired
    private SearchClient searchClient;

    @Override
    public SearchResponseDTO getProduct(SearchRequestDTO request, String searchTerm) {

        request.setSearchTerm(searchTerm);

        Map<String, Object> products = searchClient.getProducts(request.getSearchTerm());

        System.out.println(products.get("response"));

        Map<String, Object> response = (Map<String, Object>) products.get("response");
        List<Map<String, Object> >docs = (List<Map<String, Object>>) response.get("docs");




        SearchResponseDTO responseDTO = new SearchResponseDTO();
        ProductDTO productDTO = new ProductDTO();
        productDTO.setDescription((String) docs.get(0).get("description"));

        int res = (int) docs.get(0).get("isInStock");
        if(res == 1) {
            productDTO.setInStock(true);
        }

        else{
            productDTO.setInStock(false);
        }

        productDTO.setSalePrice((double) docs.get(0).get("salePrice"));
        productDTO.setTitle(searchTerm);
        responseDTO.setProducts(Arrays.asList(productDTO));
        return responseDTO;

    }
}
