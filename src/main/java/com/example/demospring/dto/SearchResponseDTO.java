package com.example.demospring.dto;

import java.util.List;

public class SearchResponseDTO {

    private List<ProductDTO> products;

    public List<ProductDTO> getProducts(){
        return products;
    }

    public void setProducts(List<ProductDTO> products){
        this.products = products;
    }
}
