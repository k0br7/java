package com.example.MyBookShopApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/search")
public class SearchController {

    @GetMapping("/{query}")
    public String searchPage(@PathVariable(required = true) String query){
        return "search/index";
    }
//    @GetMapping
//    public String searchPage(@RequestParam(required = true) String query){
//        return "search/index";
//    }
}
