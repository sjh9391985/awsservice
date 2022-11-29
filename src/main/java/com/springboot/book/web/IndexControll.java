package com.springboot.book.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexControll {

    @GetMapping("/")
    public String index(){
        return "index";
    }

}
