package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.AuthorService;
import com.example.MyBookShopApp.data.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bookshop")
public class MainPageController {

    private final AuthorService authorService;
    private final BookService bookService;


    @Autowired
    public MainPageController(AuthorService authorService, BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @GetMapping("/main")
    public String mainPage(Model model) {
        model.addAttribute("authorService", authorService);
        model.addAttribute("bookData", bookService.getBooksData());
        return "index";
    }
}
