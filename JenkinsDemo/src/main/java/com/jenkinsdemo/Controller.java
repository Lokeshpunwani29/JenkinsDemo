package com.jenkinsdemo;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/")
public class Controller {
    @GetMapping("print")
    public String print(){
        return "Hello World from JenkinsDemo.";
    }
}
