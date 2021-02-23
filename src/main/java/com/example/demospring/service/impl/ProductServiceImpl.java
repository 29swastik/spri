package com.example.demospring.service.impl;

import com.example.demospring.dto.SearchRequestDTO;
import com.example.demospring.dto.SearchResponseDTO;
import com.example.demospring.service.ProductService;
import com.example.demospring.client.SearchClient;
import com.example.demospring.constants.SolrFieldNames;
import com.example.demospring.dto.ProductDTO;
import java.util.concurrent.Executors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private SearchClient searchClient;

    public List<ProductDTO> getProduct(String query) {

        Map<String, Object> productResponse = searchClient.getProducts(query);
        Map<String, Object> responseObject = (Map<String, Object>) (productResponse.get("response"));
        List<Map<String, Object>> products = (List<Map<String, Object>>) responseObject.get("docs");
        List<ProductDTO> productDTOS = new ArrayList<>();

        for (Map<String, Object> productClientResponse :products) {

            String title = (String) productClientResponse.get(SolrFieldNames.NAME);
            boolean inStock = (int) productClientResponse.get(SolrFieldNames.IN_STOCK) == 1? true: false;
            String description = (String) productClientResponse.get(SolrFieldNames.DESCRIPTION);
            double salePrice = (double) productClientResponse.get(SolrFieldNames.SALE_PRICE);

            ProductDTO productDTO = new ProductDTO();
            productDTO.setInStock(inStock);
            productDTO.setTitle(title);
            productDTO.setSalePrice(salePrice);
            productDTO.setDescription(description);

            productDTOS.add(productDTO);
        }




        return productDTOS;
    }

    @Override
    public SearchResponseDTO getProductLocation(SearchRequestDTO request) {

        SearchResponseDTO responseDTO = new SearchResponseDTO();
        ExecutorService threadPool = Executors.newFixedThreadPool(2);

        threadPool.execute(() -> {
            String searchTermQuery = request.getSearchTerm();
            List<ProductDTO> productDTOS = getProduct(searchTermQuery);
            responseDTO.setProducts(productDTOS);
        });

        threadPool.execute(() -> {
            String locationQuery = SolrFieldNames.STOCK_LOCATION + ":\"" + request.getStockLocation() + "\"";
            List<ProductDTO> locationProductDTOs = getProduct(locationQuery);
            responseDTO.setLocationBasedProducts(locationProductDTOs);
        });

        awaitTerminationAfterShutdown(threadPool);
        return responseDTO;
    }

    private void awaitTerminationAfterShutdown(ExecutorService threadPool) {
        threadPool.shutdown();
        try {
            if (!threadPool.awaitTermination(60, TimeUnit.SECONDS)) {
                threadPool.shutdownNow();
            }
        } catch (InterruptedException ex) {
            threadPool.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

}
