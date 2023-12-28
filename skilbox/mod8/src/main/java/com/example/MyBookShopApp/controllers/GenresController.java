package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.book.Book;
import com.example.MyBookShopApp.service.BookService;
import com.example.MyBookShopApp.service.GenresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/genres")
public class GenresController {

    @Autowired
    private GenresService genresService;

    @Autowired
    private BookService bookService;

    @GetMapping
    public String genresPage(Model model){
        model.addAttribute("genresService",genresService);
        return "genres/index";
    }



    @GetMapping("/{slug}")
    public String genresPageBySlug(@PathVariable String slug, Model model){
        var genre = genresService.getBySlug(slug);
        var b2g = genresService.getAllByGenre(0,5,genre).getContent();
        var booksList = new ArrayList<Book>();

        for (var b : b2g)
            booksList.add(b.getBook());

        if(genre == null)
            return "redirect:/genres";

        model.addAttribute("genre",genre);
        model.addAttribute("booksList",booksList);

        return "genres/slug";
    }
}
