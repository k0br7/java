package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.tags.Tag;
import com.example.MyBookShopApp.service.BookService;
import com.example.MyBookShopApp.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/tags")
public class TagController {

    @Autowired
    private TagService tagService;
    @Autowired
    private BookService bookService;

    @ModelAttribute("tagcloud")
    public Map<Tag, String> getTagCloud() {
        return tagService.getTagCloud();
    }


    @GetMapping("/{slug}")
    public String tags(@PathVariable String slug, Model model) {

        var tag = tagService.getTagBySlug(slug);
        var booksList = bookService.getBooksByTag(tag, 0, 5);

        model.addAttribute("tag", tag);
        model.addAttribute("booksList", booksList);


        return "tags/index";
    }

}
