package com.example.MyBookShopApp.service;

import com.example.MyBookShopApp.data.enums.B2UType;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;
import java.util.StringJoiner;

@Service
public class ChangeBookService {

    public void changeBookStatus(B2UType status, Set<Integer> booksIds, String kept, String cart, String archived, HttpServletResponse httpServletResponse) {

        if (status == B2UType.KEPT) {
            change(B2UType.KEPT, booksIds, kept, httpServletResponse);
        } else if (status == B2UType.CART) {
            change(B2UType.CART, booksIds, cart, httpServletResponse);
        } else if (status == B2UType.PAID) {

        } else if (status == B2UType.ARCHIVED) {
            change(B2UType.ARCHIVED, booksIds, archived, httpServletResponse);
        } else if (status == B2UType.UNLINK) {
            unlinkBook(booksIds, kept, cart, archived, httpServletResponse);
        }

//        if(cartContents == null || cartContents.isEmpty()){
//            var stringBooksIds = booksIds.stream().map(Object::toString).toList();
//            stringJoiner.add(String.join("/",stringBooksIds));
//        }else{
//            var cookieBooksIdsList = cartContents.split("/");
//            for(var bookId : cookieBooksIdsList){
//                booksIds.add(Integer.parseInt(bookId));
//            }
//            var stringBooksIds = booksIds.stream().map(Object::toString).toList();
//            stringJoiner.add(String.join("/",stringBooksIds));
//        }
//
//        System.out.println(stringJoiner.toString());
//        System.out.println(stringJoiner.toString());
//        System.out.println(stringJoiner.toString());
//
//        var cookie = new Cookie("cartContents", stringJoiner.toString());
//        cookie.setPath("/");
//        httpServletResponse.addCookie(cookie);
    }

    private void unlinkBook(Set<Integer> booksIds, String kept, String cart, String archived, HttpServletResponse httpServletResponse) {
        System.out.println("===================");
        if (kept != null && !kept.isEmpty()) {
            var stringJoiner = new StringJoiner("/");
            var cookieBooksIdsList = kept.split("/");
            var newbooksIds = new HashSet<Integer>();
            for (var bookId : cookieBooksIdsList) {
                var x = Integer.parseInt(bookId);
                System.out.println(x);
                System.out.println(booksIds.contains(x));
                if (!booksIds.contains(x))
                    newbooksIds.add(x);
            }
            var stringBooksIds = booksIds.stream().map(Object::toString).toList();
            stringJoiner.add(String.join("/", stringBooksIds));

            var cookie = new Cookie(B2UType.KEPT.name(), stringJoiner.toString());
            cookie.setPath("/");
            httpServletResponse.addCookie(cookie);
        }

    }

    private void change(B2UType cookieName, Set<Integer> booksIds, String cookieData, HttpServletResponse httpServletResponse) {
        var stringJoiner = new StringJoiner("/");
        if (cookieData == null || cookieData.isEmpty()) {
            var stringBooksIds = booksIds.stream().map(Object::toString).toList();
            stringJoiner.add(String.join("/", stringBooksIds));
        } else {
            var cookieBooksIdsList = cookieData.split("/");
            for (var bookId : cookieBooksIdsList) {
                booksIds.add(Integer.parseInt(bookId));
            }
            var stringBooksIds = booksIds.stream().map(Object::toString).toList();
            stringJoiner.add(String.join("/", stringBooksIds));
        }
        var cookie = new Cookie(cookieName.name(), stringJoiner.toString());
        cookie.setPath("/");
        httpServletResponse.addCookie(cookie);
    }
}
