package com.example.SpringSecurity.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String home(HttpServletRequest request){
        return "Welcome Home, Your session Id is" + request.getSession().getId();
    }

    @GetMapping("/about-us")
    public String aboutUs(){
        return "About us";
    }

    @GetMapping("/contact")
    public String contact(){
        return "Contact details";
    }

    @GetMapping("/public/demo")
    public String demo(){
        return "Public page demo code...";
    }


}
