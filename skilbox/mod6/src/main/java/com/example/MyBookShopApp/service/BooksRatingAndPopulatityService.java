package com.example.MyBookShopApp.service;

import com.example.MyBookShopApp.data.book.Book;
import com.example.MyBookShopApp.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BooksRatingAndPopulatityService {
    @Autowired
    private  BookRepository bookRepository;

    public Page<Book> getPageOfPopularBooks(Integer offset, Integer limit) {
        bookRepository.updStatB();
        bookRepository.updStatC();
        bookRepository.updStatK();
        bookRepository.updStatP();
        bookRepository.updNullStat();

        var li = bookRepository.findAll();

        for(var x : li){
            var e = x.getStatB()+x.getStatC()*0.7+x.getStatK()*0.4;
                x.setStatP(e);
                bookRepository.save(x);
        }

        var nextPage = PageRequest.of(offset, limit);
        return bookRepository.findAllByOrderByStatPDesc(nextPage);
    }

}
