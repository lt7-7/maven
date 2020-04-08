package org.example.spring.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api")
@Controller

public class ApiController {
    @RequestMapping("test")
    public String testApi(){
        return "/index.jsp";
    }
}
