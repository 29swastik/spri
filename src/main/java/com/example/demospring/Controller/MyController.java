package com.example.demospring.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @GetMapping(path = "/hello")
    public String helloWorld()
    {
        return "Spring Success Finally";
    }

}
