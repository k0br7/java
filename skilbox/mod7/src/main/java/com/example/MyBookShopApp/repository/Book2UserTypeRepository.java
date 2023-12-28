package com.example.MyBookShopApp.repository;

import com.example.MyBookShopApp.data.book.links.Book2UserTypeEntity;
import com.example.MyBookShopApp.data.enums.B2UType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Book2UserTypeRepository extends JpaRepository<Book2UserTypeEntity,Integer> {

    Book2UserTypeEntity findByCode(B2UType code);

}
