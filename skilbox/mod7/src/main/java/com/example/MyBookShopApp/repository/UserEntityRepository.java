package com.example.MyBookShopApp.repository;

import com.example.MyBookShopApp.data.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity,Integer> {
}
