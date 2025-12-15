package com.jenkinsdemo;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("print")
    public String print(){
        return "Hello World from Jenkins Demo";
    }
}
