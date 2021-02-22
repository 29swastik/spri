package com.example.demospring.Service;

import com.example.demospring.DTO.MyRequestDTO;
import com.example.demospring.DTO.MyResponseDTO;

public interface UserService {

    boolean updateEmployee(MyRequestDTO request, String id);


}
