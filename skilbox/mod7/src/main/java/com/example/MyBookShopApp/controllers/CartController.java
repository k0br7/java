package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.book.Book;
import com.example.MyBookShopApp.service.BookService;
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
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private BookService bookService;

    @ModelAttribute("cartBooks")
    public List<Book> cartBooks(@CookieValue(name = "CART", required = false) String CART, Model model){
        if(CART == null || CART.isEmpty())
            return new ArrayList<>();
        return bookService.getBookFromCookie(CART);
    }

    @ModelAttribute("discountPrice")
    public Integer discountPrice(@CookieValue(name = "CART", required = false) String CART, Model model){
        var discountPrice = 0;
        var books = cartBooks(CART,model);

        for(var b : books)
            discountPrice+=b.getDiscountPrice();

        return discountPrice;
    }
    @ModelAttribute("price")
    public Integer price(@CookieValue(name = "CART", required = false) String CART, Model model){
        var price = 0;
        var books = cartBooks(CART,model);

        for(var b : books)
            price+=b.getPrice();

        return price;
    }

    @GetMapping
    public String cartPage(){
        return "cart";
    }

}
