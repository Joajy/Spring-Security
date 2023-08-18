package com.Kim.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;

@Controller
public class IndexController {

    @GetMapping({"", "/"})
    public String index(){
        return "index";
    }


}
