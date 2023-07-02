package com.moaydogdu.ssexample5.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public String test(){
        return "You accessed the endpoint perfectly.";
    }

}
