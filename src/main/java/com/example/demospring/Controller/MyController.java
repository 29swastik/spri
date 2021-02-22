package com.example.demospring.Controller;
import com.example.demospring.DTO.MyRequestDTO;
import com.example.demospring.DTO.MyResponseDTO;
import org.springframework.web.bind.annotation.*;

@RestController
public class MyController {

    @GetMapping(path = "/hello")
    public String helloWorld()
    {
        return "Spring Success Finally";
    }

    @PostMapping(path = "/hello-post")
    public String helloWorldPost()
    {
        return "Hello Post";
    }

    @GetMapping(path = "/hello-query")
    public String helloQuery(@RequestParam String query)
    {
        return "query" + query;
    }

    @PostMapping(value = "/register")
    public String registerUser(@RequestBody MyRequestDTO request)
    {
        return request.toString();
    }

    //[GET] /employee/{employeeID}
    //  /employee/123

    @GetMapping(path = "/employee/{employeeID}")
    public MyResponseDTO getEmployeeDetails(@PathVariable String employeeID)
    {
        MyResponseDTO response = new MyResponseDTO();
        response.setId(employeeID);
        return response;
    }

}
