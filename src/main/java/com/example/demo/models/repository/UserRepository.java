package com.example.demo.models.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmail(String email);

}// Interface Closure