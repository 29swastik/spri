package com.example.demospring.service.impl;

import com.example.demospring.client.SearchClient;
import com.example.demospring.dto.SearchRequestDTO;
import com.example.demospring.dto.SearchResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private SearchClient searchClient;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
    }

//    @Test
//    void getProduts() throws IOException {
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        Map<String, Object> searchTermMockObject = objectMapper.readValue(new URL("file:src/test/resources/search.mock"), Map.class);
//
//        Map<String, Object> locationMockObject = objectMapper.readValue(new URL("file:src/test/resources/location.mock"), Map.class);
//
//        Mockito.when(searchClient.getProducts("samsung")).thenReturn(searchTermMockObject);
//        Mockito.when(searchClient.getProducts("stockLocation:\"Jakarta\"")).thenReturn(locationMockObject);
//
//        SearchRequestDTO searchRequestDTO = new SearchRequestDTO();
//        searchRequestDTO.setSearchTerm("samsung");
//        searchRequestDTO.setStockLocation("Jakarta");
//        SearchResponseDTO searchResponseDTO = productService.getProductLocation(searchRequestDTO);
//
//        assertEquals(searchResponseDTO.getProducts().size(), 10);
//        assertEquals(searchResponseDTO.getLocationBasedProducts(), null);
//    }

    @Test
    public void testGetProductsExceptionTest() throws IOException{
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> searchTermMockObject = objectMapper.readValue(new URL("file:src/test/resources/search.mock"), Map.class);

        Map<String, Object> locationMockObject = objectMapper.readValue(new URL("file:src/test/resources/location.mock"), Map.class);

        Mockito.when(searchClient.getProducts("samsung")).thenReturn(searchTermMockObject);
        Mockito.when(searchClient.getProducts("stockLocation:\"Jakarta\"")).thenThrow(NullPointerException.class);

        SearchRequestDTO searchRequestDTO = new SearchRequestDTO();
        searchRequestDTO.setSearchTerm("samsung");
        searchRequestDTO.setStockLocation("Jakarta");
        SearchResponseDTO searchResponseDTO = productService.getProductLocation(searchRequestDTO);

        assertEquals(searchResponseDTO.getProducts().size(), 10);
        assertEquals(searchResponseDTO.getLocationBasedProducts(), null);

        Mockito.verify(searchClient).getProducts("samsung");
        Mockito.verify(searchClient).getProducts("stockLocation:\"Jakarta\"");
    }

}