package com.luv2code.springboot.cruddemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RoutingController {
    @GetMapping("/")
    public String Main() {

        return "/secure/index";
    }
    @GetMapping("/secure/login")
    public String login() {
        System.out.println("start login !!!!");
        return "/secure/login";
    }




}
