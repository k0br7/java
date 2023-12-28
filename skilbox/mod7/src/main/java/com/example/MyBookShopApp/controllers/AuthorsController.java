package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.author.Author;
import com.example.MyBookShopApp.service.AuthorService;
import com.example.MyBookShopApp.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@Api(description = "authors data")
public class AuthorsController {

    private final AuthorService authorService;
    @Autowired
    private BookService bookService;

    @Autowired
    public AuthorsController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @ModelAttribute("authorsMap")
    public Map<String,List<Author>> authorsMap(){
        return authorService.getAuthorsMap();
    }

    @GetMapping("/authors")
    public String authorsPage(){
        return "/authors/index";
    }

    @GetMapping("/authors/{slug}")
    public String authorsPageBySlug(@PathVariable String slug, Model model){

        var author = authorService.getAuthorBySlug(slug);
        var booksList = bookService.getBooksByAuthor(author,0,6);
        var countBooksByAuthor=bookService.countByAuthor(author);

        model.addAttribute("author",author);
        model.addAttribute("booksList",booksList);
        model.addAttribute("countBooksByAuthor",countBooksByAuthor);

        return "/authors/slug";
    }
    @GetMapping("/books/author/{authorSlug}")
    public String authorBooks(@PathVariable String authorSlug, Model model){

        var author = authorService.getAuthorBySlug(authorSlug);
        var booksList = bookService.getRecentBooksByAuthor(author,0,20);

        model.addAttribute("author",author);
        model.addAttribute("booksList",booksList);

        return "/books/author";
    }




    @ApiOperation("method to get map of authors")
    @GetMapping("/api/authors")
    @ResponseBody
    public Map<String,List<Author>> authors(){
        return authorService.getAuthorsMap();
    }
}
