package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.book.Book;
import com.example.MyBookShopApp.service.BookService;
import liquibase.pro.packaged.K;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/postponed")
public class PostponedController {

    @Autowired
    private  BookService bookService;

    @ModelAttribute("postponedBooks")
    public List<Book> postponedBooks(@CookieValue(name = "KEPT", required = false) String KEPT, Model model){
        if(KEPT == null || KEPT.isEmpty())
            return new ArrayList<>();
        return bookService.getBookFromCookie(KEPT);
    }

    @GetMapping
    public String postponedPage(){
        return "postponed";
    }

}
