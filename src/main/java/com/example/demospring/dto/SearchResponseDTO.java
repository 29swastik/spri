package com.example.demospring.dto;

import java.util.List;

public class SearchResponseDTO {

    private List<ProductDTO> products;

    public List<ProductDTO> getLocationBasedProducts() {
        return locationBasedProducts;
    }

    public void setLocationBasedProducts(List<ProductDTO> locationBasedProducts) {
        this.locationBasedProducts = locationBasedProducts;
    }

    private  List<ProductDTO> locationBasedProducts;

    public List<ProductDTO> getProducts(){
        return products;
    }

    public void setProducts(List<ProductDTO> products){
        this.products = products;
    }
}
